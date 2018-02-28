package github.dbousamra.ship.components

import github.dbousamra.ecs._

case class PlayerControlComponent() extends Component {
  var left: Boolean = false
  var right: Boolean = false
  var space: Boolean = false
}
