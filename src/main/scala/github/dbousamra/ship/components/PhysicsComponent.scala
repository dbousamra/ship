package github.dbousamra.ship.components

import com.badlogic.gdx.physics.box2d.Body
import github.dbousamra.ecs.Component

case class PhysicsComponent(body: Body) extends Component
