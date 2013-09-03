package week05

import Funs._

object Vector{
  val ZERO = Vector(0, 0)
  val X = Vector(1, 0)
  val Y = Vector(0, 1)
}

case class Vector(var x: Float, var y: Float) {
  
  def +(that: Vector) = Vector(x+that.x, y+that.y)
  def *(scalar: Float) = Vector(x*scalar, y*scalar)
  def /(scalar: Float) = Vector(x/scalar, y/scalar)
  def -(that: Vector) = Vector(x-that.x, y-that.y)
  def unary_- = Vector(-x, -y)
  
  def +!(that: Vector) = {x += that.x; y += that.y; this}
  def -!(that: Vector) = {x -= that.x; y -= that.y; this}
  def *!(scalar: Float) = {x = x*scalar; y = y*scalar; this}
  def /!(scalar: Float) = {x = x/scalar; y = y/scalar; this}
  
  def dot(that: Vector) = x*that.x + y*that.y
  def len2 = x*x + y*y
  def len = sqrt(x*x + y*y).toFloat
  
  def nor = this / len
  def nord = this /! len
  def angle = {
	var angle = Math.atan2(y, x)
	if (angle < 0) angle += 2*Pi
	angle
  }

  def rotate(a:Float) = Vector((x*cos(a) - y*sin(a)).toFloat, (x*sin(a) + y*cos(a)).toFloat)
  def rotated(a: Float) = {
    val newX = x * cos(a) - y * sin(a)
    val newY = x * sin(a) + y * cos(a)
    x = newX.toFloat; y = newY.toFloat
    this
  }
  
  override def toString = "[" + x  + " " + y + "]"
}