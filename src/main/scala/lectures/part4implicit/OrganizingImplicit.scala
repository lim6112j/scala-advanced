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
  implicit val personOrdering: Ordering[Person] = Ordering.fromLessThan(_.name < _.name)
  println(persons.sorted)
  /*
  implicit scope
    - normal scope = local scope
    - imported scope
    - companions of all types involved in the method signature
      - List
      - Ordering
      - all the types involved
   */
  // def sorted[B :> A](implicit ord: Ordering[B]): List[B]
}
