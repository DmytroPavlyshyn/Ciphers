package com.company

object Alphabet {
  private  val lowerCaseAlphabet = 'a' to 'z'
  private val upperCaseAlphabt = lowerCaseAlphabet.map(_.toUpper)
  val alphabet = lowerCaseAlphabet.union(upperCaseAlphabt).toList
  val alphabetWithSpace:List[Char] = alphabet ++ List(' ')
  val mapWithAlphabeticalPosition = alphabetWithSpace.zipWithIndex.toMap

}
