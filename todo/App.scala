package wanghg

import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.util.{AccumulatorV2, CollectionAccumulator}
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer

object App {

  def main(args: Array[String]): Unit = {
    // $ nc -lk 9999
    val conf = new SparkConf().setMaster("local[2]") setAppName ("NetworkWordCount")
    val ssc = new StreamingContext(conf, Seconds(5))
    val acc = new Top3(List.empty[(String, Int)])
    ssc.sparkContext.register(acc)
    val lines = ssc.socketTextStream("localhost", 9999)
    val words = lines.flatMap(_.split(" "))
    val pairs = words.map(word => (word, 1))
    val wordCounts = pairs.reduceByKey(_ + _)
    wordCounts.foreachRDD(rdd => {
      rdd.foreach(acc.add)
      println("--------------------------------------------------")
      acc.value.sortWith(_._2 > _._2).foreach(println)
    })
    ssc.start()
    ssc.awaitTermination()
  }

}

class Top3(l: List[(String, Int)]) extends AccumulatorV2[(String, Int), List[(String, Int)]] {

  private[this] var list = l

  override def isZero: Boolean = list.isEmpty

  override def copy(): AccumulatorV2[(String, Int), List[(String, Int)]] = new Top3(list)

  override def reset(): Unit = list = List.empty[(String, Int)]

  override def merge(other: AccumulatorV2[(String, Int), List[(String, Int)]]): Unit = {
    val l = other.value
    for (v <- l) add(v)
  }

  override def add(v: (String, Int)): Unit = {
    list = add(list, v, ListBuffer.empty[(String, Int)])
  }

  override def value: List[(String, Int)] = list

  private def add(list: List[(String, Int)], v: (String, Int), acc: ListBuffer[(String, Int)]): List[(String, Int)] = {
    val (newWord, deltaCount) = v
    list match {
      case Nil => acc.+=(v).toList
      case (word, count) :: xs if word == newWord => acc.+=((word, deltaCount + count)).++=(xs).toList
      case (word, count) :: xs if word != newWord => add(xs, v, acc += ((word, count)))
    }
  }
}

