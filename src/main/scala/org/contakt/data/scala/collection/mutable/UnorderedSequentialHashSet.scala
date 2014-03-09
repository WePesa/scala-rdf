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

  /** adds all elements produced by an UnorderedSequentialSet to this mutable set. */
  def ++=(xs: UnorderedSequentialSet[A]) = {
    xs.iterator.foreach{add(_)}
    this
  }

	override def -(elem: A) = UnorderedSequentialHashSet.hashSetToUnorderedSequentialHashSet(super.-(elem))

	override def -(elem1: A, elem2: A, elems: A*) = UnorderedSequentialHashSet.hashSetToUnorderedSequentialHashSet(super.-(elem1, elem2, elems:_*))

  override def addString(b: scala.StringBuilder) = super.addString(b)
  override def addString(b: scala.StringBuilder, sep: String) = super.addString(b, sep)
  override def addString(b: scala.StringBuilder, start: String, sep: String, end: String) = super.addString(b, start, sep, end)

	override def apply(elem: A) = super.apply(elem)

	override def canEqual(that: Any) = super.canEqual(that)

	override def collect[B](pf: PartialFunction[A, B]) = UnorderedSequentialHashSet.hashSetToUnorderedSequentialHashSet(this.asInstanceOf[HashSet[A]].collect(pf))

	override def empty = UnorderedSequentialHashSet.hashSetToUnorderedSequentialHashSet(super.empty)

	override def filter(p: (A) => Boolean) = UnorderedSequentialHashSet.hashSetToUnorderedSequentialHashSet(super.filter(p))

	override def filterNot(p: (A) => Boolean) = UnorderedSequentialHashSet.hashSetToUnorderedSequentialHashSet(super.filterNot(p))

	override def grouped(size: Int) = UnorderedSequentialHashSet.hashSetIteratorToUnorderedSequentialHashSetIterator(super.grouped(size))

	override def map[B](f: (A) => B) = UnorderedSequentialHashSet.hashSetToUnorderedSequentialHashSet(this.asInstanceOf[HashSet[A]].map(f))

  def mapToSet[B](f: (A) => B) = map(f)

  override def mkString: String = super.mkString
  override def mkString(sep: String) = super.mkString(sep)
  override def mkString(start: String, sep: String, end: String) = super.mkString(start, sep, end)

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