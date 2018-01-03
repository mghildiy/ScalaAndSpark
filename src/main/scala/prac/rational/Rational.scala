package prac.rational

// parameters become part of primary constructor created by Scala compiler
class Rational(n:Int, d:Int) {
  //every code not part of method definition goes into primary constructor(in same order) created by Scala compiler
  require(d != 0)
  private val g = gcd(n.abs,d.abs)
  val numer:Int = n/g
  val denom:Int = d/g

  // every auxiliary constructor must call either primary constructor or other auxiliary constructor declared before it
  def this(n: Int) = this(n,1) //auxiliary constructor

  override def toString = numer +"/"+ denom

  private def gcd(a: Int,b: Int): Int =
    if(b == 0) a else gcd(b, a % b)

  def add(other: Rational) =
    new Rational(this.numer * other.denom + other.numer * this.denom, this.denom * other.denom)

  def +(other: Rational) =
    new Rational(this.numer * other.denom + other.numer * this.denom, this.denom * other.denom)

  def +(i: Int) =
    new Rational(this.numer + i * this.denom, this.denom)

  def -(other: Rational): Rational =
    new Rational(this.numer * other.denom - other.numer * this.denom, denom * other.denom)

  def -(i: Int): Rational =
    new Rational(this.numer - i * this.denom, this.denom)

  def *(other: Rational) =
    new Rational(this.numer * other.numer,this.denom * other.denom)

  def *(i: Int): Rational =
    new Rational(this.numer * i, this.denom)

  def /(other: Rational) =
    new Rational(this.numer * other.denom, this.denom * other.numer)

  def /(i: Int) =
    new Rational(this.numer, this.denom * i)

  def lessThan(other: Rational) =
    this.numer * other.denom < other.numer * this.denom

  def max(other: Rational) =
    if(this.lessThan(other)) other else this
}
