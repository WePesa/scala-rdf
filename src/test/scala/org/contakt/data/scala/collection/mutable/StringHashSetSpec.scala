package org.contakt.data.scala.collection.mutable

/**
 * Test class to apply generic Set tests to Scala's HashSet class.
 * This is really a test of the tests.
 */
class StringHashSetSpec extends SetSpec[String, HashSetMiniSet[String]] with StringHashSetSpecSetup {}
