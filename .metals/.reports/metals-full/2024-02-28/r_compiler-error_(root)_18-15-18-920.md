file://<WORKSPACE>/src/main/scala/Module%2012/exercises-12.worksheet.sc
### java.lang.AssertionError: assertion failed

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 3.3.1
Classpath:
<WORKSPACE>/.bloop/root/bloop-bsp-clients-classes/classes-Metals-mONe5NoGTwGlbquaVQ2AXA== [exists ], <HOME>/.cache/bloop/semanticdb/com.sourcegraph.semanticdb-javac.0.9.9/semanticdb-javac-0.9.9.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala3-library_3/3.3.1/scala3-library_3-3.3.1.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.10/scala-library-2.13.10.jar [exists ]
Options:
-Xsemanticdb -sourceroot <WORKSPACE>


action parameters:
uri: file://<WORKSPACE>/src/main/scala/Module%2012/exercises-12.worksheet.sc
text:
```scala
object worksheet{
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
  
        
        case Withdrawal(accountType, accountNum, amount) =>
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
  //         Account.applyTransaction(BalanceEnquiry(Savings, "003")) should be (200)
  
  //         // should not allow another 400 withdrawal
  //         intercept[IllegalStateException] {
  //           Account.applyTransaction(Withdrawal(Savings, "003", 400))
  //         }
  
  //         // should still have 200 left
  //         Account.applyTransaction(BalanceEnquiry(Savings, "003")) should be (200)
  //       }
  
  //       it("should prevent more than 1000 overdraft on checking") {
  //         // We now want different rules for Checking accounts.
  //         //
  //         // NOTE: Instead of altering the body of the Withdrawal
  //         // method with an if statement, use the pattern matching rules to create two separate cases in
  //         // applyTransaction, so that one works for Checking and one works for Savings, with the different rules
  
  //         Account.applyTransaction(OpenAccount(Checking, "003"))
  
  //         // add a starting balance
  //         Account.applyTransaction(Deposit(Checking, "003", 1000))
  
  //         // Now make a couple of withdrawals
  //         Account.applyTransaction(Withdrawal(Checking, "003", 800))
  //         Account.applyTransaction(Withdrawal(Checking, "003", 800))
  
  //         // should have 600 overdraft now
  //         Account.applyTransaction(BalanceEnquiry(Checking, "003")) should be (-600)
  
  //         // should not allow another 800 withdrawal
  //         intercept[IllegalStateException] {
  //           Account.applyTransaction(Withdrawal(Checking, "003", 800))
  //         }
  
  //         // should still have 200 left
  //         Account.applyTransaction(BalanceEnquiry(Checking, "003")) should be (-600)
  //       }
  //     }
  
  //     it("should prevent duplicate account numbers for the same account type") {
  //       // finally, add a guard to make sure that an account is not overwritten.
  //       //
  //       // NOTE! again, instead of throwing an exception in the OpenAccount handler for applyTransaction,
  //       // instead add a NEW case above that, for OpenAccount with an if-guard to check if that account number
  //       // already exists for that account type, and throw the exception there. You should not have to change
  //       // the existing OpenAccount case, just add a new one above it.
  
  
  //       Account.applyTransaction(OpenAccount(Checking, "010"))
  //       Account.applyTransaction(OpenAccount(Savings, "020"))
  //       Account.applyTransaction(Deposit(Checking, "010", 100))
  //       Account.applyTransaction(Deposit(Savings, "020", 200))
  
  //       intercept[IllegalStateException] {
  //         Account.applyTransaction(OpenAccount(Checking, "010"))
  //       }
  
  //       intercept[IllegalStateException] {
  //         Account.applyTransaction(OpenAccount(Savings, "020"))
  //       }
  
  //       Account.applyTransaction(BalanceEnquiry(Checking, "010")) should be (100)
  //       Account.applyTransaction(BalanceEnquiry(Savings, "020")) should be (200)
  //     }
  //   }
  // }
}
```



#### Error stacktrace:

