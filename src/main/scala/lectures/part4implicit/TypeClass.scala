package lectures.part4implicit


trait Animal
final case class Dog(name: String) extends Animal
final case class Cat(name: String) extends Animal
object Humanish {
  trait HumanLike[A] {
    def speak(speaker: A): Unit
  }
  object HumanLike {
    implicit object DogHumanLike extends HumanLike[Dog] {
      def speak(dog: Dog): Unit = {
        println(s"I'm a dog, my name is ${dog.name}")
      }
    }
  }
}
object TypeClass extends App {
  import Humanish.HumanLike
  def makeHumanLikeThingSpeak[A](animal: A)(implicit humanlike: HumanLike[A]): Unit = {
    humanlike.speak(animal)
  }
  makeHumanLikeThingSpeak(Dog("Rover"))
}
