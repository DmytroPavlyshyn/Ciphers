package com.company.lab2

import java.util

import com.company.interfaces.Cipher

class Polibiy extends Cipher {

  val polibiyKeyTable = new PolibiyKeyTable()
  import polibiyKeyTable._
  override def getName: String = "Polibiy"

  override def code(s: String): String = {
    val arrayMessage = s.toLowerCase.toCharArray
    arrayMessage.map(find).map(charToEncrypt).mkString
  }

  override def decode(s: String): String = {
    val arrayMessage = s.toCharArray
    arrayMessage.map(find).map(charToDecrypt).mkString
  }

  def getInfo: String = {
    val info: StringBuilder = new StringBuilder
    var i: Int = 0
    while (i < PolibiyKeyTable.polibiyKeyTable.length) {
      info.append(util.Arrays.toString(PolibiyKeyTable.polibiyKeyTable(i))).append("\n")
      i += 1;
    }
    "Encryption key: \n" + info
  }
}
