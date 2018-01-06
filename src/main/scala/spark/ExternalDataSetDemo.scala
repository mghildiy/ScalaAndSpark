package spark

import org.apache.spark.{SparkConf, SparkContext}

object ExternalDataSetDemo {

  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "D:\\softwares\\spark\\winutils")
    val conf = new SparkConf().setMaster("local").setAppName("WordCount")
    val sc  = new SparkContext(conf)
    sc.setLogLevel("ERROR")

    val inputFile = "D:\\projects\\ScalaSpark\\for-program-testing\\log.txt"
    val logRDD = sc.textFile(inputFile)
    println(logRDD.getClass)
    println(logRDD)
  }
}
