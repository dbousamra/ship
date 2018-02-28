package github.dbousamra.ship.entities

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d._
import com.badlogic.gdx.physics.box2d.{World => PhysicsWorld}
import github.dbousamra.ecs._
import github.dbousamra.ship.Constants
import github.dbousamra.ship.GameUniverse
import github.dbousamra.ship.components._

object EntityFactory {

  def createShip(universe: GameUniverse): Entity = {

    val x = Constants.WORLD_WIDTH / 2f
    val y = Constants.WORLD_HEIGHT / 2f
    val radius = 12f / Constants.PPM

    val bodyDef = new BodyDef()
    bodyDef.`type` = BodyDef.BodyType.DynamicBody
    bodyDef.position.set(x, y)
    val body = universe.physicsWorld.createBody(bodyDef)

    val circle = new CircleShape()
    circle.setRadius(radius)

    val fixtureDef = new FixtureDef()
    fixtureDef.shape = circle

    body.createFixture(fixtureDef)
    body.setUserData(circle)

    val entity = Entity.create()

    entity.addComponent(PlayerComponent())
    entity.addComponent(PlayerControlComponent())
    entity.addComponent(TransformComponent(new Vector2(body.getPosition.x, body.getPosition.y), 0f))
    entity.addComponent(PhysicsComponent(body))
    entity.addComponent(ShapeComponent(circle))
    entity.addComponent(RemainInBoundsComponent())

    entity
  }

  def createProjectile(world: World): Entity = {
    val entity = Entity.create()
    entity
  }

}
