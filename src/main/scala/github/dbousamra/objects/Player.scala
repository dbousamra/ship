package github.dbousamra.objects

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d._

case class Player(x: Float, y: Float, radius: Float, world: World) {

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
      body.getPosition.x + math.cos(body.getAngle).toFloat * -20,
      body.getPosition.y + math.sin(body.getAngle).toFloat * -20
    )
    renderer.end()
  }

  def update(delta: Float): Unit = {
    handleInput(delta)
  }

  def handleInput(delta: Float): Unit = {
    if (Gdx.input.isKeyPressed(Keys.LEFT)) {
      body.setAngularVelocity(4f)
    } else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
      body.setAngularVelocity(-4f)
    } else {
      body.setAngularVelocity(0f)
    }
    body.setLinearVelocity(getDirection.scl(200))
  }

  def getDirection: Vector2 = {
    body.getWorldVector(new Vector2(-1, 0))
  }
}
