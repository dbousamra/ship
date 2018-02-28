package github.dbousamra.ship

import com.badlogic.gdx._
import github.dbousamra.ship.screens.PlayScreen

class Entry extends Game {

  override def create(): Unit = {
    setScreen(new PlayScreen(this))
  }

  override def dispose(): Unit = {}

  override def render(): Unit = {
    super.render();
  }
}
