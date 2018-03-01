package github.dbousamra.ship.systems

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics._
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.physics.box2d._
import com.badlogic.gdx.utils.viewport.FitViewport
import github.dbousamra.ship.components._
import github.dbousamra.ecs._
import github.dbousamra.ship.Constants

case class ShapeRenderingSystem() extends System {

  val camera: Camera = new OrthographicCamera()
  val viewPort = new FitViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, camera)
  viewPort.update(Constants.WORLD_WIDTH.toInt, Constants.WORLD_HEIGHT.toInt)
  val renderer: ShapeRenderer = new ShapeRenderer()

  camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0)

  def process(es: List[Entity], delta: Float): Unit = {
    val entities = es.flatMap { e =>
      for {
        transform <- e.getComponent[TransformComponent]
        shape <- e.getComponent[ShapeComponent]
      } yield (transform, shape)
    }

    Gdx.gl.glClearColor(0, 0, 0, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    camera.update()

    entities.foreach {
      case (transform, shape) =>
        shape.shape.getType match {
          case Shape.Type.Circle => {
            val circle = shape.shape.asInstanceOf[CircleShape]
            renderer.begin(ShapeType.Line)
            renderer.setProjectionMatrix(camera.combined)
            renderer.setColor(Color.CYAN)
            renderer.circle(transform.position.x, transform.position.y, circle.getRadius, 10)
            renderer.line(
              transform.position.x,
              transform.position.y,
              transform.position.x + math.cos(transform.rotation.toDouble).toFloat * -1f,
              transform.position.y + math.sin(transform.rotation.toDouble).toFloat * -1f
            )
            renderer.end()
          }
          case other => ()
        }
    }
  }
}
