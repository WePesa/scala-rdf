package org.contakt.data.scala.collection.mutable

import scala.collection._
import scala.collection.mutable.HashSet

/**
 * HashSet-based implementation of the 'UnorderedSequentialSet' trait.
 * Provided so that implementations of 'UnorderedSequentialSet' can be tested
 * against a built-in set implementation, to check that the behaviour
 * is the same in tests.
 */
class UnorderedSequentialHashSet[A] extends HashSet[A] with UnorderedSequentialSet[A] {

	override def +(elem: A) = UnorderedSequentialHashSet.hashSetToUnorderedSequentialHashSet(super.+(elem))

	override def +(elem1: A, elem2: A, elems: A*) = UnorderedSequentialHashSet.hashSetToUnorderedSequentialHashSet(super.+(elem1, elem2, elems:_*))

	override def -(elem: A) = UnorderedSequentialHashSet.hashSetToUnorderedSequentialHashSet(super.-(elem))

	override def -(elem1: A, elem2: A, elems: A*) = UnorderedSequentialHashSet.hashSetToUnorderedSequentialHashSet(super.-(elem1, elem2, elems:_*))

	override def collect[B](pf: PartialFunction[A, B]) = UnorderedSequentialHashSet.hashSetToUnorderedSequentialHashSet(super.collect(pf))

	override def empty = UnorderedSequentialHashSet.hashSetToUnorderedSequentialHashSet(super.empty)

	override def filter(p: (A) => Boolean) = UnorderedSequentialHashSet.hashSetToUnorderedSequentialHashSet(super.filter(p))

	override def filterNot(p: (A) => Boolean) = UnorderedSequentialHashSet.hashSetToUnorderedSequentialHashSet(super.filterNot(p))

	override def grouped(size: Int) = UnorderedSequentialHashSet.hashSetIteratorToUnorderedSequentialHashSetIterator(super.grouped(size))

	override def map[B](f: (A) => B) = UnorderedSequentialHashSet.hashSetToUnorderedSequentialHashSet(super.map(f))

	override def partition(p: (A) => Boolean) = {
		val result = super.partition(p)
		(
			UnorderedSequentialHashSet.hashSetToUnorderedSequentialHashSet(result._1),
			UnorderedSequentialHashSet.hashSetToUnorderedSequentialHashSet(result._2)
		)
	}

	override def span(p: (A) => Boolean) = {
		val result = super.span(p)
		(
			UnorderedSequentialHashSet.hashSetToUnorderedSequentialHashSet(result._1),
			UnorderedSequentialHashSet.hashSetToUnorderedSequentialHashSet(result._2)
		)
	}

}

object UnorderedSequentialHashSet {

	implicit def hashSetToUnorderedSequentialHashSet[A](hs: HashSet[A]): UnorderedSequentialHashSet[A] = {
		val result = new UnorderedSequentialHashSet[A]()
		result ++= hs
		result
	}

	implicit def hashSetIteratorToUnorderedSequentialHashSetIterator[A](hsi: Iterator[HashSet[A]]): Iterator[UnorderedSequentialHashSet[A]] = {
		new Iterator[UnorderedSequentialHashSet[A]]() {
			def hasNext = hsi.hasNext
			def next = hsi.next
		}
	}

}