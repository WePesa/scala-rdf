package org.contakt.data.scala.collection.mutable

import scala.collection.mutable.HashSet

/**
 * Trait for implementation specific setup of 'UnorderedSequentialSet' types,
 * in this case one based on Scala's HashSet.
 * 'T' is the element type of the 'UnorderedSequentialSet' implementation.
 */
trait UnorderedSequentialHashSetSpecSetup[T] extends UnorderedSequentialSetSpecSetup[T, UnorderedSequentialHashSet[T]] {

	/**
	 * Returns an empty set.
	 *
	 * @return an empty set.
	 */
	def empty = HashSet[T]()

}