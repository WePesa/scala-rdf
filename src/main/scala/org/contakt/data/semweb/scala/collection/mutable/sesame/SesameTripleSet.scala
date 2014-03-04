package org.contakt.data.semweb.scala.collection.mutable.sesame

import scala.collection.JavaConversions._
import scala.util.Try
import org.openrdf.model.{Resource, Statement}
import org.openrdf.query.{BindingSet, QueryLanguage}
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
class SesameTripleSet(val rep: Repository) extends UnorderedSequentialSet[Statement] with SparqlProcessor {

	private val defaultConnection: RepositoryConnection = rep.getConnection

  // **** UnorderedSequentialSet methods

  /** Adds a single statement to the triple set. */
  def +=(stmt: Statement): SesameTripleSet = {
  	defaultConnection.add(stmt)
  	this
  }

  /** Removes a single statement from this triple set. */
  def -=(stmt: Statement): SesameTripleSet = {
  	defaultConnection.remove(stmt)
  	this
  }
 
  /** Tests if some statement is contained in this triple set. */
  def contains(stmt: Statement): Boolean = defaultConnection.hasStatement(stmt, false, contexts:_*)

  def iterator: Iterator[Statement] = SesameTripleSet.iterator(defaultConnection)

  def :\[B](z: B)(op: (org.openrdf.model.Statement, B) => B): B = ???
  def /:[B](z: B)(op: (B, org.openrdf.model.Statement) => B): B = ???
  def -(elem: org.openrdf.model.Statement): org.contakt.data.scala.collection.mutable.UnorderedSequentialSet[org.openrdf.model.Statement] = ???
  def -(elem1: org.openrdf.model.Statement,elem2: org.openrdf.model.Statement,elems: org.openrdf.model.Statement*): org.contakt.data.scala.collection.mutable.UnorderedSequentialSet[org.openrdf.model.Statement] = ???

	/** Removes two or more elements from this mutable set. */
  def -=(stmt1: Statement, stmt2: Statement, stmts: Statement*): SesameTripleSet = {
  	defaultConnection.remove(stmt1)
  	defaultConnection.remove(stmt2)
  	defaultConnection.remove(stmts)
  	this
  }

	def +(elem: org.openrdf.model.Statement): org.contakt.data.scala.collection.mutable.UnorderedSequentialSet[org.openrdf.model.Statement] = ???
	def +(elem1: org.openrdf.model.Statement,elem2: org.openrdf.model.Statement,elems: org.openrdf.model.Statement*): org.contakt.data.scala.collection.mutable.UnorderedSequentialSet[org.openrdf.model.Statement] = ???
	
	/** adds two or more elements to this mutable set. */
	def +=(stmt1: Statement, stmt2: Statement, stmts: Statement*): SesameTripleSet = {
  	defaultConnection.add(stmt1)
  	defaultConnection.add(stmt2)
  	defaultConnection.add(stmts)
  	this
	}

	/**
	 * Adds an element to this mutable set.
	 *
	 * @param stmt the triple to be added
	 * @return true if the element was not yet present in the set, false otherwise.
	 */
	def add(stmt: Statement): Boolean = {
		if (contains(stmt)) {
			false
		} else {
			defaultConnection.add(stmt)
			true
		}
	}

	def addString(b: StringBuilder,start: String,sep: String,end: String): StringBuilder = ???
	def addString(b: StringBuilder,sep: String): StringBuilder = ???
	def addString(b: StringBuilder): StringBuilder = ???
	def aggregate[B](z: B)(seqop: (B, org.openrdf.model.Statement) => B,combop: (B, B) => B): B = ???
	// def apply(elem: org.openrdf.model.Statement): Boolean = ???

	/**
	 * Removes all elements that can be removed from the triple set.
	 * For some Sesame repository types, e.g. those with RDFS inferencing,
	 * it is not possible to remove all triples.
	 */
	def clear() = sparqlUpdate(
		"DELETE { ?s ?p ?o . } WHERE { ?s ?p ?o . }"
	)
		
	def collect[B](pf: PartialFunction[org.openrdf.model.Statement,B]): org.contakt.data.scala.collection.mutable.UnorderedSequentialSet[B] = ???
	def collectFirst[B](pf: PartialFunction[org.openrdf.model.Statement,B]): Option[B] = ???
	def copyToBuffer[B >: org.openrdf.model.Statement](dest: scala.collection.mutable.Buffer[B]): Unit = ???
	def count(p: org.openrdf.model.Statement => Boolean): Int = ???

