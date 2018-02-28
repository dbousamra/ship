package github.dbousamra.ship.systems

import com.badlogic.gdx.math.Vector2
import github.dbousamra.ship.components._
import github.dbousamra.ecs._

case class PlayerSystem() extends System {
  def process(es: List[Entity], delta: Float): Unit = {

    val entities = es.flatMap { e =>
      for {
        control <- e.getComponent[PlayerControlComponent]
        physics <- e.getComponent[PhysicsComponent]
        _ <- e.getComponent[PlayerComponent]
      } yield (e, control, physics)
    }
    entities.foreach {
      case (_, control, physics) => {
        val direction = physics.body.getWorldVector(new Vector2(-1, 0))
        physics.body.setLinearVelocity(direction.scl(10))

        if (control.left) {
          physics.body.setAngularVelocity(4f)
        } else if (control.right) {
          physics.body.setAngularVelocity(-4f)
        } else {
          physics.body.setAngularVelocity(0f)
        }
        if (control.space) {
          println("Firing")
//          fire()
        }
      }
    }
  }
}
