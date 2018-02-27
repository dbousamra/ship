package github.dbousamra.components

import com.badlogic.gdx.math.Vector2
import github.dbousamra.ecs.Component

case class TransformComponent(
  var position: Vector2,
  var rotation: Float
) extends Component
