package lectures.part4implicit

object OrganizingImplicit extends App {
//  implicit val reverseOrdering: Ordering[Int] = Ordering.fromLessThan(_ > _)
  implicit def normalOrdering: Ordering[Int] = Ordering.fromLessThan(_ < _)
  println(List(1,2,5,4).sorted)
  // scala.Predef
  /*
  Implicits ( used as implicit parameters
    - val/var
    - object
    - accessor methods = defs with no parentheses
   */

  // Exercise
  case class Person(name: String, age: Int)
  val persons = List(
    Person("lim", 30),
    Person("Peter", 42),
    Person("John", 20)
  )
//  object Person {
//    implicit val personOrdering: Ordering[Person] = Ordering.fromLessThan(_.name < _.name)
//  }
//  implicit val ageOrdering: Ordering[Person] = Ordering.fromLessThan(_.age < _.age)
//  println(persons.sorted)
  /*
  implicit scope
    - normal scope = local scope
    - imported scope
    - companions of all types involved in the method signature
      - List
      - Ordering
      - all the types involved -> A or any super type
   */
  // def sorted[B :> A](implicit ord: Ordering[B]): List[B]
  object AlphabeticOrdering {
    implicit val personOrdering: Ordering[Person] = Ordering.fromLessThan(_.name < _.name)
  }
  object AgeOrdering {
    implicit val ageOrdering: Ordering[Person] = Ordering.fromLessThan(_.age < _.age)
  }
  import AlphabeticOrdering._
  println(persons.sorted)
}
