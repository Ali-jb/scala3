import scala.annotation.tailrec


//   test ("Refactoring var to val") {

    // this method passes, but it's not very "scala-y", see if you can rid it of the need
    // to use a var but still pass the tests
    //
    // Hint: maybe recursion? List.head will give you the first value, List.tail will give you the rest


// def max(numbers : List[Int]) = {
//   var maxSoFar = Int.MinValue

//   for (n <- numbers) {
//     if (n > maxSoFar) maxSoFar = n
//   }
//   maxSoFar
// }

// def max(numbers: List[Int]): Int = 
//   if numbers == Nil then 
//     Int.MinValue
//   else 
//     val maxOfRest = max(numbers.tail)
//     println(maxOfRest)
//     if numbers.head > maxOfRest then numbers.head else maxOfRest

// max(List(1, 2, 10, 4, 5))
// max(Nil)


// -----------------------------------------------------------------------------------------------------------------------

import java.io.File

//   // there are files in the "." directory that have the extension .shkspr, complete the function below
//   // using a for to filter out all files with the .shkspr extension and return a list of their filenames
//   // so that the tests below are satisfied

def listShakespeareFiles(dirPath: String): Array[String] = 
  val fileList = (new java.io.File(dirPath)).listFiles
  for file <-fileList if file.getName.endsWith(".shkspr") yield file.getName

val filteredfilenames = listShakespeareFiles("/home/ali/code/scalaproject/target").toList


//   // question: can you guess why we define this method outside of the test below?

//     shakespeareFilenames.length should be (3)
//     shakespeareFilenames should contain theSameElementsAs (List("caesar.shkspr", "hamlet.shkspr", "romeo.shkspr"))
//   }

//   // complete the method definition below to open a file, read the first line, close the file
//   // and return the first line that was read. Make sure that the file is always closed, even
//   // if there is an exception. new BufferedReader(new FileReader(filePath)) should give you
//   // what you need

//   def firstLineOfFile(filePath: String) : String = {
//     //Source.fromFile(filePath).getLines().toList.head
//     val reader = new BufferedReader(new FileReader(filePath))
//     println("************" + filePath)
//     try {
//       reader.readLine()
//     }
//     finally {
//       reader.close()
//     }

//   }

//   test ("First line of file") {
//     firstLineOfFile("caesar.shkspr") should be ("Beware the ides of March.")
//     firstLineOfFile("romeo.shkspr") should be ("O Romeo, Romeo, wherefore art thou Romeo?")
//     firstLineOfFile("hamlet.shkspr") should be ("The lady doth protest too much, methinks.")

//     intercept[java.io.FileNotFoundException] { // nice, huh?
//       firstLineOfFile("macbeth.shkspr") should be ("")
//     }
//   }

//   // finish this method so that the string is first trimmed (get rid of leading and trailing space)
//   // and then the last character is matched to either a '?' ("Question") or anything else ("Statement")
//   // so that the test below passes
//   def statementOrQuestion(str : String) : String = 
//     str.trim match {
//       case x if x.endsWith("?")  => "Question"
//       case _ => "Statement"
//     }

//   test ("statement or question?") {
//     statementOrQuestion("hello") should be ("Statement")
//     statementOrQuestion("hello.") should be ("Statement")
//     statementOrQuestion("hello. ") should be ("Statement")
//     statementOrQuestion("  hello?") should be ("Question")
//     statementOrQuestion("hello? ") should be ("Question")
//   }



//   // extra credit
//   test ("shakespeare files contain question or statement?") {
//     // combine the methods you have created here to define and test a method that creates a map of
//     // the shakespeare quotation file names in the working directory (".") as the key, and whether
//     // they contain a statement or question, for example:
//     // "caesar.shkspr" -> "Statement"
//     // "romeo.shkspr" -> "Question"
//     // "hamlet.shkspr" -> "Statement"
//     // hint - consider using a scala.collection.immutable.HashMap[String, String] and a var
//     // or a scala.collection.mutable.HashMap[String, String] and a val
//     // can you add a test for a key of "macbeth.shkspr" that ensures a java.util.NoSuchElementException?
//     // extra extra credit - can you find a way to do it without using either a var or a mutable Map?
//     //var shksprMap = new scala.collection.immutable.HashMap[String, String]

//     //val shksprMap = new scala.collection.immutable.HashMap[String, String]

//     def expressionOrStatement(line : String) = {
//       line.trim match {
//         case x if (x.endsWith("?")) => "Question"
//         case _ => "Statement"
//       }
//     }

//       val shakespeareFilenames = listShakespeareFiles(".").toList
//       val shksprMap = {for {
//         file <- shakespeareFilenames
//         if (file.endsWith(".shkspr"))
//         firstLine = Source.fromFile(file).getLines().toList.head
//       } yield (file -> expressionOrStatement(firstLine)) }.toMap

//         //shksprMap ++ Map(file -> expressionOrStatement(firstLine))


//     shksprMap("caesar.shkspr") should be ("Statement")
//     shksprMap("romeo.shkspr") should be ("Question")
//     shksprMap("hamlet.shkspr") should be ("Statement")

//   }