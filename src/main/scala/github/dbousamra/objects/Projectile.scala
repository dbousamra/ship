package github.dbousamra.objects

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.{BodyDef, CircleShape, FixtureDef, World}

case class Projectile(x: Float, y: Float, angle: Float, world: World) {
  val renderer = new ShapeRenderer()
  val bodyDef = new BodyDef()
  bodyDef.`type` = BodyDef.BodyType.DynamicBody
  bodyDef.position.set(x, y)
  val body = world.createBody(bodyDef)

  val circle = new CircleShape()
  circle.setRadius(4)

  val fixtureDef = new FixtureDef()
  fixtureDef.shape = circle

  body.createFixture(fixtureDef)

  val trajectory = new Vector2(
    body.getPosition.x + math.cos(angle).toFloat ,
    body.getPosition.y + math.sin(angle).toFloat
  )
  body.setLinearVelocity(trajectory)

  def render(): Unit = {
    renderer.begin(ShapeType.Line)
    renderer.setColor(Color.CYAN)
    renderer.circle(body.getPosition.x, body.getPosition.y, circle.getRadius)
    renderer.end()
  }

}
