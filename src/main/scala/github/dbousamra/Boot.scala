package github.dbousamra

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration

object Boot extends App {
  val cfg: LwjglApplicationConfiguration = new LwjglApplicationConfiguration
  cfg.title = "Hello world"
  cfg.width = Constants.V_WIDTH.toInt
  cfg.height = Constants.V_HEIGHT.toInt

  new LwjglApplication(new Entry, cfg)
}
