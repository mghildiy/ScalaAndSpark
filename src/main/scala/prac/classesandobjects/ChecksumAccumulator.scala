package prac.classesandobjects

import scala.collection.mutable.Map

//companion class for ChecksumAccumulator object
class ChecksumAccumulator {
  private var sum = 0;

  def add(b:Byte): Unit = {
    sum += b
  }

  def checkSum() = {
    ~(sum & 0xFF) + 1
  }

}

//companion object for ChecksumAccumulator class
object ChecksumAccumulator {
  private val cache = Map[String,Int]()

  def calculate(s:String): Int = {
    if(cache.contains(s)){
      cache(s)
    }else{
      val acc = new ChecksumAccumulator
      for(c<-s){
        acc.add(c.toByte)
      }
      val cs = acc.checkSum()
      cache += (s->cs)
      cs
    }
  }
}