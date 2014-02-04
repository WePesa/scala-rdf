package org.contakt.data.scala.collection.mutable

/**
 * Temporary trait that implements a subset of the methods from 'scala.mutable.collection.Set'.
 * Will not be required once all 'Set' methods are implemented.
 * 'T' is the element type of the Set implementation,
 * and 'TSet' is the class implementing 'Set[T]' that should be used.
 */
trait MiniSet[T, TSet] {

  /** Adds a single element to the set. */
  def +=(elem: T): TSet

  /** Removes a single element from this mutable set. */
  def -=(elem: T): TSet

  /** Tests if some element is contained in this set. */
  def contains(elem: T): Boolean

  def iterator: Iterator[T]

  def empty: TSet

  /** The size of this mutable set. */
  def size: Int

}
