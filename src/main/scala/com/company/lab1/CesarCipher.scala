package com.company.lab1

import com.company.Alphabet.alphabetWithSpace
import com.company.interfaces.Cipher

class CesarCipher(shift: Int) extends Cipher {

  override def getName: String = "Cesar"

  override def code(s: String): String = {
    core(s, shift)
  }

  override def decode(s: String): String = {
    core(s, -shift)
  }

  private def core(message: String, shift2: Int): String = {
    val shift = (shift2 + alphabetWithSpace.size) % alphabetWithSpace.size
    message.map(ch => {
      val index = alphabetWithSpace.indexOf(ch)
      if (index == -1) {
        ch
      } else {
        alphabetWithSpace((index + shift) % alphabetWithSpace.size)
      }
    }
    )
  }

}