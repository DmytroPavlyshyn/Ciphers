package com.company.lab2

import com.company.Alphabet._
import com.company.interfaces.Cipher

class Gronselwald(key: Array[Int]) extends Cipher {
  //delete from key all letters indexes that are not in alphabet
  val validateKey = key.filter(v => mapWithAlphabeticalPosition.values.exists(_ == v))
  var indexValue: Int = 0

  override def getName: String = "Gronselwald"

  override def code(s: String): String = {
    try {
      s.map((_, getValidatedKeyItem())).map {
        case (character, index) => {
          val position = (mapWithAlphabeticalPosition(character) + index) % alphabetWithSpace.length
          val lookup = mapWithAlphabeticalPosition.map(_.swap)
          lookup(position)
        }
      }.mkString
    } finally {
      resetIndex()
    }
  }

  private def getValidatedKeyItem(): Int = {
    if (validateKey == null) {
      return -1
    }
    if (indexValue == validateKey.length) {
      indexValue = 0;
    }
    indexValue += 1
    indexValue - 1
  }

  private def resetIndex(): Unit = {
    indexValue = 0
  }

  override def decode(s: String): String = {
    try {
      val lookup = mapWithAlphabeticalPosition.map(_.swap)
      s.map(mapWithAlphabeticalPosition(_))
        .map(_ - getValidatedKeyItem())
        .map(index => {
          if (index < 0) {
             mapWithAlphabeticalPosition(alphabetWithSpace.last) + index + 1
          } else {
            index
          }

        })
        .map(lookup(_)).mkString
    } finally {
      resetIndex()
    }
  }
}
