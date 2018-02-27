package github.dbousamra.systems

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics._
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.physics.box2d._
import com.badlogic.gdx.utils.viewport.FitViewport
import github.dbousamra.Constants
import github.dbousamra.components._
import github.dbousamra.ecs._

case class RenderingSystem() extends System {
  val camera: Camera = new OrthographicCamera()
  val viewPort: FitViewport = new FitViewport(Constants.SCREEN_WIDTH / Constants.PPM, Constants.SCREEN_HEIGHT / Constants.PPM, camera)
  val renderer: ShapeRenderer = new ShapeRenderer()

  viewPort.update((Constants.SCREEN_WIDTH / Constants.PPM).toInt, (Constants.SCREEN_HEIGHT / Constants.PPM).toInt)
  camera.position.set(viewPort.getWorldWidth() / 2, viewPort.getWorldHeight() / 2, 0)

  def process(es: List[Entity], delta: Int): Unit = {
    val entities = es.flatMap { e =>
      for {
        transform <- e.get[TransformComponent]
        shape <- e.get[ShapeComponent]
      } yield (e, transform, shape)
    }

    camera.update()
    Gdx.gl.glClearColor(0, 0, 0, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

    entities.foreach {
      case (e, transform, shape) =>
        shape.shape.getType match {
          case Shape.Type.Circle => {
            val circle = shape.shape.asInstanceOf[CircleShape]
            renderer.begin(ShapeType.Line)
            renderer.setProjectionMatrix(camera.combined)
            renderer.setColor(Color.CYAN)
            renderer.circle(transform.position.x, transform.position.y, circle.getRadius, 10)
//            renderer.line(
//              transform.position.x,
//              transform.position.y,
//              transform.position.x + math.cos(transform.rotation.toDouble).toFloat * -1f,
//              transform.position.y + math.sin(transform.rotation.toDouble).toFloat * -1f
//            )
            renderer.end()
          }
          case other => ()
        }
    }
  }
}
