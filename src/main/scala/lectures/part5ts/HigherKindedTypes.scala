package lectures.part5ts

object HigherKindedTypes extends App {
  // use HKT
  trait Monad[F[_], A] {
    def flatMap[B](f: A => F[B]): F[B]
    def map[B](f: A => B): F[B]
  }
  class MonadList(list: List[Int]) extends Monad[List, Int] {
    override def flatMap[B](f: Int => List[B]): List[B] = list.flatMap(f)

    override def map[B](f: Int => B): List[B] = list.map(f)
  }
  def multiply[F[_], A, B](ma: Monad[F, A], mb: Monad[F, B]): F[(A, B)] =
    for {
      a <- ma
      b <- mb
    } yield (a, b)
  val monadList = new MonadList(List(1,2))
  monadList.flatMap(x => List(x, x + 1)) // List(Int)
  // Monad[List, Int] => List[Int]
  monadList.map(_ * 2)
  // Monad[List, Int] => List[Int]
  println(multiply(new MonadList(List(1,2,3)), new MonadList(List(3,1,3))))
}