```
scala.runtime.Scala3RunTime$.assertFailed(Scala3RunTime.scala:11)
	dotty.tools.dotc.core.TypeOps$.dominators$1(TypeOps.scala:248)
	dotty.tools.dotc.core.TypeOps$.approximateOr$1(TypeOps.scala:382)
	dotty.tools.dotc.core.TypeOps$.orDominator(TypeOps.scala:395)
	dotty.tools.dotc.core.Types$OrType.join(Types.scala:3435)
	dotty.tools.dotc.core.Types$OrType.widenUnionWithoutNull(Types.scala:3451)
	dotty.tools.dotc.core.Types$Type.widenUnion(Types.scala:1296)
	dotty.tools.dotc.core.ConstraintHandling.widenOr$1(ConstraintHandling.scala:652)
	dotty.tools.dotc.core.ConstraintHandling.widenInferred(ConstraintHandling.scala:668)
	dotty.tools.dotc.core.ConstraintHandling.widenInferred$(ConstraintHandling.scala:29)
	dotty.tools.dotc.core.TypeComparer.widenInferred(TypeComparer.scala:30)
	dotty.tools.dotc.core.TypeComparer$.widenInferred(TypeComparer.scala:3031)
	dotty.tools.dotc.typer.Namer.rhsType$1(Namer.scala:1926)
	dotty.tools.dotc.typer.Namer.cookedRhsType$1(Namer.scala:1932)
	dotty.tools.dotc.typer.Namer.lhsType$1(Namer.scala:1933)
	dotty.tools.dotc.typer.Namer.inferredResultType(Namer.scala:1944)
	dotty.tools.dotc.typer.Namer.inferredType$1(Namer.scala:1691)
	dotty.tools.dotc.typer.Namer.valOrDefDefSig(Namer.scala:1698)
	dotty.tools.dotc.typer.Namer.defDefSig(Namer.scala:1789)
	dotty.tools.dotc.typer.Namer$Completer.typeSig(Namer.scala:791)
	dotty.tools.dotc.typer.Namer$Completer.completeInCreationContext(Namer.scala:934)
	dotty.tools.dotc.typer.Namer$Completer.complete(Namer.scala:814)
	dotty.tools.dotc.core.SymDenotations$SymDenotation.completeFrom(SymDenotations.scala:174)
	dotty.tools.dotc.core.Denotations$Denotation.completeInfo$1(Denotations.scala:187)
	dotty.tools.dotc.core.Denotations$Denotation.info(Denotations.scala:189)
	dotty.tools.dotc.core.SymDenotations$SymDenotation.ensureCompleted(SymDenotations.scala:393)
	dotty.tools.dotc.typer.Typer.retrieveSym(Typer.scala:2989)
	dotty.tools.dotc.typer.Typer.typedNamed$1(Typer.scala:3014)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:3111)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3184)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3188)
	dotty.tools.dotc.typer.Typer.traverse$1(Typer.scala:3210)
	dotty.tools.dotc.typer.Typer.typedStats(Typer.scala:3256)
	dotty.tools.dotc.typer.Typer.typedClassDef(Typer.scala:2669)
	dotty.tools.dotc.typer.Typer.typedTypeOrClassDef$1(Typer.scala:3036)
	dotty.tools.dotc.typer.Typer.typedNamed$1(Typer.scala:3040)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:3111)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3184)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3188)
	dotty.tools.dotc.typer.Typer.traverse$1(Typer.scala:3210)
	dotty.tools.dotc.typer.Typer.typedStats(Typer.scala:3256)
	dotty.tools.dotc.typer.Typer.typedClassDef(Typer.scala:2669)
	dotty.tools.dotc.typer.Typer.typedTypeOrClassDef$1(Typer.scala:3036)
	dotty.tools.dotc.typer.Typer.typedNamed$1(Typer.scala:3040)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:3111)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3184)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3188)
	dotty.tools.dotc.typer.Typer.traverse$1(Typer.scala:3210)
	dotty.tools.dotc.typer.Typer.typedStats(Typer.scala:3256)
	dotty.tools.dotc.typer.Typer.typedPackageDef(Typer.scala:2812)
	dotty.tools.dotc.typer.Typer.typedUnnamed$1(Typer.scala:3081)
	dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:3112)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3184)
	dotty.tools.dotc.typer.Typer.typed(Typer.scala:3188)
	dotty.tools.dotc.typer.Typer.typedExpr(Typer.scala:3300)
	dotty.tools.dotc.typer.TyperPhase.typeCheck$$anonfun$1(TyperPhase.scala:44)
	dotty.tools.dotc.typer.TyperPhase.typeCheck$$anonfun$adapted$1(TyperPhase.scala:54)
	scala.Function0.apply$mcV$sp(Function0.scala:42)
	dotty.tools.dotc.core.Phases$Phase.monitor(Phases.scala:440)
	dotty.tools.dotc.typer.TyperPhase.typeCheck(TyperPhase.scala:54)
	dotty.tools.dotc.typer.TyperPhase.runOn$$anonfun$3(TyperPhase.scala:88)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.immutable.List.foreach(List.scala:333)
	dotty.tools.dotc.typer.TyperPhase.runOn(TyperPhase.scala:88)
	dotty.tools.dotc.Run.runPhases$1$$anonfun$1(Run.scala:246)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.ArrayOps$.foreach$extension(ArrayOps.scala:1321)
	dotty.tools.dotc.Run.runPhases$1(Run.scala:262)
	dotty.tools.dotc.Run.compileUnits$$anonfun$1(Run.scala:270)
	dotty.tools.dotc.Run.compileUnits$$anonfun$adapted$1(Run.scala:279)
	dotty.tools.dotc.util.Stats$.maybeMonitored(Stats.scala:67)
	dotty.tools.dotc.Run.compileUnits(Run.scala:279)
	dotty.tools.dotc.Run.compileSources(Run.scala:194)
	dotty.tools.dotc.interactive.InteractiveDriver.run(InteractiveDriver.scala:165)
	scala.meta.internal.pc.MetalsDriver.run(MetalsDriver.scala:45)
	scala.meta.internal.pc.PcCollector.<init>(PcCollector.scala:44)
	scala.meta.internal.pc.PcSemanticTokensProvider$Collector$.<init>(PcSemanticTokensProvider.scala:61)
	scala.meta.internal.pc.PcSemanticTokensProvider.Collector$lzyINIT1(PcSemanticTokensProvider.scala:61)
	scala.meta.internal.pc.PcSemanticTokensProvider.Collector(PcSemanticTokensProvider.scala:61)
	scala.meta.internal.pc.PcSemanticTokensProvider.provide(PcSemanticTokensProvider.scala:90)
	scala.meta.internal.pc.ScalaPresentationCompiler.semanticTokens$$anonfun$1(ScalaPresentationCompiler.scala:109)
```
#### Short summary: 

java.lang.AssertionError: assertion failed