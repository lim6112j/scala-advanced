package lectures.part5ts

object SelfTypes extends App {
  // requiring a type to be mixed in
  trait Instrumentalist {
    def play(): Unit
  }
  trait Singer { self: Instrumentalist => // SELF TYPE : whoever implememts Singer to implement Instrumentalist
    def sing(): Unit
  }
  class LeadSinger extends Singer with Instrumentalist {
    override def sing(): Unit = ???
    override def play(): Unit = ???
  }
  // wrong below
//  class Vocalist extends Singer {
//    override def sing(): Unit = ???
//  }
  val jamesHetfield = new Singer with Instrumentalist {
    override def sing(): Unit = ???

    override def play(): Unit = ???
  }
  class Guitarlist extends Instrumentalist {
    override def play(): Unit = println("(guitar solo)")
  }
  val ericClapton = new Guitarlist with Singer {
    override def sing(): Unit = ???
  }
  // self type vs inheritance
  class A
  class B extends A // B is an A

  trait T
  trait S {self: T =>} // S requires T

  // CAKE PATTERN => "Dependency Injection"

    // classic DI : compose at runtime
  class Component {
    // API
  }
  class ComponentA extends Component
  class ComponentB extends Component
  class DependentComponent(val component: Component) // change freely ComponentA or ComponentB at runtime, compose application
    // CAKE PATTERN : compose at compile time
  trait ScalaComponent {
      //API
      def action(x: Int): String
  }
  trait ScalaDependantComponent {self: ScalaComponent =>
    def dependentAction(x: Int): String = action(x) + "this rocks"
  }
  trait ScalaAppication {self: ScalaDependantComponent => }
  // layer 1 : small component
  trait Picture extends ScalaComponent
  trait Stats extends ScalaComponent
  // layer 2 : compose
  trait Profile extends ScalaDependantComponent with Picture
  trait Analytics extends ScalaDependantComponent with Stats
  // layer 3 : app
  trait AnalyticsApp extends ScalaAppication with Analytics

  // cyclical dependency
//  class X extends Y
//  class Y extends X // wrong

 trait X {self: Y => }
 trait Y {self: X => } // true
}
