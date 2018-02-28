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
  val physicsWorld = new World(new Vector2(0, 0), false)

  val keyboardController = KeyboardController()

  val universe = GameUniverse(engine, physicsWorld, keyboardController)

  val renderingSystem = RenderingSystem()
  engine.addSystem(PhysicsSystem(universe))
  engine.addSystem(renderingSystem)
  engine.addSystem(PhysicsDebugRenderer(universe, renderingSystem.camera))
  engine.addSystem(RemainInBoundsSystem(new Vector2(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT)))
  engine.addSystem(PlayerControlSystem(universe))
  engine.addSystem(PlayerSystem(universe))

  engine.addEntity(EntityFactory.createShip(universe))

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
