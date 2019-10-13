package com.company.interfaces

trait Cipher {
  def getName:String
  def code(s:String):String
  def decode(s:String):String
}
