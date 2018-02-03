package spark

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

object PairRDDDemo {

  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "D:\\softwares\\spark\\winutils")
    val conf = new SparkConf().setMaster("local").setAppName("WordCount")
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)

    val sc = new SparkContext(conf)

    //transformations
    //reduceByKey:creates a new pair RDD by combining all values for a key based on input function
    val inputRDD = sc.parallelize(List((1,2),(3,4),(3,6)))
    val keyValueRDD = inputRDD.map(x => (x._1,x._2))
    println("Input keyValueRDD:"+keyValueRDD.collect().mkString(","))

    val reducedByKeyRDD = keyValueRDD.reduceByKey((x,y) => x+y)
    println("New RDD after reducing:"+reducedByKeyRDD.collect().mkString(","))

    //groupByKey:creates a new pair RDD by grouping together all values for a key
    val groupedByKeyRDD = keyValueRDD.groupByKey()
    println("New RDD after grouping:"+groupedByKeyRDD.collect().mkString(","))

    //combineByKey:TODO

    //mapValues:creates a new pair RDD by applying input function to each value, key remains unchanged
    val mapValuedRDD = keyValueRDD.mapValues(x => x*x)
    println("New RDD after mapValues:"+mapValuedRDD.collect().mkString(","))

    //flatMapValues:creates a new pair RDD by applying input function(returns iterator) to every value, and then pair key to every element
    //returned
    val flatMapValueRDD = keyValueRDD.flatMapValues(x => (x to 5))
    println("New RDD after flatMapValues:"+flatMapValueRDD.collect().mkString(","))

    //keys:returns RDD consisting of just keys
    println("Only keys:"+keyValueRDD.keys.collect().mkString(","))

    //values:returns RDD consisting of just values
    println("Only values:"+keyValueRDD.values.collect().mkString(","))

    //sortByKey:returns RDD sorted by key
    println("Sorted by keys:"+keyValueRDD.sortByKey().collect().mkString(","))

    //transformations with 2 pair RDDs
    val inputRDDOther = sc.parallelize(List(((3,9))))
    val keyValueRDDOther = inputRDDOther.map(x => (x._1,x._2))

    //subtractByKey:creates a new pair RDD by removing from one those having key found in other
    val subtractByKeyRDD = keyValueRDD.subtractByKey(keyValueRDDOther)
    println("Size of new pair RDD created by subtractByKey:"+subtractByKeyRDD.count())// 1
    println(subtractByKeyRDD.collect().mkString(","))// 1 element

    //join:create a new pair RDD by inner joining together values from two RDDs having same key
    val joinRDD = keyValueRDD.join(keyValueRDDOther)
    println("Size of new pair RDD created by join:"+joinRDD.count())// 2
    println(joinRDD.collect().mkString(","))// 2 elements

    //rightOuterJoin:TODO

    //leftOuterJoin:TODO

    //filter
    val lines = sc.parallelize(Map("Messi" -> "Is God","Ronaldo" -> "Is overhyped").toSeq)
    val filteredRDD = lines.filter{case(key,value) => value.length < 10}
    println("Size of new pair RDD created by filter:"+filteredRDD.count())// 2
    println(filteredRDD.collect().mkString(","))// 2 elements

    //actions on pair RDD
    //countByKey:count number of elements for each key
    println("Map returned by countByKey:"+keyValueRDD.countByKey())

    //collectAsMap:returns Map rerpesentation of key-value pairs in RDD
    println("Map returned by collectAsMap:"+keyValueRDD.collectAsMap())

    //lookup(key):returns all values associated with the provided key
    println("All values returned by lookup(key):"+keyValueRDD.lookup(3))

  }

}
