package lectures.part4implicit

object ImplicitIntro extends App {
  val pair = "Daniel" -> "555"
  println(pair)
  case class Person(name: String) {
    def greet = s"Hi, my name is $name"
  }
//  implicit def fromStringToPerson(str: String): Person = Person(str)
//  println("lim".greet) // println(fromStringToPerson("lim").greet)
  class A {
    def greet: Int = 2
  }
  implicit def fromStringToA(str: String): A = new A
  println(fromStringToA("4").greet)
  println("4".greet)
  // implicit parameters
  def increment(x: Int)(implicit amount: Int): Int = x + amount
  implicit val defaultAmount = 10 // not default args
  println(increment(2))
}
