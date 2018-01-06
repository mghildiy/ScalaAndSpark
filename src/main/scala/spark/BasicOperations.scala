package spark

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object BasicOperations {

  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "D:\\softwares\\spark\\winutils")
    val conf = new SparkConf().setMaster("local").setAppName("WordCount")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")

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

    //key-value pairs
    val pairs = lines.map(x => (x.split(" ")(0), x))
    println(pairs.count())
    println(pairs.first())
    println(pairs)

    val numMapRDD = sc.parallelize(Seq((1,2), (3,4), (3,5)))
    println(numMapRDD.count())
    val reducedRDD = numMapRDD.reduceByKey((x,y) => x*y)
    reducedRDD.collect().foreach(println)

    val groupedRDD = numMapRDD.groupByKey()
    groupedRDD.collect().foreach(println)

    val longList = 1 to 1000000 toList
    val numsToAdd = sc.parallelize(longList,30)
    val st = System.nanoTime()
    println(numsToAdd.reduce((x,y) => x+y))
    val et = System.nanoTime()
    println("Time spend:"+(et-st))
  }
}
