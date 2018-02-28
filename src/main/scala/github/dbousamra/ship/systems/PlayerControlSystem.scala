package github.dbousamra.ship.systems

import com.badlogic.gdx.math.Vector2
import github.dbousamra.ship.components._
import github.dbousamra.ship.controllers._
import github.dbousamra.ecs._

case class PlayerControlSystem(controller: KeyboardController) extends System {
  def process(es: List[Entity], delta: Float): Unit = {

    val entities = es.flatMap { e =>
      for {
        physics <- e.getComponent[PhysicsComponent]
        _ <- e.getComponent[PlayerComponent]
      } yield (e, physics)
    }
    entities.foreach {
      case (_, physics) => {
        val direction = physics.body.getWorldVector(new Vector2(-1, 0))
        physics.body.setLinearVelocity(direction.scl(10))

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
