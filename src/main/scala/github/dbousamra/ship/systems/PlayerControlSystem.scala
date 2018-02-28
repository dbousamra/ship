package github.dbousamra.ship.systems

import github.dbousamra.ship.components._
import github.dbousamra.ship.GameUniverse
import github.dbousamra.ecs._

case class PlayerControlSystem(universe: GameUniverse) extends System {
  def process(es: List[Entity], delta: Float): Unit = {

    val entities = es.flatMap { e =>
      for {
        control <- e.getComponent[PlayerControlComponent]
      } yield (e, control)
    }
    entities.foreach {
      case (_, control) => {
        control.left = universe.keyboard.left
        control.right = universe.keyboard.right
        control.space = universe.keyboard.space
      }
    }
  }
}
