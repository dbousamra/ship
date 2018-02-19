package github.dbousamra

import com.badlogic.gdx._
import github.dbousamra.screens.PlayScreen

class Entry extends Game {

  override def create(): Unit = {
    setScreen(new PlayScreen(this))
  }

  override def dispose(): Unit = {}

  override def render(): Unit = {
    super.render();
  }
}
