package org.contakt.data.scala.collection.mutable

import org.openrdf.model.Statement

/**
 * Trait for implementation specific setup of tests of implementations of
 * 'UnorderedSequentialSet' based on Scala's built-in 'HashSet' class
 */
trait StatementUnorderedSequentialHashSetSpecSetup extends UnorderedSequentialHashSetSpecSetup[Statement] with StatementTestData {}
