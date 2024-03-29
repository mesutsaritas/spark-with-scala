package com.saritasmesut

import org.apache.spark._
import org.apache.log4j._

object StarCount {

  def main(args: Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.ERROR)
    val sparkContext = new SparkContext("local[*]", "StarCounter")
    val linesOfFile = sparkContext.textFile("../ml-100k/u.data", 100)
    //val ratings = linesOfFile.map(line => line.toString().split("\t")(2))
    val ratings = linesOfFile.map(parseLineData)
    val result = ratings.countByValue()
    val sorted = result.toSeq.sortBy(_._1)
    sorted.foreach(print)

  }

  def parseLineData(line: String) {
    val fields = line.split("\t")
    val userId = fields(0).toInt
    val rating = fields(2).toInt
    (userId, rating)

  }

}