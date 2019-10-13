package com.company.lab3

import java.nio.ByteBuffer

import com.company.interfaces.Cipher

class Vernam(key:Array[Byte]) extends Cipher{
//  val text = "Hello".toCharArray
//  val key:Array[Char] = Array(1,2,3,4,5).map(_.toChar)
//  val a:Array[(Char,Char)] = text.zip(key)
//  println(a.mkString)
//  val b = a.map(item=>{
//    item._1^item._2
//  })
//  println(b.mkString)
//  val c = b.zip(key).map(item=>{
//    (item._1^item._2).toChar
//  })
//  println(s"result : ${c.mkString}")
  val keyBuffer =  ByteBuffer.wrap(key)
  def getCyclicKey(): Byte ={
    if(keyBuffer.position() == keyBuffer.capacity()){
      keyBuffer.rewind()
    }
    keyBuffer.get()
  }


  override def getName: String = "Vernam"

  def core(s:String): String ={
    try{
      val bytes =  s.getBytes.map(_^getCyclicKey()).map(_.toByte)
      new String(bytes)
    } finally {
      keyBuffer.rewind()
    }
  }

  override def code(s: String): String = {
    core(s)
  }

  override def decode(s: String): String = {
    core(s)
  }
}
