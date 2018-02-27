package github.dbousamra.entities

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Game
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.CircleShape
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d._
import github.dbousamra.Constants
import github.dbousamra.components._
import github.dbousamra.controllers._
import github.dbousamra.ecs._
import github.dbousamra.systems._

object Ship {

  def create(x: Float, y: Float, radius: Float, world: World): Entity = {

    // Underlying physics body
    val bodyDef = new BodyDef()
    bodyDef.`type` = BodyDef.BodyType.DynamicBody
    bodyDef.position.set(x, y)
    val body = world.createBody(bodyDef)

    val circle = new CircleShape()
    circle.setRadius(radius)

    val fixtureDef = new FixtureDef()
    fixtureDef.shape = circle

    body.createFixture(fixtureDef)

    Entity.create(
      PlayerComponent(),
      TransformComponent(new Vector2(body.getPosition.x, body.getPosition.y), 0f),
      PhysicsComponent(body),
      ShapeComponent(circle)
    )
  }

}

class ShipGame extends ApplicationAdapter {

  val engine = new Engine()

  override def create(): Unit = {
    val physicsWorld = new World(new Vector2(0, -1), false)
    val keyboardController = KeyboardController()

    engine.addEntity(
      Ship.create(
        x = Constants.SCREEN_WIDTH / Constants.PPM / 2f,
        y = Constants.SCREEN_HEIGHT / Constants.PPM / 2f,
        radius = 12f / Constants.PPM,
        physicsWorld
      )
    )

    val renderingSystem = RenderingSystem()

    engine.addSystem(PhysicsSystem(physicsWorld))
    engine.addSystem(PlayerControlSystem(keyboardController))
    engine.addSystem(renderingSystem)
//    engine.addSystem(PhysicsDebugRenderer(physicsWorld, renderingSystem.camera))
  }

  override def render(): Unit = {
    engine.process()
  }
}

object ShipGameRunner {
  def main(args: Array[String]): Unit = {
    val cfg: LwjglApplicationConfiguration = new LwjglApplicationConfiguration
    cfg.title = "Hello world"
    cfg.width = Constants.SCREEN_WIDTH.toInt
    cfg.height = Constants.SCREEN_HEIGHT.toInt
    cfg.resizable = false

    new LwjglApplication(new ShipGame, cfg)

  }
}
