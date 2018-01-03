package spark

import org.apache.spark.{SparkConf, SparkContext}

object Parallelize {
  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "D:\\softwares\\spark\\winutils")
    val conf = new SparkConf().setMaster("local").setAppName("WordCount")
    val sc  = new SparkContext(conf)

    val players = sc.parallelize(List("Messi","Ronaldo","Hazard","Sanchez","Mbappe"))
  }
}
