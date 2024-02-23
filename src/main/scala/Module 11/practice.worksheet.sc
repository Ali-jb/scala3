// //pre and post conditions

// //pre-condition
// val x: Int = 2
// require(x > 0, "x must be positive")


// //post-condition
// import $ivy.`org./scalactic::scalactic:3.2.12`
// import org.scalactic.Requirements._

// def square(x: Int): Int = 
//     (x * x) ensuring (x => x> 0, "error message")

// square(10)
// // square(0)

// //________________________________________________________

// // assert and assume can be disabled 
// // but require or requireState (from scalactic) can not.

// // $scala -Xdisable-assersions
// //________________________________________________________


// val ab: Int = 2

// val z = 1