package github.dbousamra.systems

import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.physics.box2d.World
import github.dbousamra.components._
import github.dbousamra.ecs._

case class PhysicsSystem(world: World) extends System {
  def process(es: List[Entity], delta: Int): Unit = {

    world.step(1 / 60f, 6, 2)

    val entities = es.flatMap { e =>
      for {
        physics <- e.get[PhysicsComponent]
        transform <- e.get[TransformComponent]
      } yield (e, physics, transform)
    }
    entities.foreach {
      case (e, physics, transform) => {
        transform.position.x = physics.body.getPosition().x
        transform.position.y = physics.body.getPosition().y
        transform.rotation = physics.body.getAngle() * MathUtils.radiansToDegrees
        println(e)
      }
    }
  }
}
