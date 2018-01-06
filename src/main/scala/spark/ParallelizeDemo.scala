package spark

import org.apache.spark.{SparkConf, SparkContext}

object ParallelizeDemo {
  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "D:\\softwares\\spark\\winutils")
    val conf = new SparkConf().setMaster("local").setAppName("WordCount")
    val sc  = new SparkContext(conf)
    sc.setLogLevel("ERROR")

    val players = sc.parallelize(List("Messi","Ronaldo","Hazard","Sanchez","Mbappe"))
    println(players.getClass)
    println(players)

    val nums = sc.parallelize(List(1,2,3))
    println(nums.getClass)
    println(nums)

    val teamRank = sc.parallelize(Seq("Barca"->1,"Chelsea"->2))
    println(teamRank.getClass)
    println(teamRank)
  }
}
