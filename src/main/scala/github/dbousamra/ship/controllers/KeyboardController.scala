package github.dbousamra.ship.controllers

import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.InputProcessor

case class KeyboardController() extends InputProcessor {

  var left: Boolean = false
  var right: Boolean = false

  def touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = false

  def touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = false

  def scrolled(amount: Int): Boolean = false

  def keyTyped(character: Char): Boolean = false

  def touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean = false

  def mouseMoved(screenX: Int, screenY: Int): Boolean = false

  def keyUp(keycode: Int): Boolean = {
    keycode match {
      case Keys.LEFT => {
        left = false
        true
      }
      case Keys.RIGHT => {
        right = false
        true
      }
      case _ => {
        false
      }
    }
  }

  def keyDown(keycode: Int): Boolean = {
    keycode match {
      case Keys.LEFT => {
        left = true
        true
      }
      case Keys.RIGHT => {
        right = true
        true
      }
      case _ => {
        false
      }
    }
  }
}
