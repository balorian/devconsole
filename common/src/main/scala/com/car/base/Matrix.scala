package week05

import Funs._

object Matrix {
  val ID = Matrix()
  
  def rotM(a: Float) = Matrix(cos(a), -sin(a), 0, sin(a), cos(a), 0, 0, 0, 1)
  def scaleM(s: Float) = Matrix(s, 0 , 0, 0, s, 0, 0, 0, 1)
  def shearXM(k: Float) = Matrix(1, k, 0, 0, 1, 0, 0, 0, 1)
  def shearYM(k: Float) = Matrix(1, 0, 0, k, 1, 0, 0, 0, 1)
  def transM(x: Float, y: Float) = Matrix(1, 0, x, 0, 1, y, 0, 0, 1)
  
  def apply() = new Matrix
  def apply(i11: Float, i21: Float, i31: Float, i12: Float, i22: Float, i32: Float, i13: Float, i23: Float, i33: Float) = {
    val matrix = new Matrix
    
    matrix(0, 0) = i11; matrix(1, 0) = i21; matrix(2, 0) = i31
    matrix(0, 1) = i12; matrix(1, 1) = i22; matrix(2, 1) = i32
    matrix(0, 2) = i13; matrix(1, 2) = i23; matrix(2, 2) = i33
    matrix
  }
  
}

class Matrix {

  var matrix = Array[Float](1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f)
  
  def apply(i: Int, j: Int) = matrix(j + 3*i)
  def update(i: Int, j: Int, v: Float) = matrix(j + 3*i) = v
  
  def +(that: Matrix) = {
    val ret = Matrix()
    for (i <- 0 to 2; j <- 0 to 2) 
      ret(i, j) = this(i, j) + that(i, j)
    ret
  }
  def -(that: Matrix) = {
    val ret = Matrix()
    for (i <- 0 to 2; j <- 0 to 2) 
      ret(i, j) = this(i, j) - that(i, j)
    ret
  }
  def +!(that: Matrix) = {
    for(i <- 0 to 2; j <- 0 to 2)
      this(i, j) += that(i, j)
  }
  def -!(that: Matrix) = {
    for(i <- 0 to 2; j <- 0 to 2)
      this(i, j) -= that(i, j)
  }
  
  def *(scalar: Float) = {
    val ret = Matrix()
    for(i <- 0 until matrix.size) ret.matrix(i) = matrix(i) * scalar 
  }
  def *(vector: Vector) = Vector(vector.x * this(0, 0) + vector.y * this(0, 1) + this(0, 2), vector.x * this(1, 0) + vector.y * this(1, 1) + this(1, 2))
  def *(that: Matrix) = {
    val ret = Matrix(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)
    for(i <- 0 to 2; j <- 0 to 2; k <- 0 to 2)
      ret(i, j) += this(i, k) * that(k, j)
    ret
  }
  def *!(scalar: Float) = for(i <- 0 until matrix.size) matrix(i) *= scalar
  def *!(vector: Vector) = {
    val vx = vector.x * this(0, 0) + vector.y * this(0, 1) + this(0, 2)
    val vy = vector.x * this(1, 0) + vector.y * this(1, 1) + this(1, 2)
	vector.x = vx; vector.y = vy; vector
  }
  def *!(that: Matrix) = {
    val ret = this * that
    for(i <- 0 until matrix.size)
      matrix(i) = ret.matrix(i)
  }
  
  def det = this(0, 0) * this(1, 1) * this(2, 2) + this(0, 1) * this(1, 2) * this(2, 0) + this(0, 2) * this(1, 0) * this(2, 1) - 
		  	this(0, 0) * this(1, 2) * this(2, 1) - this(0, 1) * this(1, 0) * this(2, 2) - this(0, 2) * this(1, 1) * this(2, 0)
  def transpose = {
    val ret = Matrix()
    for (i <- 0 to 2; j <- 0 to 2) 
      ret(j, i) = this(i, j)
    ret
  }
  def transposed = {matrix = transpose.matrix; this}
  
  def inverse = {
    
  }
  
  override def equals(any: Any) = {
    def arreq[T](a: Array[T], b: Array[T]): Boolean = {
      var ret = true
      for(i <- 0 until a.size) ret = ret && (a(i) == b(i))
      ret
    }
    any match{
      case any: Matrix => arreq(this.matrix, any.matrix)
      case _ => false
    }
  }
  override def toString = "[" + this(0, 0) + " " + this(0, 1) + " " + this(0, 2) + " | " +
                                this(1, 0) + " " + this(1, 1) + " " + this(1, 2) + " | " +
                                this(2, 0) + " " + this(2, 1) + " " + this(2, 2) + "]"
}