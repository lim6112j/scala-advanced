package lectures.part5ts

object FBoundedPolymorphism extends App {
//  trait Animal {
//    def breed: List[Animal]
//  }
//  class Cat extends Animal {
//    override def breed: List[Animal] = ??? // List[Cat]
//  }
//  class Dog extends Animal {
//    override def breed: List[Animal] = ??? // List[Dog]
//  }
  // solution -1 : naive
//  trait Animal {
//    def breed: List[Animal]
//  }
//  class Cat extends Animal {
//    override def breed: List[Cat] = ??? // List[Cat]
//  }
//  class Dog extends Animal {
//    override def breed: List[Dog] = ??? // List[Dog]
//  }
  // solution -2 FBP(F-bouned polymorphism)
//  trait Animal[A <: Animal[A]] { // recursive type : F-Bounded Polymorphism
//    def breed: List[Animal[A]]
//  }
//  class Cat extends Animal[Cat] {
//    override def breed: List[Cat] = ??? // List[Cat]
//  }
//  class Dog extends Animal[Dog] {
//    override def breed: List[Dog] = ??? // List[Dog]
//  }
//  trait Entity[E <: Entity[E]] // ORM
//  class Person extends Comparable[Person] { //FBP
//    override def compareTo(t: Person): Int = ???
//  }
//  class Crocodile extends Animal[Dog] { // should match crocodile and dog , consult solution 3
//    override def breed: List[Animal[Dog]] = ???
//  }

  // solution 3 - FBP + self type
//  trait Animal[A <: Animal[A]] { self: A =>
//    def breed: List[Animal[A]]
//  }
//  class Cat extends Animal[Cat] {
//    override def breed: List[Cat] = ??? // List[Cat]
//  }
//  class Dog extends Animal[Dog] {
//    override def breed: List[Dog] = ??? // List[Dog]
//  }
//  // solved solution -2 problem
//  class Crocodile extends Animal[Crocodile] { // should match crocodile and dog , consult solution 3
//    override def breed: List[Crocodile] = ???
//  }
//  // but here is another problem
//  trait Fish extends Animal[Fish] {self: Fish => }
//  class Shark extends Fish {
//    //    override def breed: List[Animal[Shark]] = ??? // error
//    override def breed: List[Animal[Fish]] = List(new Cod) // wrong!!
//  }
//  class Cod extends Fish {
//    override def breed: List[Animal[Fish]] = ???
//  }

  // exercise

  // solution -4 type classes
  trait Animal
  trait CanBreed[A] {
    def breed(a: A): List[A]
  }
  class Dog extends Animal
  object Dog {
    implicit object DogsCanBreed extends CanBreed[Dog] {
      def breed(a: Dog): List[Dog] = List()
    }
  }
  implicit class CanBreedOps[A](animal: A) {
    def breed(implicit canBreed: CanBreed[A]): List[A] =
      canBreed.breed(animal)
  }
  val dog = new Dog
  dog.breed
}
