package github.dbousamra.ship.entities

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d._
import github.dbousamra.ship.components._
import github.dbousamra.ecs._

object Ship {

  def create(x: Float, y: Float, radius: Float, world: World): Entity = {

    val bodyDef = new BodyDef()
    bodyDef.`type` = BodyDef.BodyType.DynamicBody
    bodyDef.position.set(x, y)
    val body = world.createBody(bodyDef)

    val circle = new CircleShape()
    circle.setRadius(radius)

    val fixtureDef = new FixtureDef()
    fixtureDef.shape = circle

    body.createFixture(fixtureDef)
    body.setUserData(circle)

    val entity = Entity.create()

    entity.addComponent(PlayerComponent())
    entity.addComponent(TransformComponent(new Vector2(body.getPosition.x, body.getPosition.y), 0f))
    entity.addComponent(PhysicsComponent(body))
    entity.addComponent(ShapeComponent(circle))
    entity.addComponent(RemainInBoundsComponent())

    entity
  }

}
