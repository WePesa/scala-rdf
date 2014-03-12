package org.contakt.data.scala.collection.mutable

import scala.collection.mutable.{Buffer,HashSet}
import scala.collection.immutable
import scala.reflect.ClassTag
import org.scalatest._
import org.scalatest.matchers._
import org.openrdf.model.impl.StatementImpl

/**
 * Abstract base class for testing implementations of
 * the 'UnorderedSequentialSet' trait.
 * 'T' is the element type of the Traversable implementation,
 * and 'TSet' is the class implementing 'UnorderedSequentialSet[T]' that should be used.
 */
abstract class UnorderedSequentialSetSpec[T: ClassTag, TSet <: UnorderedSequentialSet[T]] extends FlatSpec with BeforeAndAfter with ShouldMatchers with UnorderedSequentialSetSpecSetup[T, TSet] {

  before {
    beforeSetup()
  }

  after {
    afterSetup()
  }

  "An unordered sequential set" should "have a concrete type for an empty set" in {
    info(empty.getClass.getName)
  }

  it should "be empty if created empty" in {
    val emptySet = empty
    assert(emptySet.size == 0, "empty set was not empty as expected (size != 0)")
    assert(emptySet.isEmpty, "empty set was not empty as expected (!isEmpty)")
    assert(!emptySet.iterator.hasNext, "empty set was not empty as expected (iterator has next)")
  }

  it should "allow adding an element to an empty set" in {
    val newSet = empty
    assert(newSet.size == 0, s"new set was not empty as expected (size != 0): size = ${newSet.size}")
    assert(newSet.isEmpty, "new set was not empty as expected (!isEmpty)")
    newSet += newElem1
    assert(newSet.size == 1, s"new set did not contain 1 element as expected (size != 1): size = ${newSet.size}")
    assert(newSet.nonEmpty, "new set was not non-empty as expected (!nonEmpty)")
  }

  it should "allow adding multiple elements to an empty set" in {
    val elem1 = newElem1
    val elem2 = newElem2
    val elem3 = newElem3
    val newSet = empty
    assert(newSet.size == 0, s"new set was not empty as expected (size != 0): size = ${newSet.size}")
    newSet += (elem1, elem2)
    assert(newSet.size == 2, s"new set did not contain 2 elements as expected (size != 2): size = ${newSet.size}")
    val newSet2 = empty
    assert(newSet2.size == 0, s"new set (2) was not empty as expected (size != 0): size = ${newSet2.size}")
    newSet2 += (elem1, elem2, elem3)
    assert(newSet2.size == 3, s"new set (3) did not contain 3 elements as expected (size != 3): size = ${newSet2.size}")
  }

  it should "allow adding the contents of another set or iterator to it" in {
    val elem1 = newElem1
    val elem2 = newElem2
    val elem3 = newElem3
    val newSet = empty
    val newSet2 = empty
    newSet += elem1
    assert(newSet.size == 1, s"new set had unexpected size (size != 1): size = ${newSet.size}")
    newSet2 += (elem2, elem3)
    assert(newSet2.size == 2, s"new set 2 had unexpected size (size != 2): size = ${newSet2.size}")
    newSet ++= newSet2
    assert(newSet.size == 3, s"new set had unexpected size (size != 3): size = ${newSet.size}")
    val newSet3 = empty
    newSet3 += elem1
    assert(newSet3.size == 1, s"new set 3 had unexpected size (size != 1): size = ${newSet.size}")
    newSet3 ++= newSet2.iterator
    assert(newSet3.size == 3, s"new set 3 had unexpected size (size != 3): size = ${newSet.size}")
  }

  it should "contain an element that was already added to it" in {
    val newSet = empty
    newSet += newElem1
    assert(newSet.contains(newElem1), s"new set did not contain the element added to it (#1), as expected")
    newSet += newElem2
    assert(newSet.contains(newElem2), s"new set did not contain the element added to it (#2), as expected")
  }

