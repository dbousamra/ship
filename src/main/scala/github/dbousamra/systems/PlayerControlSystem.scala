package github.dbousamra.systems

import github.dbousamra.components._
import github.dbousamra.controllers.KeyboardController
import github.dbousamra.ecs._

case class PlayerControlSystem(controller: KeyboardController) extends System {
  def process(es: List[Entity], delta: Int): Unit = {

    val entities = es.flatMap { e =>
      for {
        physics <- e.get[PhysicsComponent]
        player <- e.get[PlayerComponent]
      } yield (e, physics, player)
    }
    entities.foreach {
      case (_, physics, _) => {
        if (controller.left) {
          physics.body.setAngularVelocity(4f)
        } else if (controller.right) {
          physics.body.setAngularVelocity(-4f)
        } else {
          physics.body.setAngularVelocity(0f)
        }
      }
    }

  }
}
