package com.car

import com.badlogic.gdx.Game
import com.badlogic.gdx.Screen
import com.badlogic.gdx.Gdx.gl
import com.badlogic.gdx.Gdx.graphics
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.graphics.GL10
import com.badlogic.gdx.graphics.OrthographicCamera

class DevConsoleGame extends Game {
  override def create() {
	  setScreen(new DevConsoleScreen)
  }
}

class AbstractScreen extends Screen {
  var paused = false
  var visible = false
  var justDraw = false

  def show() {
    visible = true;
  }

  def hide() {
    visible = false;
  }

  def pause() {
    paused = true;
  }

  def resume() {
    paused = false;
  }

  def into() {

  }

  def outOf() {

  }

  def dispose(): Unit = {
  }

  def render(delta: Float): Unit = {
    gl.glClearColor(0, 0, 0, 1);
    gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
    
    
  }

  def resize(width: Int, height: Int): Unit = {
  }
}