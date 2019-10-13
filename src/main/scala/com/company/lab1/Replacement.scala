package com.company.lab1

import com.company.Alphabet.alphabetWithSpace
import com.company.interfaces.Cipher

class Replacement extends Cipher {

  override def getName: String = "Replacement"

  val shuffled = scala.util.Random.shuffle(alphabetWithSpace.toList)

  val alphabetShuffled:Map[Char,Char] = alphabetWithSpace.zipWithIndex.map{
    case (ch,index)=>{
      ch->shuffled(index)
    }
  }.toMap

  override def code(s: String): String = {
    s.map((ch)=>{
      alphabetShuffled.getOrElse(ch,ch)
    }).mkString
  }

  override def decode(s: String): String = {
    val shuffledAlphabet = alphabetShuffled.map(_.swap)
    s.map((ch)=>{
      shuffledAlphabet.getOrElse(ch,ch)
    }).mkString
  }
}
