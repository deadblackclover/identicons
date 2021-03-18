package cipherdogs

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class IdenticonsSpec extends AnyFlatSpec with Matchers {
  "The Identicons object" should "calculate the correct md5 hash" in {
    val hash = Identicons.md5("test").map(0xFF & _).map { "%02x".format(_) }.foldLeft("") { _ + _ }
    hash shouldEqual "098f6bcd4621d373cade4e832627b4f6"
  }
}
