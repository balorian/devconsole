package com.car

import com.badlogic.gdx.backends.lwjgl._

object Main {
  def main(args: Array[String]) {
    val cfg = new LwjglApplicationConfiguration()
    cfg.title = "devconsole"
    cfg.width = 640
    cfg.height = 480
    cfg.useGL20 = true
    cfg.forceExit = false
    new LwjglApplication(new DevConsoleGame(), cfg)
  }
}
