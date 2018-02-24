package github.dbousamra.objects

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d._

case class Projectile(x: Float, y: Float, direction: Vector2, world: World) {
  val renderer = new ShapeRenderer()
  val bodyDef = new BodyDef()
  bodyDef.`type` = BodyDef.BodyType.DynamicBody
  bodyDef.bullet = false
  bodyDef.position.set(x, y)
  val body = world.createBody(bodyDef)

  val circle = new CircleShape()
  circle.setRadius(4)

  val fixtureDef = new FixtureDef()
  fixtureDef.shape = circle
  fixtureDef.density = 0.0f
  // Important: A sensor shape collects contact information but never generates a collision response.
  fixtureDef.isSensor = true

  body.createFixture(fixtureDef)

  body.setLinearVelocity(direction.scl(120f))

  def render(): Unit = {
    renderer.begin(ShapeType.Line)
    renderer.setColor(Color.CYAN)
    renderer.circle(body.getPosition.x, body.getPosition.y, circle.getRadius)
    renderer.end()
  }

}
