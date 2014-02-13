package org.contakt.data.semweb.scala.collection.mutable.sesame

import org.openrdf.model.{Resource, Statement}
import org.openrdf.repository.{Repository, RepositoryConnection}
import org.openrdf.repository.sail.SailRepository
import org.openrdf.sail.inferencer.fc.ForwardChainingRDFSInferencer
import org.openrdf.sail.memory.MemoryStore
import org.contakt.data.scala.collection.mutable.UnorderedSequentialSet

/**
 * Sesame implementation of an RDF triple store as
 * a mutable set of statements (triples).
 * This class is abstract as some configuration methods
 * (from SesameTripleSetConfiguration) need to be able
 * to be mixed-in via traits to suit different user choices.
 */
class SesameTripleSet(val rep: Repository) extends UnorderedSequentialSet[Statement] {

	private val defaultConnection: RepositoryConnection = rep.getConnection

  /** As seen from class SesameTripleSet, the missing signatures are as follows.
    * For convenience, these are usable as stub implementations. */
  def :\[B](z: B)(op: (org.openrdf.model.Statement, B) => B): B = ???
  def /:[B](z: B)(op: (B, org.openrdf.model.Statement) => B): B = ???
  def -(elem: org.openrdf.model.Statement): org.contakt.data.scala.collection.mutable.UnorderedSequentialSet[org.openrdf.model.Statement] = ???
  def -(elem1: org.openrdf.model.Statement,elem2: org.openrdf.model.Statement,elems: org.openrdf.model.Statement*): org.contakt.data.scala.collection.mutable.UnorderedSequentialSet[org.openrdf.model.Statement] = ???
  def -=(elem1: org.openrdf.model.Statement,elem2: org.openrdf.model.Statement,elems: org.openrdf.model.Statement*): org.contakt.data.scala.collection.mutable.UnorderedSequentialSet[org.openrdf.model.Statement] = ???
  def -=(elem: org.openrdf.model.Statement): org.contakt.data.scala.collection.mutable.UnorderedSequentialSet[org.openrdf.model.Statement] = ???
	def +(elem: org.openrdf.model.Statement): org.contakt.data.scala.collection.mutable.UnorderedSequentialSet[org.openrdf.model.Statement] = ???
	def +(elem1: org.openrdf.model.Statement,elem2: org.openrdf.model.Statement,elems: org.openrdf.model.Statement*): org.contakt.data.scala.collection.mutable.UnorderedSequentialSet[org.openrdf.model.Statement] = ???
	def +=(elem1: org.openrdf.model.Statement,elem2: org.openrdf.model.Statement,elems: org.openrdf.model.Statement*): org.contakt.data.scala.collection.mutable.UnorderedSequentialSet[org.openrdf.model.Statement] = ???
	def +=(elem: org.openrdf.model.Statement): org.contakt.data.scala.collection.mutable.UnorderedSequentialSet[org.openrdf.model.Statement] = ???
	def add(elem: org.openrdf.model.Statement): Boolean = ???
	def addString(b: StringBuilder,start: String,sep: String,end: String): StringBuilder = ???
	def addString(b: StringBuilder,sep: String): StringBuilder = ???
	def addString(b: StringBuilder): StringBuilder = ???
	def aggregate[B](z: B)(seqop: (B, org.openrdf.model.Statement) => B,combop: (B, B) => B): B = ???
	def apply(elem: org.openrdf.model.Statement): Boolean = ???
	def canEqual(that: Any): Boolean = ???
	def clear(): Unit = ???
	def collect[B](pf: PartialFunction[org.openrdf.model.Statement,B]): org.contakt.data.scala.collection.mutable.UnorderedSequentialSet[B] = ???
	def collectFirst[B](pf: PartialFunction[org.openrdf.model.Statement,B]): Option[B] = ???
	def contains(elem: org.openrdf.model.Statement): Boolean = ???
	def copyToBuffer[B >: org.openrdf.model.Statement](dest: scala.collection.mutable.Buffer[B]): Unit = ???
	def count(p: org.openrdf.model.Statement => Boolean): Int = ???
	def empty: org.contakt.data.scala.collection.mutable.UnorderedSequentialSet[org.openrdf.model.Statement] = ???
	def exists(p: org.openrdf.model.Statement => Boolean): Boolean = ???
	def filter(p: org.openrdf.model.Statement => Boolean): org.contakt.data.scala.collection.mutable.UnorderedSequentialSet[org.openrdf.model.Statement] = ???
	def filterNot(p: org.openrdf.model.Statement => Boolean): org.contakt.data.scala.collection.mutable.UnorderedSequentialSet[org.openrdf.model.Statement] = ???
	def find(p: org.openrdf.model.Statement => Boolean): Option[org.openrdf.model.Statement] = ???
	def fold[A1 >: org.openrdf.model.Statement](z: A1)(op: (A1, A1) => A1): A1 = ???
	def foldLeft[B](z: B)(op: (B, org.openrdf.model.Statement) => B): B = ???
	def foldRight[B](z: B)(op: (org.openrdf.model.Statement, B) => B): B = ???
	def forall(p: org.openrdf.model.Statement => Boolean): Boolean = ???
	def foreach[B](f: org.openrdf.model.Statement => B): Unit = ???
	def grouped(size: Int): Iterator[org.contakt.data.scala.collection.mutable.UnorderedSequentialSet[org.openrdf.model.Statement]] = ???
	def hasDefiniteSize: Boolean = ???
	def isEmpty: Boolean = ???
	def isTraversableAgain: Boolean = ???
	def iterator: Iterator[org.openrdf.model.Statement] = ???
	def map[B](f: org.openrdf.model.Statement => B): org.contakt.data.scala.collection.mutable.UnorderedSequentialSet[B] = ???
	def mkString(start: String,sep: String,end: String): String = ???
	def mkString(sep: String): String = ???
	def mkString: String = ???
	def nonEmpty: Boolean = ???
	def partition(p: org.openrdf.model.Statement => Boolean): (org.contakt.data.scala.collection.mutable.UnorderedSequentialSet[org.openrdf.model.Statement], org.contakt.data.scala.collection.mutable.UnorderedSequentialSet[org.openrdf.model.Statement]) = ???
	def reduce[A1 >: org.openrdf.model.Statement](op: (A1, A1) => A1): A1 = ???
	def reduceLeft[B >: org.openrdf.model.Statement](op: (B, org.openrdf.model.Statement) => B): B = ???
	def reduceLeftOption[B >: org.openrdf.model.Statement](op: (B, org.openrdf.model.Statement) => B): Option[B] = ???
	def reduceOption[A1 >: org.openrdf.model.Statement](op: (A1, A1) => A1): Option[A1] = ???
	def reduceRight[B >: org.openrdf.model.Statement](op: (org.openrdf.model.Statement, B) => B): B = ???
	def reduceRightOption[B >: org.openrdf.model.Statement](op: (org.openrdf.model.Statement, B) => B): Option[B] = ???
	def remove(elem: org.openrdf.model.Statement): Boolean = ???
	def retain(p: org.openrdf.model.Statement => Boolean): Unit = ???
	def size: Int = ???
	def span(p: org.openrdf.model.Statement => Boolean): (org.contakt.data.scala.collection.mutable.UnorderedSequentialSet[org.openrdf.model.Statement], org.contakt.data.scala.collection.mutable.UnorderedSequentialSet[org.openrdf.model.Statement]) = ???
	def stringPrefix: String = ???
	def toBuffer[A1 >: org.openrdf.model.Statement]: scala.collection.mutable.Buffer[A1] = ???
	def toIndexedSeq: scala.collection.immutable.IndexedSeq[org.openrdf.model.Statement] = ???
	def toIterable: Iterable[org.openrdf.model.Statement] = ???
	def toIterator: Iterator[org.openrdf.model.Statement] = ???
	def toList: List[org.openrdf.model.Statement] = ???
	def toSeq: Seq[org.openrdf.model.Statement] = ???
	def toSet[B >: org.openrdf.model.Statement]: scala.collection.immutable.Set[B] = ???
	def toStream: scala.collection.immutable.Stream[org.openrdf.model.Statement] = ???
	def toTraversable: Traversable[org.openrdf.model.Statement] = ???
	def toVector: Vector[org.openrdf.model.Statement] = ???
	def update(elem: org.openrdf.model.Statement,included: Boolean): Unit = ???
  /** End of the auto-generated stubs. */

