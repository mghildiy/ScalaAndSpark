package spark

import java.util.logging.{Level, Logger}



object BasicOperations {

  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "D:\\softwares\\spark\\winutils")
    val conf = new SparkConf().setMaster("local").setAppName("WordCount")
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)

    val sc = new SparkContext(conf)
    //sc.setLogLevel("ERROR")

    //transformations
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
    println("New RDD size after splitting:"+words.collect().length)// 11
    println(words.collect().mkString(","))// 11 elements

    //pseudo set operations(RDDs are of same type)
    val inputRDD1 = sc.parallelize(List("coffee","coffee","panda","monkey","tea"))// 5 elements
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
    val filteredRDD1 = strs.filter(str => str.contains("Scala"))
    println("New RDD size after filtering:"+filteredRDD1.collect().length)// 3
    println(filteredRDD1.collect().mkString(","))// 3 elements


    //actions
    //reduce:computes a value using an input function which takes two input elements of the type in RDD and returns a new element of same type
    println("Product of all the numbers:"+nums.reduce((x,y) => x*y))// 24

    //fold:same as reduce, except it needs a starting value called 'zero value' to be used for initial call
    val employeeRDD = sc.parallelize(List(("Jack",1000),("John",1200),("Jill",655)))
    val zeroValue = ("dummy",0)
    val maxSalaryEmployee = employeeRDD.fold(zeroValue)((acc,emp2) => {if(acc._2 < emp2._2) emp2 else acc})
    println("Employee with maximum salary is"+maxSalaryEmployee)

    //aggregate:this operation enables us to return type different from type in RDD.Zero value is needed as in fold, a function to combine
    //elements in RDD with accumulator, and another function which merges two accumulators from two different nodes in the cluster
    val result = nums.aggregate((0.0,0.0))((acc,value) => (acc._1 + value,acc._2 + 1),(acc1,acc2) => (acc1._1 + acc2._1,acc1._2 + acc2._2))
    println("Sum,number:"+result._1+","+result._2)
    println("Average is:"+result._1/result._2)

    //count:returns number of elements in RDD
    println("Number of elements:"+nums.count())// 4

    //countByValue:returns tuples of element and no of times it occurs in RDD
    val strRDD = sc.parallelize(List("One","Two","Three","One","Four",4,"Three"))
    val resultMap = strRDD.countByValue()
    println(resultMap)// 5 entries in map:One -> 2, Two -> 1, Four -> 1, Three -> 2, 4 -> 1


    //collect,take(n):return elements from RDD
    println("Collect:"+nums.collect().mkString(","))
    println("Take:"+nums.take(3).mkString(","))
    //foreach(func):returns nothing, just applies input function on each element in RDD
  }
}
