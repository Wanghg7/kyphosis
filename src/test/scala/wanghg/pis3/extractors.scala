package wanghg.pis3

import org.junit.Test
import org.junit.Assert._

class ExtractorsTest {

  @Test
  def testEMail: Unit = {
    "harry@hogwarts.edu" match {
      case EMail(user, domain) =>
        assertEquals("harry", user)
        assertEquals("hogwarts.edu", domain)
      case _ =>
        assertTrue(false)
    }
    "harry AT hogwats.edu" match {
      case EMail(user, domain) => assertTrue(false)
      case _ =>
    }
  }

  @Test
  def testTwice: Unit = {
    "barbar" match {
      case Twice(x) => assertEquals("bar", x)
      case _ => assertTrue(false)
    }
    "barbara" match {
      case Twice(x) => assertTrue(false)
      case _ =>
    }
  }

  @Test
  def testUppercase: Unit = {
    "POTTER" match {
      case Uppercase() =>
      case _ => assertTrue(false)
    }
    "Potter" match {
      case Uppercase() => assertTrue(false)
      case _ =>
    }
  }

  @Test
  def testUpperTwiceUser: Unit = {
    "BILBIL@nowhere.com" match {
      case EMail(Twice(x@Uppercase()), domain) =>
        assertEquals("BIL", x)
        assertEquals("nowhere.com", domain)
      case _ =>
        assertTrue(false)
    }
    "BILBILB@nowhere.com" match {
      case EMail(Twice(Uppercase()), _) => assertTrue(false)
      case _ =>
    }
    "BILBIl@nowhere.com" match {
      case EMail(Twice(Uppercase()), _) => assertTrue(false)
      case _ =>
    }
    "BILBIL AT nowhere.com" match {
      case EMail(Twice(Uppercase()), _) => assertTrue(false)
      case _ =>
    }
  }

  @Test
  def testRegex: Unit = {
    val regex = "([^@]+)@([^@]+)".r
    "harry.potter@hogwarts.edu" match {
      case regex(name, domain) =>
        assertEquals("harry.potter", name)
        assertEquals("hogwarts.edu", domain)
      case _ =>
        assertTrue(false)
    }
    val regex2 = """([^@]+)@([^@.]+(\.([^@.]+))+)""".r
    "harry.potter@hogwarts.edu.cn" match {
      case regex2(name, domain, temp, top) =>
        assertEquals("harry.potter", name)
        assertEquals("hogwarts.edu.cn", domain)
        assertEquals(".cn", temp)
        assertEquals("cn", top)
      case _ =>
        assertTrue(false)
    }
    // DOES NOT WORK:
    //    "harry.potter@hogwarts.edu" match {
    //      case ("([^@]+)@([^@]+)".r)(name, domain) =>
    //        assertEquals("harry.potter", name)
    //        assertEquals("hogwarts.edu", domain)
    //      case _ =>
    //        assertTrue(false)
    //    }
  }

}
