package github.dbousamra.ship.screens

import com.badlogic.gdx._
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import github.dbousamra.ship.controllers.KeyboardController
import github.dbousamra.ecs._
import github.dbousamra.ship._
import github.dbousamra.ship.entities._
import github.dbousamra.ship.systems._

class PlayScreen(game: Entry) extends Screen {

  val engine = Engine()

  val physicsWorld = new World(new Vector2(0, -1), false)
  val keyboardController = KeyboardController()
  Gdx.input.setInputProcessor(keyboardController)

  val worldBounds = new Vector2(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT)

  engine.addEntity(
    Ship.create(
      x = Constants.WORLD_WIDTH / 2f,
      y = Constants.WORLD_HEIGHT / 2f,
      radius = 12f / Constants.PPM,
      physicsWorld
    )
  )

  val renderingSystem = RenderingSystem()
  engine.addSystem(PhysicsSystem(physicsWorld))
  engine.addSystem(renderingSystem)
  engine.addSystem(PhysicsDebugRenderer(physicsWorld, renderingSystem.camera))
  engine.addSystem(RemainInBoundsSystem(worldBounds))
  engine.addSystem(PlayerControlSystem(keyboardController))

  def render(delta: Float) = {
    engine.process(delta)
  }

  def dispose() = ()

  def show() = ()

  def resume() = ()

  def pause() = ()

  def hide() = ()

  def resize(width: Int, height: Int) = ()
}
