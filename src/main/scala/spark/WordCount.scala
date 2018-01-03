package spark

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "D:\\softwares\\spark\\winutils")
    val conf = new SparkConf().setMaster("local").setAppName("WordCount")
    val sc  = new SparkContext(conf)
    val inputFile = "D:\\projects\\ScalaSpark\\for-program-testing\\wordcount-input.txt"
    val outputFile = "D:\\projects\\ScalaSpark\\for-program-testing\\wordcount-output"

    val input = sc.textFile(inputFile)
    //val words = input.flatMap(_.split(" "))
    //val counts = words.map(word=>(word,1)).reduceByKey(_*_)
    val words = input.flatMap(line => line.split(" "))
    val counts = words.map(word => (word, 1)).reduceByKey{case (x, y) => x + y}
    counts.saveAsTextFile(outputFile)

    sc.stop()
  }
}
