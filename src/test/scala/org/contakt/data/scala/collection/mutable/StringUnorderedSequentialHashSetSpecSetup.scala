package org.contakt.data.scala.collection.mutable

/**
 * Trait for implementation specific setup of tests of implementations of
 * 'UnorderedSequentialSet' based on Scala's built-in 'HashSet' class
 */
trait StringUnorderedSequentialHashSetSpecSetup extends UnorderedSequentialHashSetSpecSetup[String] {

	/**
	 * Returns an empty set.
	 *
	 * @return an empty set.
	 */
	// def empty = HashSet[T]()

  /** Returns a new element of type String. */
  // def newElem1: String = "foobar"

  /** Returns a different new element of type String. */
  // def newElem2: String = "barfoo"

}