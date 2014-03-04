package org.contakt.data.scala.collection.mutable

import scala.collection._
import scala.collection.mutable.Buffer

/**
 * Scala trait for sets that do not guarantee
 * to return results in a repeatable order,
 * and do guarantee to provide support for parallel operations.
 * Implementations may be based on databases, so assumptions
 * should be avoided around the size of result sets, and around
 * whether operations can be done by storing all intermediate data
 * in-memory (or not).
 * This method signature is intended to be a subset
 * of Scala's 'mutable.Set' method signature.
 */

 trait UnorderedSequentialSet[A] {

 	// **** Set[A] methods

	/** Adds a single element to the set. */
	def +=(elem: A): UnorderedSequentialSet[A] 
  
	/** Removes a single element from this mutable set. */
	def -=(elem: A): UnorderedSequentialSet[A] 
  
	/** Tests if some element is contained in this set. */
	def contains(elem: A): Boolean 
  
	def iterator: Iterator[A] 

	/** Computes the intersection between this set and another set. */
	// def &(that: GenSet[A]): Set[A] 

	/** The difference of this set and another set. */
	// def &~(that: GenSet[A]): Set[A] 
  
	/** Creates a new set consisting of all the elements of this set and two or more specified elements. */
	def +(elem1: A, elem2: A, elems: A*): UnorderedSequentialSet[A] 
  
	/** Creates a new set consisting of all the elements of this set and elem. */
	def +(elem: A): UnorderedSequentialSet[A] 
  
	/** Creates a new set consisting of all the elements of this set and those provided by the specified traversable object. */
	// def ++(xs: GenTraversableOnce[A]): Set[A] 
  
	/** [use case] Returns a new mutable set containing the elements from the left hand operand followed by the elements from the right hand operand. */
	// def ++[B](that: GenTraversableOnce[B]): Set[B] 
  
	/** As with ++, returns a new collection containing the elements from the left operand followed by the elements from the right operand. */
	// def ++:[B >: A, That](that: collection.Traversable[B])(implicit bf: CanBuildFrom[Set[A], B, That]): That 
  
	/** [use case] As with ++, returns a new collection containing the elements from the left operand followed by the elements from the right operand. */
	// def ++:[B](that: TraversableOnce[B]): Set[B] 
  
	/** adds all elements produced by a TraversableOnce to this mutable set. */
	// def ++=(xs: TraversableOnce[A]): Set.this.type 
  
	/** adds two or more elements to this mutable set. */
	def +=(elem1: A, elem2: A, elems: A*): UnorderedSequentialSet[A] 
  
	/** Creates a new set consisting of all the elements of this set except the two or more specified elements. */
	def -(elem1: A, elem2: A, elems: A*): UnorderedSequentialSet[A] 
  
	/** Creates a new set consisting of all the elements of this set except elem. */
	def -(elem: A): UnorderedSequentialSet[A] 
  
	/** Creates a new set consisting of all the elements of this set except those provided by the specified traversable object. */
	// def --(xs: GenTraversableOnce[A]): Set[A] 
  
	/** Removes all elements produced by an iterator from this mutable set. */
	// def --=(xs: TraversableOnce[A]): Set.this.type 
  
	/** Removes two or more elements from this mutable set. */
	def -=(elem1: A, elem2: A, elems: A*): UnorderedSequentialSet[A] 
  
	/** Applies a binary operator to a start value and all elements of this mutable set. */
	def /:[B](z: B)(op: (B, A) => B): B 
  
	/** Applies a binary operator to all elements of this mutable set and a start value. */
	def :\[B](z: B)(op: (A, B) => B): B 
  
	/** Send a message to this scriptable object. */
	// def <<(cmd: Message[A]): Unit 
  
	/**
	 * Adds an element to this mutable set.
	 *
	 * @param elem the element to be added
	 * @return true if the element was not yet present in the set, false otherwise.
	 */
	def add(elem: A): Boolean 
  
	/** Appends all elements of this mutable set to a string builder. */
	def addString(b: scala.StringBuilder): scala.StringBuilder 
  
	/** Appends all elements of this mutable set to a string builder using a separator string. */
	def addString(b: scala.StringBuilder, sep: String): scala.StringBuilder 
  
	/** Appends all elements of this mutable set to a string builder using start, end, and separator strings. */
	def addString(b: scala.StringBuilder, start: String, sep: String, end: String): scala.StringBuilder 
  
	/** Aggregates the results of applying an operator to subsequent elements. */
	def aggregate[B](z: B)(seqop: (B, A) => B, combop: (B, B) => B): B 
  
	/** Composes two instances of Function1 in a new Function1, with this function applied first. */
	// def andThen[A](g: (Boolean) => A): (A) => A 
  
	/** Tests if some element is contained in this set. */
	def apply(elem: A): Boolean = contains(elem)
  
	// def asParIterable: ParIterable[A] 
  
	// def asParSeq: ParSeq[A] 
  
	/** Method called from equality methods, so that user-defined subclasses can refuse to be equal to other collections of the same kind. */
	def canEqual(that: Any) = {
		that match {
			case uss: UnorderedSequentialSet[A] => true
			case _ => false
		}
	}
  
	/** Removes all elements from the set. */
	def clear(): Unit 
  
	/** [use case] Builds a new collection by applying a partial function to all elements of this mutable set on which the function is defined. */
	def collect[B](pf: PartialFunction[A, B]): UnorderedSequentialSet[B] 
  
	/** Finds the first element of the mutable set for which the given partial function is defined, and applies the partial function to it. */
	def collectFirst[B](pf: PartialFunction[A, B]): Option[B] 
  
	/** The factory companion object that builds instances of class mutable.Set. */
	// def companion: GenericCompanion[Set] 
  
	/** Composes two instances of Function1 in a new Function1, with this function applied last. */
	// def compose[A](g: (A) => A): (A) => Boolean 
  
	/** [use case] Copies elements of this mutable set to an array. */
	// def copyToArray(xs: Array[A], start: Int, len: Int): Unit 
  
	/** [use case] Copies values of this mutable set to an array. */
	// def copyToArray(xs: Array[A]): Unit 
  
	/** [use case] Copies values of this mutable set to an array. */
	// def copyToArray(xs: Array[A], start: Int): Unit 
  
	/** Copies all elements of this mutable set to a buffer. */
	def copyToBuffer[B >: A](dest: Buffer[B]): Unit 
  
	/** Counts the number of elements in the mutable set which satisfy a predicate. */
	def count(p: (A) => Boolean): Int 
  
	/** Computes the difference of this set and another set. */
	// def diff(that: GenSet[A]): Set[A] 
  
	/** Selects all elements except first n ones. */
	// def drop(n: Int): Set[A] 
  
	/** Selects all elements except last n ones. */
	// def dropRight(n: Int): Set[A] 
  
	/** Drops longest prefix of elements that satisfy a predicate. */
	// def dropWhile(p: (A) => Boolean): Set[A] 
  
	def empty: UnorderedSequentialSet[A]
  
	/** Compares this set with another object for equality. */
	def equals(that: Any): Boolean 
  
	/** Tests whether a predicate holds for some of the elements of this mutable set. */
	def exists(p: (A) => Boolean): Boolean 
  
	/** Selects all elements of this mutable set which satisfy a predicate. */
	def filter(p: (A) => Boolean): UnorderedSequentialSet[A]
  
	/** Selects all elements of this mutable set which do not satisfy a predicate. */
	def filterNot(p: (A) => Boolean): UnorderedSequentialSet[A]
  
	/** Finds the first element of the mutable set satisfying a predicate, if any. */
	def find(p: (A) => Boolean): Option[A] 
  
	/** [use case] Builds a new collection by applying a function to all elements of this mutable set and using the elements of the resulting collections. */
	// def flatMap[B](f: (A) => GenTraversableOnce[B]): Set[B] 
  
	/** [use case] Converts this mutable set of traversable collections into a mutable set formed by the elements of these traversable collections. */
	// def flatten[B]: UnorderedSequentialSet[B]
  
	/** Folds the elements of this mutable set using the specified associative binary operator. */
	def fold[A1 >: A](z: A1)(op: (A1, A1) => A1): A1 
  
	/** Applies a binary operator to a start value and all elements of this mutable set. */
	def foldLeft[B](z: B)(op: (B, A) => B): B 
  
	/** Applies a binary operator to all elements of this mutable set and a start value. */
	def foldRight[B](z: B)(op: (A, B) => B): B 
  
	/** Tests whether a predicate holds for all elements of this mutable set. */
	def forall(p: (A) => Boolean): Boolean 
  
	/** [use case] Applies a function f to all elements of this mutable set. */
	def foreach[B](f: (A) => B): Unit 
  
	/** The generic builder that builds instances of mutable.Set at arbitrary element types. */
	// def genericBuilder[B]: Builder[B, Set[B]] 
  
	/** Partitions this mutable set into a map of mutable sets according to some discriminator function. */
	// def groupBy[K](f: (A) => K): immutable.Map[K, UnorderedSequentialSet[A]] 
  
	/** Partitions elements in fixed size mutable sets. */
	def grouped(size: Int): Iterator[UnorderedSequentialSet[A]] 
  
	/** Tests whether this mutable set is known to have a finite size. */
	def hasDefiniteSize: Boolean 
  
	/** The hashCode method for reference types. */
	def hashCode(): Int 
  
	/** Selects the first element of this mutable set. */
	// def head: A 
  
	/** Optionally selects the first element. */
	// def headOption: Option[A] 
  
	// def ifParSeq[R](isbody: (ParSeq[A]) => R): (TraversableOps[A])#Otherwise[R] 
  
	/** Selects all elements except the last. */
	// def init: Set[A] 
  
	/** Iterates over the inits of this mutable set. */
	// def inits: Iterator[Set[A]] 
  
	/** Computes the intersection between this set and another set. */
	// def intersect(that: GenSet[A]): Set[A] 
  
	/** Tests if this set is empty. */
	def isEmpty: Boolean 
  
	def isParIterable = false 
  
	def isParSeq = false 
  
	def isParallel = false
  
	/** Tests whether this mutable set can be repeatedly traversed. */
	def isTraversableAgain: Boolean 
  
	/** Selects the last element. */
	// def last: A 
  
	/** Optionally selects the last element. */
	// def lastOption: Option[A] 
  
	/** [use case] Builds a new collection by applying a function to all elements of this mutable set. */
	def map[B](f: (A) => B): UnorderedSequentialSet[B]
  
	/** Creates a new builder by applying a transformation function to the results of this builder. */
	//  mapResult[NewTo](f: (Set[A]) => NewTo): Builder[A, NewTo] 
  
	/** [use case] Finds the largest element. */
	// def max: A 
  
	// def maxBy[B](f: (A) => B)(implicit cmp: Ordering[B]): A 
  
	/** [use case] Finds the smallest element. */
	// def min: A 
  
	// def minBy[B](f: (A) => B)(implicit cmp: Ordering[B]): A 
  
	/** Displays all elements of this mutable set in a string. */
	def mkString: String 
  
	/** Displays all elements of this mutable set in a string using a separator string. */
	def mkString(sep: String): String 
  
	/** Displays all elements of this mutable set in a string using start, end, and separator strings. */
	def mkString(start: String, sep: String, end: String): String 
  
	/** Tests whether the mutable set is not empty. */
	def nonEmpty: Boolean 
  
	/** Returns a parallel implementation of this collection. */
	// def par: ParSet[A] 
  
	/** Partitions this mutable set in two mutable sets according to a predicate. */
	def partition(p: (A) => Boolean): (UnorderedSequentialSet[A], UnorderedSequentialSet[A]) 
  
	/** [use case] Multiplies up the elements of this collection. */
	// def product: A 
  
	/** Reduces the elements of this mutable set using the specified associative binary operator. */
	def reduce[A1 >: A](op: (A1, A1) => A1): A1 
  
	/** Applies a binary operator to all elements of this mutable set. */
	def reduceLeft[B >: A](op: (B, A) => B): B 
  
	/** Optionally applies a binary operator to all elements of this mutable set. */
	def reduceLeftOption[B >: A](op: (B, A) => B): Option[B] 
  
	/** Reduces the elements of this mutable set, if any, using the specified associative binary operator. */
	def reduceOption[A1 >: A](op: (A1, A1) => A1): Option[A1] 
  
	/** Applies a binary operator to all elements of this mutable set. */
	def reduceRight[B >: A](op: (A, B) => B): B 
  
	/** Optionally applies a binary operator to all elements of this mutable set. */
	def reduceRightOption[B >: A](op: (A, B) => B): Option[B] 
  
	/** Removes an element from this set. */
	def remove(elem: A): Boolean 
  
	/** The collection of type mutable set underlying this TraversableLike object. */
	// def repr: Set[A] 
  
	/** The result when this set is used as a builder */
	// def result(): Set[A] 
  
	/** Removes all elements from the set for which do not satisfy a predicate. */
	def retain(p: (A) => Boolean): Unit 
  
	/** [use case] Checks if the other iterable collection contains the same elements in the same order as this mutable set. */
	// def sameElements(that: GenIterable[A]): Boolean 
  
	/** Computes a prefix scan of the elements of the collection. */
	// def scan[B >: A, That](z: B)(op: (B, B) => B)(implicit cbf: CanBuildFrom[Set[A], B, That]): That 
  
	/** Produces a collection containing cumulative results of applying the operator going left to right. */
	// def scanLeft[B, That](z: B)(op: (B, A) => B)(implicit bf: CanBuildFrom[Set[A], B, That]): That 
  
	/** Produces a collection containing cumulative results of applying the operator going right to left. */
	// def scanRight[B, That](z: B)(op: (A, B) => B)(implicit bf: CanBuildFrom[Set[A], B, That]): That 
  
	/** A version of this collection with all of the operations implemented sequentially. */
	// def seq: Set[A] 
  
	/** The size of this mutable set. */
	def size: Int 
  
	/** Gives a hint that one expects the result of this builder to have the same size as the given collection, plus some delta. */
	// def sizeHint(coll: TraversableLike[_, _], delta: Int): Unit 
  
	/** Gives a hint that one expects the result of this builder to have the same size as the given collection, plus some delta. */
	// def sizeHint(coll: TraversableLike[_, _]): Unit 
  
	/** Gives a hint how many elements are expected to be added when the next result is called. */
	// def sizeHint(size: Int): Unit 
  
	/** Gives a hint how many elements are expected to be added when the next result is called, together with an upper bound given by the size of some other collection. */
	// def sizeHintBounded(size: Int, boundingColl: TraversableLike[_, _]): Unit 
  
	/** Selects an interval of elements. */
	// def slice(from: Int, until: Int): Set[A] 
  
	/** Groups elements in fixed size blocks by passing a "sliding window" over them (as opposed to partitioning them, as is done in grouped. */
	// def sliding(size: Int, step: Int): Iterator[Set[A]] 
  
	/** Groups elements in fixed size blocks by passing a "sliding window" over them (as opposed to partitioning them, as is done in grouped. */
	// def sliding(size: Int): Iterator[Set[A]] 
  
	/** Splits this mutable set into a prefix/suffix pair according to a predicate. */
	def span(p: (A) => Boolean): (UnorderedSequentialSet[A], UnorderedSequentialSet[A]) 
  
	/** Splits this mutable set into two at a given position. */
	// def splitAt(n: Int): (Set[A], Set[A]) 
  
	/** Defines the prefix of this object's toString representation. */
	def stringPrefix: String 
  
	/** Tests whether this set is a subset of another set. */
	// def subsetOf(that: GenSet[A]): Boolean 
  
	/** An iterator over all subsets of this set. */
	// def subsets: Iterator[Set[A]] 
  
	/** An iterator over all subsets of this set of the given size. */
	// def subsets(len: Int): Iterator[Set[A]] 
  
	/** [use case] Sums up the elements of this collection. */
	// def sum: A 
  
	/** Selects all elements except the first. */
	// def tail: Set[A] 
  
	/** Iterates over the tails of this mutable set. */
	// def tails: Iterator[Set[A]] 
  
	/** Selects first n elements. */
	// def take(n: Int): Set[A] 
  
	/** Selects last n elements. */
	// def takeRight(n: Int): Set[A] 
  
	/** Takes longest prefix of elements that satisfy a predicate. */
	// def takeWhile(p: (A) => Boolean): Set[A] 
  
	/** [use case] Converts this mutable set into another by copying all elements. */
	// def to[Col[_]]: Col[A] 
  
	/** [use case] Converts this mutable set to an array. */
	// def toArray: Array[A] 
  
	/** Converts this mutable set to a mutable buffer. */
	def toBuffer[A1 >: A]: Buffer[A1] 
  
	/** Converts this mutable set to an indexed sequence. */
	def toIndexedSeq: immutable.IndexedSeq[A] 
  
	/** Converts this mutable set to an iterable collection. */
	def toIterable: collection.Iterable[A] 
  
	/** Returns an Iterator over the elements in this mutable set. */
	def toIterator: Iterator[A] 
  
	/** Converts this mutable set to a list. */
	def toList: List[A] 
  
	/** [use case] Converts this mutable set to a map. */
	// def toMap[T, U]: collection.Map[T, U] 
  
	// def toParArray: ParArray[A] 
  
	/** Converts this mutable set to a sequence. */
	def toSeq: collection.Seq[A] 
  
	/** Converts this mutable set to a set. */
	def toSet[B >: A]: immutable.Set[B] 
  
	/** Converts this mutable set to a stream. */
	def toStream: immutable.Stream[A] 
  
	/** Creates a String representation of this object. */
	def toString(): String 
  
	/** Converts this mutable set to an unspecified Traversable. */
	def toTraversable: collection.Traversable[A] 
  
	/** Converts this mutable set to a Vector. */
	def toVector: Vector[A] 
  
	/** Transposes this mutable set of traversable collections into a mutable set of mutable sets. */
	// def transpose[B](implicit asTraversable: (A) => GenTraversableOnce[B]): Set[Set[B]] 
  
	/** Computes the union between of set and another set. */
	// def union(that: GenSet[A]): Set[A] 
  
	/** Converts this mutable set of pairs into two collections of the first and second half of each pair. */
	// def unzip[A1, A2](implicit asPair: (A) => (A1, A2)): (Set[A1], Set[A2]) 
  
	/** Converts this mutable set of triples into three collections of the first, second, and third element of each triple. */
	// def unzip3[A1, A2, A3](implicit asTriple: (A) => (A1, A2, A3)): (Set[A1], Set[A2], Set[A3]) 

	/** Updates the presence of a single element in this set. */
	def update(elem: A, included: Boolean): Unit 
  
	/** Creates a non-strict view of a slice of this mutable set. */
	// def view(from: Int, until: Int): IterableView[A, Set[A]] 
  
	/** Creates a non-strict view of this mutable set. */
	// def view: IterableView[A, Set[A]] 
  
	/** Creates a non-strict filter of this mutable set. */
	// def withFilter(p: (A) => Boolean): FilterMonadic[A, Set[A]] 
  
	/** [use case] Returns a mutable set formed from this mutable set and another iterable collection by combining corresponding elements in pairs. */
	// def zip[B](that: GenIterable[B]): Set[(A, B)] 
  
	/** [use case] Returns a mutable set formed from this mutable set and another iterable collection by combining corresponding elements in pairs. */
	// def zipAll[B](that: collection.Iterable[B], thisElem: A, thatElem: B): Set[(A, B)] 
  
	/** [use case] Zips this mutable set with its indices. */
	// def zipWithIndex: Set[(A, Int)] 
  
	/** Computes the union between this set and another set. */
	// def |(that: GenSet[A]): Set[A] 

	// def filter(p: (A) => Boolean): TraversableOnce[A] 
  
	// def flatMap[B](f: (A) => GenTraversableOnce[B]): TraversableOnce[B] 
  
	// def map[B](f: (A) => B): TraversableOnce[B] 
  
	// def withFilter(p: (A) => Boolean): Iterator[A] 

	// **** Other methods

 	/** The size of this mutable set. */
	def longSize: Long = size.toLong

}
