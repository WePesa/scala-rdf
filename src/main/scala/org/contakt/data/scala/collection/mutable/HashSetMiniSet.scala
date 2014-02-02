package org.contakt.data.scala.collection.mutable

import scala.collection.mutable.HashSet

/**
 * Temporary class that is necessary while we are using MiniSet.
 */
class HashSetMiniSet[T] extends HashSet[T] with MiniSet[T, HashSetMiniSet[T]] {

  override def empty = new HashSetMiniSet[T]()

}
