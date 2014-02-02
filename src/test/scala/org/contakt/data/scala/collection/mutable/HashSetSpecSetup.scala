package org.contakt.data.scala.collection.mutable

/**
 * Trait for implementation specific setup of Scala 'Set' types,
 * in this case for Scala's HashSet.
 * 'T' is the element type of the Set implementation,
 * and 'TSet' is the class implementing 'Set[T]' that should be used.
 */
trait HashSetSpecSetup[T] extends SetSpecSetup[T, HashSetMiniSet[T]] {

	/**
	 * Returns an empty set.
	 *
	 * @return an empty set.
	 */
	def empty = new HashSetMiniSet[T]()

}