package com.company.lab3

import java.util.NoSuchElementException

import com.company.interfaces.Cipher

class Playfair extends Cipher {

  val polibiyMatrix = Array(
    Array('c', 'i', 'p', 'h', 'e'),
    Array('r', 'a', 'b', 'd', 'f'),
    Array('g', 'k', 'l', 'm', 'n'),
    Array('o', 'q', 's', 't', 'u'),
    Array('v', 'w', 'x', 'y', 'z')
  )
  val UPPER_INDEX = 5
  val LOWER_INDEX = -1
  override def getName: String = "Playfair"

  //scala.util.Random.shuffle(alphabet.dropRight(0).grouped(4).toList)
  {
    println(polibiyMatrix)
  }

   def core(s: String, bound:Int, resetValue:Int, step:Int): String = {
    val length = s.length
    if (length % 2 != 0) {
      throw new RuntimeException("Word mus be even")
    }

    s.split("(?<=\\G.{2})")
      .map((s) => (s(0), s(1))).map {
      case (first, second) => {
        val firstPoint = findIndex(first)
        val secondPoint = findIndex(second)
        //horisontal
        if (firstPoint._2 == secondPoint._2) {
          var firstPointX = firstPoint._1 + step
          var secondPointX = secondPoint._1 + step

          if (firstPointX == bound) {
            firstPointX = resetValue
          }

          if (secondPointX == bound) {
            secondPointX = resetValue
          }
          ((firstPointX, firstPoint._2), (secondPointX, secondPoint._2))
        }
        //vertical
        else if (firstPoint._1 == secondPoint._1) {
          var firstPointY = firstPoint._2 + step
          var secondPointY = secondPoint._2 + step

          if (firstPointY  == bound) {
            firstPointY = resetValue
          }

          if (secondPointY  == bound) {
            secondPointY = resetValue
          }
          ((firstPoint._1, firstPointY), (secondPoint._1, secondPointY))
        } else {
          ((firstPoint._1, secondPoint._2), (secondPoint._1, firstPoint._2))
        }

      }
    }.map {
      case ((fX, fY), (sX, sY)) =>
      {
        Seq(polibiyMatrix(fX)(fY),polibiyMatrix(sX)(sY)).mkString
      }
    }.mkString

  }

  def findIndex(character: Char): (Int, Int) = {
    for (i <- polibiyMatrix.indices) {
      for (j <- polibiyMatrix(i).indices) {
        if (character.toLower.equals(polibiyMatrix(i)(j))) {
          return (i, j)
        }
      }
    }
    throw new NoSuchElementException
  }
  
  override def code(s: String): String = {
    core(s,UPPER_INDEX,LOWER_INDEX+1, 1)
  }

  override def decode(s: String): String = {
    core(s,LOWER_INDEX,UPPER_INDEX-1,-1)
  }
}
