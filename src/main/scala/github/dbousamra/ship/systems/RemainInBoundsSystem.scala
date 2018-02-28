package github.dbousamra.ship.systems

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.CircleShape
import github.dbousamra.ship.components.PhysicsComponent
import github.dbousamra.ship.components.RemainInBoundsComponent
import github.dbousamra.ecs._

case class RemainInBoundsSystem(bounds: Vector2) extends System {
  def process(es: List[Entity], delta: Float): Unit = {
    val entities = es.flatMap { e =>
      for {
        physics <- e.getComponent[PhysicsComponent]
        _ <- e.getComponent[RemainInBoundsComponent]
      } yield (e, physics)
    }
    entities.foreach {
      case (_, physics) => {

        val bodyWidth = physics.body.getUserData.asInstanceOf[CircleShape].getRadius
        println(bodyWidth)
        val xPosition = math.max(0 + bodyWidth, math.min(physics.body.getPosition.x, bounds.x - bodyWidth))
        val yPosition = math.max(0 + bodyWidth, math.min(physics.body.getPosition.y, bounds.y - bodyWidth))
        physics.body.setTransform(
          xPosition,
          yPosition,
          physics.body.getAngle
        )
      }
    }
  }
}
