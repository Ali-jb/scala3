import java.awt.Checkbox
import scala.BigDecimal.{int2bigDecimal, double2bigDecimal}


/*
 * Below is a spec for a bank account handler method. The case classes are provided for deposit,
 * withdrawal and balance enquiry, and you should complete the Account.applyTransaction method to behave in a way
 * that satisfies the specifications laid out here
 */


// First, the case classes - we provide these for speed, but you should make sure to understand some things
// for example, the Checking and Savings are case objects, not classes (which means there is only one
// of each, and the Transactions extend the Transaction sealed class providing the constructor params.

sealed class AccountType
case object Checking extends AccountType
case object Savings extends AccountType


sealed class Transaction(accountType: AccountType, accountNumber: String)

case class OpenAccount(accountType: AccountType, accountNumber: String)
    extends Transaction(accountType, accountNumber)

case class BalanceEnquiry(accountType: AccountType, accountNumber: String)
    extends Transaction(accountType, accountNumber)

case class Deposit(accountType: AccountType, accountNumber: String, amount: BigDecimal)
    extends Transaction(accountType, accountNumber)

case class Withdrawal(accountType: AccountType, accountNumber: String, amount: BigDecimal)
    extends Transaction(accountType, accountNumber)


// The account class itself is pretty simple - no surprises here, and not much safety
class Account:
  private var bal: BigDecimal = 0
  def balance =  bal
  def deposit(amnt: BigDecimal) = bal += amnt
  def withdrawal(amnt: BigDecimal) = bal -= amnt



// // The definition for Account is incomplete. You are expected to fill in the applyTransaction pattern match body,
// // as right now, it just throws an exception for most transactions you try to apply. Work through the specs below
// // to figure out the right contents for the pattern matcher. You should not need to change any of the other
// // methods in this class, only applyTransactions

object Account {
  val checkingAccounts = new collection.mutable.HashMap[String, Account]
  val savingsAccounts = new collection.mutable.HashMap[String, Account]

  private def accountsMapForType(accountType: AccountType) : collection.mutable.Map[String, Account] =
    accountType match
      case Checking => checkingAccounts
      case Savings => savingsAccounts
      case _ => throw new IllegalArgumentException("Unknown account type")
    

  def openAccount(accountType: AccountType, number: String) = 
    val account = new Account
    accountsMapForType(accountType) += number -> account
  

  // a convenience method to get the right account map type for the given account type - will be used in
  // your implementation below.

  def getAccount(accountType: AccountType, number: String) =
    accountsMapForType(accountType)(number)   // note how the scala rules start to come together, like currying


  // This is the method you have to complete - add cases into the transaction match body to meet all the specs below
  def applyTransaction(transaction: Transaction) =
    transaction match 

      case OpenAccount(accountType, accountNum) => 
        try {
          getAccount(accountType, accountNum)
          throw new IllegalStateException
        }
        catch {
          case e: NoSuchElementException => 
        }

        openAccount(accountType, accountNum)



      case BalanceEnquiry(accountType, accountNum) =>
        getAccount(accountType, accountNum).balance

      case Deposit(accountType, accountNum, amount) => getAccount(accountType, accountNum).deposit(amount)

      
      case Withdrawal(Savings, accountNum, amount) 
        if getAccount(Savings, accountNum).balance <= amount => 
          throw new IllegalStateException
        
      case Withdrawal(Savings, accountNum, amount) =>
       getAccount(Savings, accountNum).withdrawal(amount)

      case Withdrawal(Checking, accountNum, amount) 
        if getAccount(Checking, accountNum).balance + 1000<= amount => 
          throw new IllegalStateException
        
      case Withdrawal(Checking, accountNum, amount) =>
       getAccount(Checking, accountNum).withdrawal(amount)

      case _                                              
            => throw new IllegalArgumentException("Unknown transaction type")
    
}

// // And here are the specs you have to satisfy
// class Module12 extends FunSpec with Matchers with StopOnFirstFailure with SeveredStackTraces {

//   // note - the tests for Account all work, no action required here, but you can read through them if you want


val account = new Account
account.balance

//         account.balance should be (0)

