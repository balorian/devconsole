package com.car

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Input.Keys
import scala.util.matching.Regex
import scala.collection.mutable.ListBuffer
import com.badlogic.gdx.math.MathUtils

object DevConsole {

}

class DevConsole extends InputProcessor {
  Gdx.input.setInputProcessor(this)

  var i = 0

  val commands = List(
    ("test", " _ ", { println("test") }),
    ("sum", "Int", (x: Int) => { i += x }),
    ("testore", "Int String", (x: Int, s: String) => { println("testore " + s + i) }))

  val history = new ListBuffer[String]()
  history.append("")

  val help = new ListBuffer[String]()

  val font = new BitmapFont(Gdx.files.classpath("default.fnt"), false)

  def render(batch: SpriteBatch, delta: Float) {
    font.draw(batch, "~> " + history(i), 10, 10 + font.getLineHeight)

    help.zipWithIndex foreach {
      case (e, i) => font.draw(batch, e, 10, 150 + font.getLineHeight * i)
    }
  }

  def autocomplete() {
    if (help.isEmpty) {
      commands foreach {
        case (name, helpText, _) if (name.contains(history(i))) => help.append(name + "|" + helpText)
        case _ =>
      }
    } else {
      history(i) =help(0).substring(0, help(0).indexOf("|"))
      help.clear
    }
  }

  def process() {
    help.clear
    
//    commands foreach {
//      case (name, _, f() => {}) if(history(i) == name) => f()
//      case _ => 
//    }

    if (i == history.size - 1) {
      history.append("")
    } else {
      history.remove(history.size - 1)
      history.append(history(i))
      history.append("")
    }

    i = history.size - 1
  }

  override def keyDown(keycode: Int): Boolean = false
  override def keyUp(keycode: Int): Boolean = {
    keycode match {
      case Keys.UP =>
        i = MathUtils.clamp(i - 1, 0, history.size - 1)
        true
      case Keys.DOWN =>
        i = MathUtils.clamp(i + 1, 0, history.size - 1)
        true
      case Keys.ENTER =>
        process(); true
      case Keys.TAB =>
        autocomplete(); true
      case Keys.BACKSPACE =>
        if(!history(i).isEmpty()){history(i) = history(i).substring(0, history(i).size - 1)}; true
      case _ => false
    }
  }
  override def keyTyped(char: Char) = {
    char match {
      case char if char.toString.matches("([a-zA-Z0-9 ])") => {
        history(i) += char; true
      }
      case _ => false
    }
  }
  override def touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int) = false
  override def touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int) = false
  override def touchDragged(screenX: Int, screenY: Int, pointer: Int) = false
  override def mouseMoved(screenX: Int, screenY: Int) = false
  override def scrolled(amount: Int) = false

}

class DevConsoleScreen extends AbstractScreen {
  val console = new DevConsole

  override def render(delta: Float) {
    super.render(delta)

    val batch = new SpriteBatch
    batch.begin()
    console.render(batch, delta)
    batch.end()
  }
}