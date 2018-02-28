package github.dbousamra.ship.systems

import com.badlogic.gdx.physics.box2d.World
import github.dbousamra.ship.components._
import github.dbousamra.ecs._

case class PhysicsSystem(world: World) extends System {
  def process(es: List[Entity], delta: Float): Unit = {

    world.step(1 / 60f, 6, 2)

    val entities = es.flatMap { e =>
      for {
        physics <- e.getComponent[PhysicsComponent]
        transform <- e.getComponent[TransformComponent]
      } yield (e, physics, transform)
    }
    entities.foreach {
      case (_, physics, transform) => {

        transform.position.x = physics.body.getPosition.x
        transform.position.y = physics.body.getPosition.y
        transform.rotation = physics.body.getAngle()
      }
    }
  }
}
