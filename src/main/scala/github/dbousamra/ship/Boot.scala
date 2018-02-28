package github.dbousamra.ship

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.badlogic.gdx.backends.lwjgl.LwjglApplication

object Boot {

  def main(args: Array[String]): Unit = {
    val cfg: LwjglApplicationConfiguration = new LwjglApplicationConfiguration
    cfg.title = "ship"
    cfg.width = Constants.SCREEN_WIDTH.toInt
    cfg.height = Constants.SCREEN_HEIGHT.toInt
    cfg.resizable = false

    new LwjglApplication(new Entry, cfg)
    ()
  }

}