  it should "not allow adding the same element twice to it" in {
    val elem = newElem1
    val newSet = empty
    assert(newSet.size == 0, s"new set was not empty as expected (size != 0): size = ${newSet.size}")
    newSet += elem
    assert(newSet.size == 1, s"new set did not contain 1 element as expected (size != 1 after 1st attempt): size = ${newSet.size}")
    newSet += elem
    assert(newSet.size == 1, s"new set did not contain 1 element as expected (size != 1 after 2nd attempt): size = ${newSet.size}")
    val newSet2 = empty
    assert(newSet2.size == 0, s"new set 2 was not empty as expected (size != 0): size = ${newSet2.size}")
    assert(newSet2.add(elem) && (newSet2.size == 1), s"new set 2 did not contain 1 element as expected (add failed or size != 1): size = ${newSet2.size}")
    assert(!newSet2.add(elem) && (newSet2.size == 1), s"new set 2 did not contain 1 element as expected (add succeeded or size != 1: size = ${newSet2.size}")
  }

  it should "allow removing the only element from it" in {
    val newSet = empty
    val elem = newElem1
    assert(newSet.size == 0, s"new set was not empty as expected (size != 0 before starting): size = ${newSet.size}")
    newSet += elem
    assert(newSet.size == 1, s"new set did not contain 1 element as expected: size = ${newSet.size}")
    newSet -= elem
    assert(newSet.size == 0, s"new set was not empty as expected (size != 0 after finishing): size = ${newSet.size}")
  }

