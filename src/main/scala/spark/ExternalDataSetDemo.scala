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
    println(logRDD.first())

    val lineLengths = logRDD.map(s => s.length)
    println(lineLengths.getClass)
    println(lineLengths)
    //println(lineLengths.count())
    //lineLengths.foreach(println)
    //lineLengths.take(100).foreach(println)
    println(lineLengths.reduce((x,y) => x+y))

    //key value tests
    val crapRDD = sc.textFile("D:\\projects\\ScalaSpark\\for-program-testing\\crap.txt")
    println(crapRDD)
    println(crapRDD.getClass())
    val lineToOne = crapRDD.map(line => (line,1))
    println(lineToOne)
    println(lineToOne.getClass())
    //print(lineTOOne.first())
    val lineCounts = lineToOne.reduceByKey((x,y) => x+y)
    lineCounts.sortByKey()
    lineCounts.collect().foreach(println)
  }
}
