package com.company.lab1

import com.company.interfaces.Cipher

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class Rearrangement(seed: Array[Int]) extends Cipher {

  val test = ListBuffer[ListBuffer[Char]]()

  override def getName: String = "Rearrangement"

  override def code(s: String): String = {
    var (i, j) = (0, 0)
    for (ch <- s.toList) {
      if (j == 0) {
        test += ListBuffer.fill(seed.length)('-')
      }
      test(i)(j) = ch
      j += 1

      if (j == seed.length) {
        j = 0
        i += 1
      }

    }

    val dict = seed.zipWithIndex.map {
      case (seedIndex, index) => {
        (seedIndex,
          test.map(_ (index)).toList
        )
      }
    }.toMap

    val incrypted = new StringBuilder

    seed.sorted.foreach((s) => {
      incrypted ++= dict(s).mkString
    })
    incrypted.toString()
  }

  override def decode(s: String): String = {

    val grouped = s.grouped(test.length).toList

    val dict = seed.sorted.zipWithIndex.map {
      case (sortedSeedIndex, index) => {

        (sortedSeedIndex, grouped(index).toCharArray)
      }
    }.toMap
    val decrypted = new mutable.StringBuilder

    for (i <- dict(seed.head).indices) {
      seed.foreach(s => {
        decrypted ++= dict(s)(i).toString
      })
    }
    decrypted.toString

  }
}
