package org.contakt.data.scala.collection.mutable

/**
 * Trait for implementation specific setup of Scala 'Set' types,
 * in this case for Scala's HashSet.
 * 'T' is the element type of the Set implementation,
 * and 'TSet' is the class implementing 'Set[T]' that should be used.
 */
trait StringHashSetSpecSetup extends HashSetSpecSetup[String] {

  /** Returns a new element of type String. */
  def newElem1: String = "foobar"

  /** Returns a different new element of type String. */
  def newElem2: String = "barfoo"

}