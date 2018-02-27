package github.dbousamra.objects

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d._
import github.dbousamra.Constants
import github.dbousamra.screens.PlayScreen

case class Projectile(x: Float, y: Float, direction: Vector2, playScreen: PlayScreen) {
  var alive = true
  val renderer = new ShapeRenderer()
  val bodyDef = new BodyDef()
  bodyDef.`type` = BodyDef.BodyType.DynamicBody
  bodyDef.bullet = false
  bodyDef.position.set(x, y)
  val body = playScreen.physicsWorld.createBody(bodyDef)

  val circle = new CircleShape()
  circle.setRadius(4 / Constants.PPM)

  val fixtureDef = new FixtureDef()
  fixtureDef.shape = circle
  fixtureDef.density = 0.0f
  // Important: A sensor shape collects contact information but never generates a collision response.
  fixtureDef.isSensor = true

  body.createFixture(fixtureDef)

  body.setLinearVelocity(direction.scl(20f))

  def render(): Unit = {
    renderer.begin(ShapeType.Line)
    renderer.setProjectionMatrix(playScreen.camera.combined)
    renderer.setColor(Color.CYAN)
    renderer.circle(body.getPosition.x, body.getPosition.y, circle.getRadius, 10)
    renderer.end()
  }

  def update(delta: Float): Unit = {
    val tooFarRight = body.getPosition.x > playScreen.viewPort.getWorldWidth
    val tooFarLeft = body.getPosition.x < 0
    val tooFarUp = body.getPosition.y > playScreen.viewPort.getWorldHeight
    val tooFarDown = body.getPosition.y < 0

    if (tooFarRight || tooFarLeft || tooFarUp || tooFarDown) {
      die()
    }
  }

  def die(): Unit = {
    alive = false
  }
}
