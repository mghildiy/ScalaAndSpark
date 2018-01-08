package spark

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.log4j.Logger
import org.apache.log4j.Level

object BasicOperations {

  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "D:\\softwares\\spark\\winutils")
    val conf = new SparkConf().setMaster("local").setAppName("WordCount")
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)

    val sc = new SparkContext(conf)
    //sc.setLogLevel("ERROR")

    //map:maps each element in RDD to a new element by applying passed function
    //squaring data elements
    val nums = sc.parallelize(List(1, 2, 3, 4))
    println("RDD before squaring:"+nums.collect().mkString(","))
    val squared = nums.map(x => x * x)
    println("New RDD after squaring:"+squared.collect().mkString(","))

    // finding lengths of strings
    val strs = sc.parallelize(List("Contains scala","Scala","No Scala","SCALA"))
    val strLength = strs.map(str => str.length)
    println("Lengths:"+strLength.collect().mkString(","))

    //flatmap: maps each element to multiple elements by applying passed function(this function must return an Iterator)
    //New RDD consists of elements from all of the iterators
    val lines = sc.parallelize(List("Messi is best","Neymar is not far behind","Ronaldo is overhyped"))
    println("RDD size before splitting:"+lines.collect().length)// 3 elements
    val words = lines.flatMap(line => line.split(" "))
    println("New RDD size after splitting:"+words.collect().length)
    println(words.collect().mkString(","))// 11 elements

    //pseudo set operations(RDDs are of same type)
    val inputRDD1 = sc.parallelize(List("coffee","coffee","panda","monkey","tea"))// 5 elments
    val inputRDD2 = sc.parallelize(List("coffee","monkey","kitty"))// 3 elements

    //distinct:creates a new RDD with unique elements
    val distinctRDD = inputRDD1.distinct()
    println("New RDD size after distinct:"+distinctRDD.collect().length)// 4
    println(distinctRDD.collect().mkString(","))// 4 elements

    // union:creates a new RDD consisting of data from both RDDs.Duplicates are retained
    val unionRDD = inputRDD1.union(inputRDD2)
    println("New RDD size after union:"+unionRDD.collect().length)// 8
    println(unionRDD.collect().mkString(","))// 8 elements

    //intersection:creates a new RDD consisting of elements common in both RDD.Duplicates(including from a single RDD) are removed
    val intersectionRDD = inputRDD1.intersection(inputRDD2)
    println("New RDD size after intersection:"+intersectionRDD.collect().length)// 2
    println(intersectionRDD.collect().mkString(","))// 2 elements

    //subtract:creates a new RDD consisting of data in one RDD but not in other
    val subtractRDD = inputRDD1.subtract(inputRDD2)
    println("New RDD size after subtract:"+subtractRDD.collect().length)// 4
    println(subtractRDD.collect().mkString(","))// 4 elements

    //cartesian:creates a new RDD consisting of all possible pairs
    val cartprodRDD = inputRDD1.cartesian(inputRDD2)
    println("New RDD size after cartesian product:"+cartprodRDD.collect().length)// 15
    println(cartprodRDD.collect().mkString(","))// 15 elements

    //filter:creates a new RDD consisting of those elements which pass the condition passed
    val filteredRDD = nums.filter(x => x > 1)
    println("New RDD size after filtering:"+filteredRDD.collect().length)// 3
    println(filteredRDD.collect().mkString(","))// 3 elements

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
    val numsToAdd = sc.parallelize(longList,2)
    val st = System.nanoTime()
    println(numsToAdd.reduce((x,y) => x+y))
    val et = System.nanoTime()
    println("Time spend:"+(et-st))
  }
}
