package org.contakt.data.scala.collection.mutable

import org.scalatest._
import org.scalatest.matchers._
import org.openrdf.model.impl.StatementImpl

/**
 * Abstract base class for testing implementations of
 * Scala's mutable 'Set' trait.
 * 'T' is the element type of the Set implementation,
 * and 'TSet' is the class implementing 'Set[T]' that should be used.
 */
abstract class SetSpec[T, TSet <: MiniSet[T, TSet]] extends FlatSpec with BeforeAndAfter with ShouldMatchers with SetSpecSetup[T, TSet] {

  before {
    beforeSetup()
  }

  after {
    afterSetup()
  }

  "An empty set" should "have a concrete type" in {
    info(empty.getClass.getName)
  }

  it should "be empty" in {
    val emptySet = empty
    assert(emptySet.size == 0, s"empty set was not empty as expected (size != 0)")
    assert(!emptySet.iterator.hasNext, s"empty set was not empty as expected (iterator has next)")
  }

  it should "be possible to add an element to an empty set" in {
    val newSet = empty
    assert(newSet.size == 0, s"new set was not empty as expected (size != 0): size = ${newSet.size}")
    newSet += newElem1
    assert(newSet.size == 1, s"new set did not contain 1 element as expected (size != 1): size = ${newSet.size}")
  }

  it should "not be possible to add the same element twice to a set" in {
    val newSet = empty
    val elem = newElem1
    assert(newSet.size == 0, s"new set was not empty as expected (size != 0): size = ${newSet.size}")
    newSet += elem
    assert(newSet.size == 1, s"new set did not contain 1 element as expected (size != 1 after 1st attempt): size = ${newSet.size}")
    newSet += elem
    assert(newSet.size == 1, s"new set did not contain 1 element as expected (size != 1 after 2nd attempt): size = ${newSet.size}")
  }

  it should "be possible to remove the only element from a set" in {
    val newSet = empty
    val elem = newElem1
    assert(newSet.size == 0, s"new set was not empty as expected (size != 0 before starting): size = ${newSet.size}")
    newSet += elem
    assert(newSet.size == 1, s"new set did not contain 1 element as expected (size != 1 after 1st attempt): size = ${newSet.size}")
    newSet -= elem
    assert(newSet.size == 0, s"new set was not empty as expected (size != 0 after finishing): size = ${newSet.size}")
  }

  it should "not matter if you try to remove a element that is not a member from an empty set" in {
    val newSet = empty
    val elem = newElem1
    assert(newSet.size == 0, s"new set was not empty as expected (size != 0 before starting): size = ${newSet.size}")
    newSet -= elem
    assert(newSet.size == 0, s"new set was not empty as expected (size != 0 after finishing): size = ${newSet.size}")
  }

  it should "not matter if you try to remove a element that is not a member from a non-empty set" in {
    val newSet = empty
    val elem1 = newElem1
    val elem2 = newElem2
    assert(newSet.size == 0, s"new set was not empty as expected (size != 0 before starting): size = ${newSet.size}")
    newSet += elem1
    assert(newSet.size == 1, s"new set did not contain 1 element as expected (size != 1 after +=): size = ${newSet.size}")
    newSet -= elem2
    assert(newSet.size == 1, s"new set did not contain 1 element as expected (size != 0 after -=): size = ${newSet.size}")
  }

}
