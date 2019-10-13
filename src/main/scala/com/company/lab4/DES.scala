package com.company.lab4

import java.nio.charset.Charset
import java.util.Base64

import com.company.interfaces.Cipher
import javax.crypto.SecretKey

class DES(key: SecretKey) extends Cipher{
  val encryptionCipher: javax.crypto.Cipher = javax.crypto.Cipher.getInstance("DES");
  val decryptionCipher: javax.crypto.Cipher = javax.crypto.Cipher.getInstance("DES");
  encryptionCipher.init(javax.crypto.Cipher.ENCRYPT_MODE, key)
  decryptionCipher.init(javax.crypto.Cipher.DECRYPT_MODE, key)

  override def getName: String = "DES"

  override def code(s: String): String = {
    val bytesUTF8 = s.getBytes(Charset.forName("UTF8"))
    val encoded = encryptionCipher.doFinal(bytesUTF8)
    Base64.getEncoder.encodeToString(encoded)
  }

  override def decode(s: String): String = {
    val decoded = Base64.getDecoder.decode(s)
    val bytesUTF8 = decryptionCipher.doFinal(decoded)
    new String(bytesUTF8,Charset.forName("UTF8"))
  }
}
