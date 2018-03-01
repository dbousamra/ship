package github.dbousamra.ship.components

import github.dbousamra.ecs._

case class PlayerControlComponent() extends Component {
  var turningLeft: Boolean = false
  var turningRight: Boolean = false
  var shooting: Boolean = false
}
