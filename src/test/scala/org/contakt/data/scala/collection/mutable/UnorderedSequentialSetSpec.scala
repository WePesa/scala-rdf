package org.contakt.data.scala.collection.mutable

import scala.collection.mutable.HashSet
import org.scalatest._
import org.scalatest.matchers._
import org.openrdf.model.impl.StatementImpl

/**
 * Abstract base class for testing implementations of
 * the 'UnorderedSequentialSet' trait.
 * 'T' is the element type of the Traversable implementation,
 * and 'TSet' is the class implementing 'UnorderedSequentialSet[T]' that should be used.
 */
abstract class UnorderedSequentialSetSpec[T, TSet <: UnorderedSequentialSet[T]] extends FlatSpec with BeforeAndAfter with ShouldMatchers with UnorderedSequentialSetSpecSetup[T, TSet] {

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
    assert(emptySet.size == 0, "empty set was not empty as expected (size != 0)")
    assert(emptySet.isEmpty, "empty set was not empty as expected (!isEmpty)")
    assert(!emptySet.iterator.hasNext, "empty set was not empty as expected (iterator has next)")
  }

  it should "be possible to add an element to an empty set" in {
    val newSet = empty
    assert(newSet.size == 0, s"new set was not empty as expected (size != 0): size = ${newSet.size}")
    newSet += newElem1
    assert(newSet.size == 1, s"new set did not contain 1 element as expected (size != 1): size = ${newSet.size}")
  }

  it should "be possible to add multiple elements to an empty set" in {
    val newSet = empty
    assert(newSet.size == 0, s"new set was not empty as expected (size != 0): size = ${newSet.size}")
    newSet += (newElem1, newElem2)
    assert(newSet.size == 2, s"new set did not contain 2 elements as expected (size != 2): size = ${newSet.size}")
    val newSet2 = empty
    assert(newSet2.size == 0, s"new set (2) was not empty as expected (size != 0): size = ${newSet2.size}")
    newSet2 += (newElem1, newElem2, newElem3)
    assert(newSet2.size == 3, s"new set (3) did not contain 3 elements as expected (size != 3): size = ${newSet2.size}")
  }

  it should "contain an element that was added to it" in {
    val newSet = empty
    newSet += newElem1
    assert(newSet.contains(newElem1), s"new set did not contain the element added to it (#1), as expected")
    newSet += newElem2
    assert(newSet.contains(newElem2), s"new set did not contain the element added to it (#2), as expected")
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
    assert(newSet.size == 1, s"new set did not contain 1 element as expected: size = ${newSet.size}")
    newSet -= elem
    assert(newSet.size == 0, s"new set was not empty as expected (size != 0 after finishing): size = ${newSet.size}")
  }

  it should "be possible to remove the only elements from a set" in {
    val elem1 = newElem1
    val elem2 = newElem2
    val elem3 = newElem3
    val newSet = empty
    assert(newSet.size == 0, s"new set was not empty as expected (size != 0 before starting): size = ${newSet.size}")
    newSet += (elem1, elem2)
    assert(newSet.size == 2, s"new set did not contain 2 elements as expected: size = ${newSet.size}")
    newSet -= (elem1, elem2)
    assert(newSet.size == 0, s"new set was not empty as expected (size != 0 after finishing): size = ${newSet.size}")
    val newSet2 = empty
    assert(newSet2.size == 0, s"new set 2 was not empty as expected (size != 0 before starting): size = ${newSet2.size}")
    newSet2 += (elem1, elem2, elem3)
    assert(newSet2.size == 3, s"new set 2 did not contain 3 elements as expected: size = ${newSet2.size}")
    newSet2 -= (elem1, elem2, elem3)
    assert(newSet2.size == 0, s"new set 2 was not empty as expected (size != 0 after finishing): size = ${newSet2.size}")
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

  it should "be possible to iterate through all of the elements" in {
    val controlSet = new HashSet[T]()
    val newSet = empty
    newSet += newElem1
    newSet += newElem2
    assert(newSet.size == 2, s"new set did not have the number of elements expected (size != 2 after adding elements): size = ${newSet.size}")
    assert(controlSet.size == 0, s"control set was not empty as expected (size != 0 before starting): size = ${controlSet.size}")
    for (elem <- newSet.iterator) { controlSet += elem }
    assert(controlSet.size == 2, s"control set did not have the number of elements expected (size != 2 after adding elements): size = ${controlSet.size}")
  }

}
