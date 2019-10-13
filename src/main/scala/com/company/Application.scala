package com.company

import com.company.interfaces.Cipher
import com.company.lab1.{CesarCipher, Rearrangement, Replacement}
import com.company.lab2.Polibiy
import com.company.lab3.{Playfair, Vernam}
import com.company.lab4.DES
import com.company.lab5.RSA
import javax.crypto.KeyGenerator

object Application extends App {

  val ciphers = Seq(
    new CesarCipher(2),
    new Replacement,
    new Rearrangement(Array(1,4,5,3)),
    new Polibiy,
    new Gronselwald(Array(1,3,4,5)),
    new Playfair,
    new Vernam(Array[Byte](1.toByte,2.toByte,3.toByte,4.toByte)),
    new DES(KeyGenerator.getInstance("DES").generateKey),
    new RSA
  )




  ciphers.foreach(format(_, args(0).replaceAll("\\s","")))

  def format(cipher:Cipher, input:String): Unit = {

    val encoded = cipher.code(input)
    println(s"---------${cipher.getName}-----------")
    println(s"Original: $input")
    println(s"Encoded : $encoded")
    val decoded = cipher.decode(encoded)
    println(s"Decoded : $decoded")

  }
}
