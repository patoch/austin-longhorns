package com.datastax.demo.austinlonghorns

import java.io.StringReader

import org.apache.lucene.analysis.en.EnglishAnalyzer
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute
import org.apache.lucene.util.Version

import scala.collection.mutable

/**
 * Created by patrick on 15/01/15.
 */
object TextUtils {

  val LuceneVersion = Version.LUCENE_4_10_3


  def getTerms(content: String): Seq[String] = {
    val tReader = new StringReader(content)
    val analyzer = new EnglishAnalyzer(LuceneVersion)
    val tStream = analyzer.tokenStream("contents", tReader)
    val term = tStream.addAttribute(classOf[CharTermAttribute])
    tStream.reset()

    val result = mutable.ArrayBuffer.empty[String]
    while(tStream.incrementToken()) {
      val termValue = term.toString
      if (!(termValue matches ".*[\\d\\.].*")) {
        result += term.toString
      }
    }
    result
  }

}
