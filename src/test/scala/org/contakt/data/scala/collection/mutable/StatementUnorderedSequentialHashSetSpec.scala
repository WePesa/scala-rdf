package org.contakt.data.scala.collection.mutable

import org.openrdf.model.Statement

/**
 * Test class to apply generic tests to an implementation of 'UnorderedSequentialSet'
 * using Scala's built-in 'HashSet' class.
 * This is really a test of the tests.
 */
class StatementUnorderedSequentialSetSpec extends UnorderedSequentialSetSpec[Statement, UnorderedSequentialHashSet[Statement]] with StatementUnorderedSequentialHashSetSpecSetup {}
