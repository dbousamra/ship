package github.dbousamra.ship

import com.badlogic.gdx.Gdx
import github.dbousamra.ecs.Engine
import com.badlogic.gdx.physics.box2d.{World => PhysicsWorld}
import github.dbousamra.ship.controllers.KeyboardController

case class GameUniverse(
  engine: Engine,
  physicsWorld: PhysicsWorld,
  keyboard: KeyboardController
) {
  val constants = Constants
  Gdx.input.setInputProcessor(keyboard)
}
