package github.dbousamra.screens

import com.badlogic.gdx._
import com.badlogic.gdx.graphics._
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.utils.viewport._
import github.dbousamra._
import github.dbousamra.objects.Player

class PlayScreen(game: Entry) extends Screen {

  val camera = new OrthographicCamera()
  val viewPort = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, camera)
  camera.position.set(viewPort.getWorldWidth() / 2, viewPort.getWorldHeight() / 2, 0)

  val physicsWorld = new World(new Vector2(0, 0), false)
  val physicsWorldRenderer = new Box2DDebugRenderer()

  val player = Player(
    x = Constants.V_WIDTH / 2,
    y = Constants.V_HEIGHT / 2,
    radius = 12,
    world = physicsWorld
  )

  def render(delta: Float) = {
    update(delta)
    Gdx.gl.glClearColor(0, 0, 0, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
//    physicsWorldRenderer.render(physicsWorld, camera.combined)
    player.render()
  }

  def update(delta: Float): Unit = {
    physicsWorld.step(1 / 60f, 6, 2);
    camera.update()
    player.update(delta)
  }

  def dispose() = ()

  def show() = ()

  def resume() = ()

  def pause() = ()

  def hide() = ()

  def resize(width: Int, height: Int) = {
    viewPort.update(width, height)
  }
}
