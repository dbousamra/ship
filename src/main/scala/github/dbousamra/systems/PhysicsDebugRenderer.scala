package github.dbousamra.systems

import com.badlogic.gdx.graphics._
import com.badlogic.gdx.physics.box2d._
import github.dbousamra.ecs._

case class PhysicsDebugRenderer(world: World, camera: Camera) extends System {

  val renderer = new Box2DDebugRenderer()

  def process(es: List[Entity], delta: Int): Unit = {
    renderer.render(world, camera.projection)
  }

}
