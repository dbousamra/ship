package github.dbousamra.objects

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.math.Vector2
import scala.collection.mutable.{ListBuffer => MList}
import com.badlogic.gdx.physics.box2d._

case class Player(x: Float, y: Float, radius: Float, world: World) {

  val projectiles = MList.empty[Projectile]

  val renderer: ShapeRenderer = new ShapeRenderer()

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

  def render(): Unit = {
    renderer.begin(ShapeType.Line)
    renderer.setColor(Color.CYAN)
    renderer.circle(body.getPosition.x, body.getPosition.y, circle.getRadius)
    renderer.line(
      body.getPosition.x,
      body.getPosition.y,
      body.getPosition.x + math.cos(body.getAngle.toDouble).toFloat * -20f,
      body.getPosition.y + math.sin(body.getAngle.toDouble).toFloat * -20f
    )
    renderer.end()
    projectiles.foreach(_.render)

  }

  def update(delta: Float): Unit = {
    handleInput(delta)
    body.setLinearVelocity(getDirection.scl(200))
  }

  def handleInput(delta: Float): Unit = {
    if (Gdx.input.isKeyPressed(Keys.LEFT)) {
      body.setAngularVelocity(4f)
    } else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
      body.setAngularVelocity(-4f)
    } else {
      body.setAngularVelocity(0f)
    }

    if (Gdx.input.isKeyPressed(Keys.SPACE)) {
      fire()
    }
  }

  def getDirection: Vector2 = {
    body.getWorldVector(new Vector2(-1, 0))
  }

  def fire(): Unit = {
    projectiles += Projectile(body.getPosition.x, body.getPosition.y, body.getAngle, world)
  }
}
