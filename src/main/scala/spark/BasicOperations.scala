package spark

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object BasicOperations {

  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "D:\\softwares\\spark\\winutils")
    val conf = new SparkConf().setMaster("local").setAppName("WordCount")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")
    //val spark = SparkSession.builder.getOrCreate()
    //spark.sparkContext.setLogLevel("ERROR")

    //map
    val nums = sc.parallelize(List(1, 2, 3, 4))
    val squared = nums.map(x => x * x)
    println(squared.collect().mkString(","))

    //transformations
    //flatmap
    val lines = sc.parallelize(List("Messi is best","Neymar is not far behind","Ronaldo is overhyped"))
    val words = lines.flatMap(line => line.split(" "))
    println(words.first())
    println(words.count())
    println(words.collect().mkString(","))

    //actions
    //reduce
    println(nums.reduce((x,y) => x*y))
  }
}
