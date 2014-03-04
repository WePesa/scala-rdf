package org.contakt.data.scala.collection.mutable

import scala.collection.Traversable

/**
 * Trait for implementation specific setup of 'UnorderedSequentialSet' types,
 * which allows the same tests to work for different implementations.
 * 'T' is the element type of the Traversable implementation,
 * and 'TSet' is the class implementing 'UnorderedSequentialSet[T]' that should be used.
 */
trait UnorderedSequentialSetSpecSetup[T, TSet <: UnorderedSequentialSet[T]] {

  /**
   * Any 'before' items for tests.
   */
  def beforeSetup() = {}

  /**
   * Any 'after' items for tests.
   */
  def afterSetup() = {}

	/**
	 * Returns an empty set.
	 *
	 * @return an empty set.
	 */
	def empty: TSet

  /** Returns a new element of type T. */
  def newElem1: T

  /** Returns a different new element of type T. */
  def newElem2: T

  /** Returns another different new element of type T. */
  def newElem3: T

}