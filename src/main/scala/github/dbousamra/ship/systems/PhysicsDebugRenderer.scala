package github.dbousamra.ship.systems

import com.badlogic.gdx.graphics._
import com.badlogic.gdx.physics.box2d._
import github.dbousamra.ecs._
import github.dbousamra.ship.GameUniverse

case class PhysicsDebugRenderer(universe: GameUniverse, camera: Camera) extends System {

  val renderer = new Box2DDebugRenderer()

  def process(es: List[Entity], delta: Float): Unit = {
    renderer.render(universe.physicsWorld, camera.combined)
  }

}