account.withdrawal(1000)
account.balance 
//        
//         account.balance should be (-1000)

account.deposit(2000)
account.balance
//         
//         account.balance should be (1000)




//   // as you can see, the account itself has no validation to make sure a withdrawal doesn't cause an
//   // overdraft.


//   // Next: we should be able to create an account using the companion object, using an
//   // account number. You will need to add OpenAccount, BalanceEnquiry, Deposit and Withdrawal cases to the
//   // applyTransaction method in the Account companion object


// should not allow access to non-existent accounts
// Account.applyTransaction(BalanceEnquiry(Checking, "001")) ERROR! Account should be created first
// Account.applyTransaction(Deposit(Checking, "001", 100)) ERROR! Account should be created first
// Account.applyTransaction(Withdrawal(Savings, "002", 100)) ERROR! Account should be created first



// ("should allow account to be registered") 
Account.applyTransaction(OpenAccount(Checking, "001"))
// Account.applyTransaction(OpenAccount(Checking, "001")) Error! repeated account creation

Account.applyTransaction(OpenAccount(Savings, "001"))
Account.applyTransaction(BalanceEnquiry(Checking, "001")) 

//Account.applyTransaction(BalanceEnquiry(Checking, "001")) should be (0)


//     // OK, now we get to the more fun parts. The specs below should be clear enough to see
//     // what is needed, but some comments give hints as to how to do what's needed.
//     // In particular, pay attention to the types of the exceptions expected

//     describe("limit account withdrawals") {
//       it("should prevent negative balance on savings") {
//         // do this by adapting Withdrawal match case
Account.applyTransaction(OpenAccount(Savings, "003"))

//         // add a starting balance
Account.applyTransaction(Deposit(Savings, "003", 1000))
Account.applyTransaction(BalanceEnquiry(Savings, "003"))


//         // Now make a couple of withdrawals

Account.applyTransaction(Withdrawal(Savings, "003", 400))
Account.applyTransaction(Withdrawal(Savings, "003", 400))
Account.applyTransaction(BalanceEnquiry(Savings, "003"))


//         // should have 200 left

//         // should not allow another 400 withdrawal

// Account.applyTransaction(Withdrawal(Savings, "003", 400)) ERROR! No more money left.



//       it("should prevent more than 1000 overdraft on checking") {
//         // We now want different rules for Checking accounts.
//         //
//         // NOTE: Instead of altering the body of the Withdrawal
//         // method with an if statement, use the pattern matching rules to create two separate cases in
//         // applyTransaction, so that one works for Checking and one works for Savings, with the different rules

Account.applyTransaction(OpenAccount(Checking, "003"))

//         // add a starting balance
Account.applyTransaction(Deposit(Checking, "003", 1000))

//         // Now make a couple of withdrawals
Account.applyTransaction(Withdrawal(Checking, "003", 800))
Account.applyTransaction(Withdrawal(Checking, "003", 800))

Account.applyTransaction(BalanceEnquiry(Checking, "003"))
//         // should have 600 overdraft now


//         // should not allow another 800 withdrawal
// Account.applyTransaction(Withdrawal(Checking, "003", 800))  ERROR! No more money left.


//     it("should prevent duplicate account numbers for the same account type") {
//       // finally, add a guard to make sure that an account is not overwritten.
//       //
//       // NOTE! again, instead of throwing an exception in the OpenAccount handler for applyTransaction,
//       // instead add a NEW case above that, for OpenAccount with an if-guard to check if that account number
//       // already exists for that account type, and throw the exception there. You should not have to change
//       // the existing OpenAccount case, just add a new one above it.


    Account.applyTransaction(OpenAccount(Checking, "010"))
    Account.applyTransaction(OpenAccount(Savings, "020"))
    Account.applyTransaction(Deposit(Checking, "010", 100))
    Account.applyTransaction(Deposit(Savings, "020", 200))


        // Account.applyTransaction(OpenAccount(Checking, "010")) ERROR
        // Account.applyTransaction(OpenAccount(Savings, "020")) ERROR


Account.applyTransaction(BalanceEnquiry(Checking, "010"))
Account.applyTransaction(BalanceEnquiry(Savings, "020")) 
