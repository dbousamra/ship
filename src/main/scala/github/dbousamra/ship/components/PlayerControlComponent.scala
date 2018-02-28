package github.dbousamra.ship.components

import github.dbousamra.ecs._

case class PlayerControlComponent(
  var left: Boolean,
  var right: Boolean
) extends Component
