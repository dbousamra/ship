package github.dbousamra.ship.systems

import com.badlogic.gdx.math.Vector2
import github.dbousamra.ship.components._
import github.dbousamra.ecs._
import github.dbousamra.ship.GameUniverse
import github.dbousamra.ship.entities.EntityFactory

case class PlayerSystem(universe: GameUniverse) extends System {
  def process(es: List[Entity], delta: Float): Unit = {

    val entities = es.flatMap { e =>
      for {
        control <- e.getComponent[PlayerControlComponent]
        physics <- e.getComponent[PhysicsComponent]
        _ <- e.getComponent[PlayerComponent]
      } yield (control, physics)
    }
    entities.foreach {
      case (control, physics) => {
        val direction = physics.body.getWorldVector(new Vector2(-1, 0))
        physics.body.setLinearVelocity(direction.scl(10))

        if (control.turningLeft) {
          physics.body.setAngularVelocity(4f)
        } else if (control.turningRight) {
          physics.body.setAngularVelocity(-4f)
        } else {
          physics.body.setAngularVelocity(0f)
        }
        if (control.shooting) {
          engine.foreach(_.addEntity(EntityFactory.createShip(universe)))
        }
      }
    }
  }
}
