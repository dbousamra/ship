package github.dbousamra.ship.systems

import github.dbousamra.ship.components._
import github.dbousamra.ship.GameUniverse
import github.dbousamra.ecs._

case class PlayerControlSystem(universe: GameUniverse) extends System {
  def process(es: List[Entity], delta: Float): Unit = {

    val entities = es.flatMap { e =>
      for {
        control <- e.getComponent[PlayerControlComponent]
      } yield (control)
    }
    entities.foreach { control =>
      {
        control.turningLeft = universe.keyboard.left
        control.turningRight = universe.keyboard.right
        control.shooting = universe.keyboard.space
      }
    }
  }
}