 //  /** The size of this triple set. */
 //  override def size: Int = defaultConnection.size(contexts:_*).toInt

	// // **** Methods from Set[Statement]

 //  /** Adds a single statement to the triple set. */
 //  def +=(stmt: Statement): SesameTripleSet = {
 //  	defaultConnection.add(stmt)
 //  	this
 //  }

 //  /** Removes a single statement from this triple set. */
 //  def -=(stmt: Statement): SesameTripleSet = {
 //  	defaultConnection.remove(stmt)
 //  	this
 //  }

 //  /** Tests if some statement is contained in this triple set. */
 //  def contains(stmt: Statement): Boolean = defaultConnection.hasStatement(stmt, false, contexts:_*)

 //  def iterator: Iterator[Statement] = iterator(defaultConnection)

 //  /**
 //   * Returns an empty <strong>in-memory</strong> Sesame triple set (i.e. with no added triples beyond any that
 //   * Sesame puts there by default).
 //   *
 //   * @return a Sesame triple set with no triples.
 //   */
 //  def empty: SesameTripleSet = SesameTripleSet.empty

	// // **** Other methods ****

	// def iterator(connection: RepositoryConnection): Iterator[Statement] = {
	// 	val results = connection.getStatements(null, null, null, true)
 //    new RepositoryResultIterator(results)
 //  }

 //  private def contexts: Seq[Resource] = RepositoryResultIterator(defaultConnection.getContextIDs).toSeq

}

object SesameTripleSet {

  /**
   * Returns an empty <strong>in-memory</strong> Sesame triple set
   * without inferencing.
   *
   * @return a Sesame triple set with no triples and no inferencing.
   */
  def empty: SesameTripleSet = {
    val sailStack = new MemoryStore()
    val repository = new SailRepository(sailStack)
    repository.initialize()

    new SesameTripleSet(repository)
  }

  /**
   * Returns an <strong>in-memory</strong> Sesame triple set
   * (containing any triples that Sesame puts there by default)
   * with RDFS inferencing.
   *
   * @return a Sesame triple set with default triples and RDFS inferencing.
   */
  def default: SesameTripleSet = {
    val sailStack = new ForwardChainingRDFSInferencer(new MemoryStore())
    val repository = new SailRepository(sailStack)
    repository.initialize()

    new SesameTripleSet(repository)
  }

}