package spark

import org.apache.spark.{SparkConf, SparkContext}

object FilterTransformation {
  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "D:\\softwares\\spark\\winutils")
    val conf = new SparkConf().setMaster("local").setAppName("WordCount")
    val sc  = new SparkContext(conf)

    val inputFile = "D:\\projects\\ScalaSpark\\for-program-testing\\log.txt"
    val logRDD = sc.textFile(inputFile)
    val idRDD = logRDD.filter(line => line.contains("ID for link"))
    println("ID:"+idRDD.count())
    val localidRDD = logRDD.filter(line => line.contains("Local id for link"))
    println("Local id:"+localidRDD.count())
    val tileidRDD = logRDD.filter(line => line.contains("Tile id for link"))
    println("Tile id:"+tileidRDD.count())
    val levelidRDD = logRDD.filter(line => line.contains("Level id for link"))
    println("Level id:"+levelidRDD.count())
    val resultRDD = idRDD.union(localidRDD).union(localidRDD).union(levelidRDD)
    println("Total:"+resultRDD.count())
    resultRDD.take(16).foreach(println)

    val altRDD = logRDD.filter(line => line.contains("for link identified as FREEWAY_CLASS_ROAD_1:"))
    println("Total:"+altRDD.count())
    altRDD.take(16).foreach(println)
    //val outputFile = "D:\\projects\\ScalaSpark\\for-program-testing\\log-output"
    //resultRDD.saveAsTextFile(outputFile)

    sc.stop()
  }
}
