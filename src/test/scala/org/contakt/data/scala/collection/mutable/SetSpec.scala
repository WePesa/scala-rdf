package org.contakt.data.scala.collection.mutable

import org.scalatest._
import org.scalatest.matchers._

/**
 * Abstract base class for testing implementations of
 * Scala's mutable 'Set' trait.
 * 'T' is the element type of the Set implementation,
 * and 'TSet' is the class implementing 'Set[T]' that should be used.
 */
abstract class SetSpec[T, TSet <: MiniSet[T, TSet]] extends FlatSpec with BeforeAndAfter with ShouldMatchers with SetSpecSetup[T, TSet] {

  before {
    beforeSetup
  }

  after {
    afterSetup
  }

  "An empty set" should "have a working iterator" in {
    val emptySet = empty
  }

  "An empty set" should "be empty" in {
    val emptySet = empty
    assert(!emptySet.iterator.hasNext, s"empty set [${emptySet.getClass.getName}] was not empty as expected")
  }

}
