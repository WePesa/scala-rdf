package org.contakt.data.scala.collection.mutable

/**
 * Test class to apply generic tests to an implementation of 'UnorderedSequentialSet'
 * using Scala's built-in 'HashSet' class.
 * This is really a test of the tests.
 */
class StringUnorderedSequentialSetSpec extends UnorderedSequentialSetSpec[String, UnorderedSequentialHashSet[String]] with StringUnorderedSequentialHashSetSpecSetup {}