  /**
   * Returns an empty <strong>in-memory</strong> Sesame triple set (i.e. with no added triples beyond any that
   * Sesame puts there by default).
   *
   * @return a Sesame triple set with no triples.
   */
  def empty = SesameTripleSet.empty

	/** Compares this set with another object for equality. */
	override def equals(that: Any) = {
		if (canEqual(that)) {
			val ussThat = that.asInstanceOf[UnorderedSequentialSet[Statement]]
			if (longSize != ussThat.longSize) {
				false
			} else {
				// Check that every statement in 'uss' is also in this triple set.
				val iter = ussThat.iterator
				var areEqual = true
				while (areEqual && iter.hasNext) {
					areEqual = contains(iter.next)
				}
				areEqual
			}
		} else {
			false
		}
	}  

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

  /** Tests if this triple set is empty. */
	def isEmpty = longSize == 0

	def isTraversableAgain: Boolean = ???
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

  /** The size of this triple set. */
  def size = longSize.toInt

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

	// **** Other methods ****
	private def contexts = SesameTripleSet.contexts(defaultConnection)

  /** The size of this triple set. */
  override def longSize: Long = defaultConnection.size(contexts:_*)

  def sparqlAsk(query: String) = SesameTripleSet.sparqlAsk(query)(defaultConnection)

  def sparqlConstruct(query: String) = SesameTripleSet.sparqlConstruct(query)(defaultConnection)

  def sparqlDescribe(query: String) = SesameTripleSet.sparqlDescribe(query)(defaultConnection)

  def sparqlSelect(query: String) = SesameTripleSet.sparqlSelect(query)(defaultConnection)

  def sparqlUpdate(update: String) = SesameTripleSet.sparqlUpdate(update)(defaultConnection)

}

object SesameTripleSet {

	/**
	 * Returns a sequence of the contexts in the Repository to which the
	 * RepositoryConnection is connected.
	 */
  def contexts(connection: RepositoryConnection): Seq[Resource] = RepositoryResultIterator(connection.getContextIDs).toSeq

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
	 * Returns an iterator for the statements in the Repository to which the
	 * RepositoryConnection is connected.
	 */
  def iterator(implicit connection: RepositoryConnection): Iterator[Statement] = {
    val results = connection.getStatements(null, null, null, true)
    new RepositoryResultIterator(results)
  }

  /**
   * Returns the results of a SPARQL ASK query on the Repository to which the
   * RepositoryConnection is connected.
   */
  def sparqlAsk(query: String)(implicit connection: RepositoryConnection): Try[Boolean] = {
  	Try {
	  	val preparedQuery = connection.prepareBooleanQuery(QueryLanguage.SPARQL, query)
	  	preparedQuery.evaluate
  	}
  }

  /**
   * Returns the results of a SPARQL CONSTRUCT query on the Repository to which the
   * RepositoryConnection is connected.
   */
  def sparqlConstruct(query: String)(implicit connection: RepositoryConnection): Try[Iterator[Statement]] = {
  	Try {
	  	val preparedQuery = connection.prepareGraphQuery(QueryLanguage.SPARQL, query)
	  	QueryResultIterator(preparedQuery.evaluate)
  	}
  }

  /**
   * Returns the results of a SPARQL DESCRIBE query on the Repository to which the
   * RepositoryConnection is connected.
   */
  def sparqlDescribe(query: String)(implicit connection: RepositoryConnection): Try[Iterator[Statement]] = {
  	Try {
	  	val preparedQuery = connection.prepareGraphQuery(QueryLanguage.SPARQL, query)
	  	QueryResultIterator(preparedQuery.evaluate)
  	}
  }

  /**
   * Returns the results of a SPARQL SELECT query on the Repository to which the
   * RepositoryConnection is connected.
   */
  def sparqlSelect(query: String)(implicit connection: RepositoryConnection): Try[Iterator[BindingSet]] = {
  	Try {
	  	val preparedQuery = connection.prepareTupleQuery(QueryLanguage.SPARQL, query)
	  	QueryResultIterator(preparedQuery.evaluate)
  	}
  }

  /**
   * Executes a SPARQL INSERT or DELETE update on the Repository to which the
   * RepositoryConnection is connected.
   */
  def sparqlUpdate(update: String)(implicit connection: RepositoryConnection): Try[Unit] = {
  	Try {
	  	val preparedUpdate = connection.prepareUpdate(QueryLanguage.SPARQL, update)
	  	preparedUpdate.execute
  	}
  }

}
