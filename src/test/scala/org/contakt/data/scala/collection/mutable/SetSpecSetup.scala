package org.contakt.data.scala.collection.mutable

import scala.collection.mutable.Set

/**
 * Trait for implementation specific setup of Scala 'Set' types,
 * which allows the same tests to work for different implementations.
 * 'T' is the element type of the Set implementation,
 * and 'TSet' is the class implementing 'Set[T]' that should be used.
 */
trait SetSpecSetup[T, TSet <: MiniSet[T, TSet]] {

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

}