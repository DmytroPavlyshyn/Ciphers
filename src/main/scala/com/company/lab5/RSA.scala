package com.company.lab5

import java.util.Base64

import com.company.interfaces.Cipher
import lab5.RSAUtil
import RSAUtil._

class RSA extends Cipher{

  override def getName: String = "RSA"

  override def code(s: String): String = {
    Base64.getEncoder.encodeToString( encrypt(s, publicKey));
  }

  override def decode(s: String): String = {
    RSAUtil.decrypt(s, privateKey);
  }

}