  it should "allow removing the only elements from it" in {
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

  it should "ignore it if you try to remove a element when it is empty" in {
    val newSet = empty
    val elem = newElem1
    assert(newSet.size == 0, s"new set was not empty as expected (size != 0 before starting): size = ${newSet.size}")
    newSet -= elem
    assert(newSet.size == 0, s"new set was not empty as expected (size != 0 after finishing): size = ${newSet.size}")
    val removed = newSet.remove(elem)
    assert(!removed && (newSet.size == 0), s"new set was not empty as expected (remove succeeded or size != 0 after finishing): removed = $removed, size = ${newSet.size}")
  }

  it should "ignore it if you try to remove a element that is not in it" in {
    val newSet = empty
    val elem1 = newElem1
    val elem2 = newElem2
    assert(newSet.size == 0, s"new set was not empty as expected (size != 0 before starting): size = ${newSet.size}")
    newSet += elem1
    assert(newSet.size == 1, s"new set did not contain 1 element as expected (size != 1 after +=): size = ${newSet.size}")
    newSet -= elem2
    assert(newSet.size == 1, s"new set did not contain 1 element as expected (size != 0 after -=): size = ${newSet.size}")
    val removed2 = newSet.remove(elem2)
    assert(!removed2 && (newSet.size == 1), s"new set did not contain 1 element as expected (remove succeeded or size != 1): removed #2 = $removed2, size = ${newSet.size}")
    val removed1 = newSet.remove(elem1)
    assert(removed1 && (newSet.size == 0), s"new set was not empty as expected (remove failed or size != 0): removed #1 = $removed1, size = ${newSet.size}")
  }

  it should "allow clearing the elements" in {
    val elem1 = newElem1
    val elem2 = newElem2
    val newSet = empty
    assert(newSet.size == 0, s"new set was not empty as expected (size != 0 before starting): size = ${newSet.size}")
    newSet += (elem1, elem2)
    assert(newSet.size == 2, s"new set did not contain 2 elements as expected (size != 2): size = ${newSet.size}")
    newSet.clear
    assert(newSet.size == 0, s"new set was not empty as expected (size != 0 after clearing): size = ${newSet.size}")
  }

  it should "allow iterating through all of the elements" in {
    val controlSet = new HashSet[T]()
    val newSet = empty
    newSet += newElem1
    newSet += newElem2
    assert(newSet.size == 2, s"new set did not have the number of elements expected (size != 2 after adding elements): size = ${newSet.size}")
    assert(controlSet.size == 0, s"control set was not empty as expected (size != 0 before starting): size = ${controlSet.size}")
    for (elem <- newSet.iterator) { controlSet += elem }
    assert(controlSet.size == 2, s"control set did not have the number of elements expected (size != 2 after adding elements): size = ${controlSet.size}")
  }

  it should "have an 'apply' method that does a 'contains' check" in {
    val newSet = empty
    val elem1 = newElem1
    val elem2 = newElem2
    newSet += elem1
    assert(newSet(elem1) == newSet.contains(elem1), "apply/contains comparison failed for element that is in set")
    assert(newSet(elem2) == newSet.contains(elem2), "apply/contains comparison failed for element that is not in set")
  }

  it should "allow counting of elements based on a filter function" in {
    val newSet = empty
    val elem1 = newElem1
    val elem2 = newElem2
    val elem3 = newElem3
    newSet += (elem1, elem2)
    val count1 = newSet.count{_ == elem1}
    assert(count1 == 1, s"new set had wrong number of matching elements (count != 1): count = $count1")
    val count2 = newSet.count{_ == elem3}
    assert(count2 == 0, s"new set had wrong number of matching elements (count != 0): count = $count2")
  }

  it should "allow testing for matching elements based on a filter function" in {
    val newSet = empty
    val elem1 = newElem1
    val elem2 = newElem2
    val elem3 = newElem3
    newSet += (elem1, elem2)
    val exists1 = newSet.exists{_ == elem1}
    assert(exists1, "new set had no matching elements")
    val exists2 = newSet.exists{_ == elem3}
    assert(!exists2, "new set had unexpected matching elements")
  }

  it should "allow returning a subset based on matching a filter function" in {
    val newSet = empty
    val elem1 = newElem1
    val elem2 = newElem2
    val elem3 = newElem3
    newSet += (elem1, elem2)
    val newSet1 = newSet.filter{_ == elem1}
    assert(newSet1.size == 1, s"filtered set had wrong number of elements (size != 1): size = ${newSet1.size}")
    val newSet2 = newSet.filter{_ == elem3}
    assert(newSet2.size == 0, s"filtered set had wrong number of elements (size != 0): size = ${newSet2.size}")
  }

  it should "allow returning a subset based on not matching a filter function" in {
    val newSet = empty
    val elem1 = newElem1
    val elem2 = newElem2
    val elem3 = newElem3
    newSet += (elem1, elem2)
    val newSet1 = newSet.filterNot{_ == elem1}
    assert(newSet1.size == 1, s"filtered set had wrong number of elements (size != 1): size = ${newSet1.size}")
    val newSet2 = newSet.filterNot{_ == elem3}
    assert(newSet2.size == 2, s"filtered set had wrong number of elements (size != 2): size = ${newSet2.size}")
  }

  it should "allow find an element that matches a filter function, if any" in {
    val newSet = empty
    val elem1 = newElem1
    val elem2 = newElem2
    val elem3 = newElem3
    newSet += (elem1, elem2)
    newSet.find{_ == elem1} match {
      case Some(elem) => // ignore
      case None =>
        assert(false, "failed to find matching element")
    }
    newSet.find{_ == elem3} match {
      case None => // ignore
      case Some(elem) =>
        assert(false, "unexpectedly found matching element")
    }
  }

  it should "allow testing whether all elements match a filter function" in {
    val newSet = empty
    val elem1 = newElem1
    val elem2 = newElem2
    val elem3 = newElem3
    newSet += (elem1, elem2)
    assert(newSet.forall{elem => !(elem == elem3)}, "unexpectedly found a filter match")
    assert(!newSet.forall{elem => elem == elem1}, "unexpectedly found all elements matching a filter")
  }

  it should "allow iterating over all elements and applying a function" in {
    val newSet = empty
    val elem1 = newElem1
    val elem2 = newElem2
    val elem3 = newElem3
    newSet += (elem1, elem2)
    var count = 0
    newSet.foreach{elem => count += 1}
    assert(count == 2, s"count different to expected (count != 2): count = $count")
    val newSet2 = empty
    newSet2 += (elem1, elem2, elem3)
    count = 0
    newSet2.foreach{elem => count += 1}
    assert(count == 3, s"count different to expected (count != 3): count = $count")
  }

  it should "be able to iterate over elements and return them in fixed size groups" in {
    val newSet = empty
    val elem1 = newElem1
    val elem2 = newElem2
    val elem3 = newElem3
    newSet += (elem1, elem2, elem3)
    var count = 0
    for (group <- newSet.grouped(1)) {
      assert(group.size == 1, s"element group was the wrong size (size != 1): size = ${group.size}")
      count += 1
    }
    assert(count == 3, s"unexpected element group count (count != 3): count = $count")
    count = 0
    for (group <- newSet.grouped(2)) {
      assert((group.size >= 1) && (group.size <= 2), s"element group was the wrong size (size != 1 or 2): size = ${group.size}")
      count += 1
    }
    assert(count == 2, s"unexpected element group count (count != 2): count = $count")
    count = 0
    for (group <- newSet.grouped(3)) {
      assert(group.size == 3, s"element group was the wrong size (size != 3 for grouped(3)): size = ${group.size}")
      count += 1
    }
    assert(count == 1, s"unexpected element group count (count != 1 for grouped(3): count = $count")
    count = 0
    for (group <- newSet.grouped(4)) {
      assert(group.size == 3, s"element group was the wrong size (size != 3 for grouped(4)): size = ${group.size}")
      count += 1
    }
    assert(count == 1, s"unexpected element group count (count != 1 for grouped(4)): count = $count")
  }

  it should "be possible to create a string with all of the elements" in {
    val newSet = empty
    val elem1 = newElem1
    val elem2 = newElem2
    val elem3 = newElem3
    newSet += (elem1, elem2, elem3)
    val start = "{{{ "
    val sep = " ;;; "
    val end = " }}}"
    val stringValue = newSet.mkString(start, sep, end)
    val q = "\""
    assert(stringValue.startsWith(start), s"string value did not start with 'start' string (${q}$start${q}): $stringValue")
    assert(stringValue.endsWith(end), s"string value did not end with 'end' string (${q}$end${q}): $stringValue")
    assert(stringValue.contains(elem1.toString), s"string value did not contain 'elem1' string (${q}${q}${q}$elem1${q}${q}${q}): $stringValue")
    assert(stringValue.contains(elem2.toString), s"string value did not contain 'elem2' string (${q}${q}${q}$elem2${q}${q}${q}): $stringValue")
    assert(stringValue.contains(elem3.toString), s"string value did not contain 'elem3' string (${q}${q}${q}$elem3${q}${q}${q}): $stringValue")
    assert(stringValue.split(sep).size == 3, s"string did not split on the 'sep' string (${q}$sep${q}) into the correct number of elements (3): $stringValue")
  }

  it should "be possible to partition the elements of a set based on a predicate" in {
    val newSet = empty
    val elem1 = newElem1
    val elem2 = newElem2
    val elem3 = newElem3
    newSet += (elem1, elem2, elem3)
    val (partition1, partition2) = newSet.partition{_ == elem1}
    assert(partition1.size < newSet.size, s"partition 1 not smaller than new set as expected: size = ${partition1.size}")
    assert(partition2.size < newSet.size, s"partition 2 not smaller than new set as expected: size = ${partition2.size}")
    assert(partition1.size + partition2.size == newSet.size, "partitions did not add up to original new set: sum = ${partition1.size + partition2.size}")
  }

  it should "be able to remove all elements that don't match a predicate" in {
    val newSet = empty
    val elem1 = newElem1
    val elem2 = newElem2
    val elem3 = newElem3
    newSet += (elem1, elem2, elem3)
    assert(newSet.size == 3, s"new set was smaller than expected (size != 3): size = ${newSet.size}")   
    newSet.retain{_ == elem1}
    assert(newSet.size == 1, s"new set was not the expected size (size != 1): size = ${newSet.size}")   
  }

  it should "be possible to create an array of the elements" in {
    val newSet = empty
    val elem1 = newElem1
    val elem2 = newElem2
    val elem3 = newElem3
    newSet += (elem1, elem2, elem3)
    val newArray: Array[T] = newSet.toArray
    assert(newArray.size == newSet.size, "new array was not the same size as new set: newSet.size = ${newSet.size}, newArray.size = ${newArray.size}")
  }

  it should "be possible to create a buffer of the elements" in {
    val newSet = empty
    val elem1 = newElem1
    val elem2 = newElem2
    val elem3 = newElem3
    newSet += (elem1, elem2, elem3)
    val newBuffer: Buffer[T] = newSet.toBuffer
    assert(newBuffer.size == newSet.size, "new buffer was not the same size as new set: newSet.size = ${newSet.size}, newBuffer.size = ${newBuffer.size}")
  }

  it should "be possible to create an indexed sequence of the elements" in {
    val newSet = empty
    val elem1 = newElem1
    val elem2 = newElem2
    val elem3 = newElem3
    newSet += (elem1, elem2, elem3)
    val newSeq: IndexedSeq[T] = newSet.toIndexedSeq
    assert(newSeq.size == newSet.size, s"new sequence was not the same size as new set: newSet.size = ${newSet.size}, newSeq.size = ${newSeq.size}")
  }

  it should "be possible to create an iterable or iterator for the elements" in {
    val newSet = empty
    val elem1 = newElem1
    val elem2 = newElem2
    val elem3 = newElem3
    newSet += (elem1, elem2, elem3)
    val newIterable: Iterable[T] = newSet.toIterable
    var count1 = 0
    for (elem <- newIterable.iterator) count1 += 1
    assert(count1 == newSet.size, s"new iterable did not have the same number of elements as new set (size != 3): size = $count1")
    val newIterator: Iterator[T] = newSet.toIterator
    var count2 = 0
    for (elem <- newIterator) count2 += 1
    assert(count2 == newSet.size, s"new iterator did not have the same number of elements as new set (size != 3): size = $count2")
  }

  it should "be possible to create a list of the elements" in {
    val newSet = empty
    val elem1 = newElem1
    val elem2 = newElem2
    val elem3 = newElem3
    newSet += (elem1, elem2, elem3)
    val newList: List[T] = newSet.toList
    assert(newList.size == newSet.size, s"new list was not the same size as new set: newSet.size = ${newSet.size}, newList.size = ${newList.size}")
  }

  it should "be possible to create a sequence of the elements" in {
    val newSet = empty
    val elem1 = newElem1
    val elem2 = newElem2
    val elem3 = newElem3
    newSet += (elem1, elem2, elem3)
    val newSeq: Seq[T] = newSet.toSeq
    assert(newSeq.size == newSet.size, s"new sequence was not the same size as new set: newSet.size = ${newSet.size}, newSeq.size = ${newSeq.size}")
  }

  it should "be possible to create an immutable set of the elements" in {
    val newSet = empty
    val elem1 = newElem1
    val elem2 = newElem2
    val elem3 = newElem3
    newSet += (elem1, elem2, elem3)
    val newImmutableSet: immutable.Set[T] = newSet.toSet
    assert(newImmutableSet.size == newSet.size, s"new immutable set was not the same size as new set: newSet.size = ${newSet.size}, newImmutableSet.size = ${newImmutableSet.size}")
  }

  it should "be possible to create an immutable stream of the elements" in {
    val newSet = empty
    val elem1 = newElem1
    val elem2 = newElem2
    val elem3 = newElem3
    newSet += (elem1, elem2, elem3)
    val newStream: immutable.Stream[T] = newSet.toStream
    assert(newStream.size == newSet.size, s"new stream was not the same size as new set: newSet.size = ${newSet.size}, newStream.size = ${newStream.size}")
  }

  it should "be possible to traverse the elements" in {
    val newSet = empty
    val elem1 = newElem1
    val elem2 = newElem2
    val elem3 = newElem3
    newSet += (elem1, elem2, elem3)
    val newTraversable: Traversable[T] = newSet.toTraversable
    var count = 0
    for (elem <- newTraversable) count += 1
    assert(count == newSet.size, s"new traversable did not have the same number of elements as new set (size != 3): size = $count")
  }

  it should "be possible to create a vector of the elements" in {
    val newSet = empty
    val elem1 = newElem1
    val elem2 = newElem2
    val elem3 = newElem3
    newSet += (elem1, elem2, elem3)
    val newVector: Vector[T] = newSet.toVector
    assert(newVector.size == newSet.size, s"new vector was not the same size as new set: newSet.size = ${newSet.size}, newVector.size = ${newVector.size}")
  }

  it should "be possible to add or remove an element based on a boolean value" in {
    val newSet = empty
    val elem1 = newElem1
    val elem2 = newElem2
    val elem3 = newElem3
    newSet += (elem1, elem2)
    assert(newSet.size == 2, s"new set was not the expected size (before, size != 2): size = ${newSet.size}")
    newSet.update(elem3, true)
    assert(newSet.size == 3, s"new set was not the expected size (size != 3): size = ${newSet.size}")
    newSet.update(elem3, false)
    assert(newSet.size == 2, s"new set was not the expected size (after, size != 2): size = ${newSet.size}")
  }

}
