package org.contakt.data.semweb.scala.collection.mutable.sesame

import scala.collection.Set
import scala.collection.immutable
import scala.collection.mutable.Buffer
import scala.collection.JavaConversions._
import scala.reflect.ClassTag
import scala.util.Try
import org.openrdf.model.{Resource, Statement}
import org.openrdf.query.{BindingSet, QueryLanguage}
import org.openrdf.repository.{Repository, RepositoryConnection}
import org.openrdf.repository.sail.SailRepository
import org.openrdf.sail.inferencer.fc.ForwardChainingRDFSInferencer
import org.openrdf.sail.memory.MemoryStore
import org.contakt.data.scala.collection.mutable.{UnorderedSequentialHashSet, GroupIterator, UnorderedSequentialSet}

/**
 * Sesame implementation of an RDF triple store as
 * a mutable set of statements (triples).
 * This class is abstract as some configuration methods
 * (from SesameTripleSetConfiguration) need to be able
 * to be mixed-in via traits to suit different user choices.
 */
class SesameTripleSet(val rep: Repository) extends UnorderedSequentialSet[Statement] with SparqlProcessor {

	private val defaultConnection = rep.getConnection

  // **** UnorderedSequentialSet methods

  /** Adds a single statement to the triple set. */
  def +=(stmt: Statement) = {
  	defaultConnection.add(stmt)
  	this
  }

  /** Removes a single statement from this triple set. */
  def -=(stmt: Statement) = {
  	defaultConnection.remove(stmt)
  	this
  }
 
  /** Tests if some statement is contained in this triple set. */
  def contains(stmt: Statement) = defaultConnection.hasStatement(stmt, false, contexts:_*)

  def iterator = SesameTripleSet.iterator(defaultConnection)

  def :\[B](z: B)(op: (org.openrdf.model.Statement, B) => B): B = ???
  def /:[B](z: B)(op: (B, org.openrdf.model.Statement) => B): B = ???
  def -(elem: org.openrdf.model.Statement): org.contakt.data.scala.collection.mutable.UnorderedSequentialSet[org.openrdf.model.Statement] = ???
  def -(elem1: org.openrdf.model.Statement,elem2: org.openrdf.model.Statement,elems: org.openrdf.model.Statement*): org.contakt.data.scala.collection.mutable.UnorderedSequentialSet[org.openrdf.model.Statement] = ???

	/** Removes two or more elements from this mutable set. */
  def -=(stmt1: Statement, stmt2: Statement, stmts: Statement*) = {
  	defaultConnection.remove(stmt1)
  	defaultConnection.remove(stmt2)
  	defaultConnection.remove(stmts)
  	this
  }

	def +(elem: org.openrdf.model.Statement): org.contakt.data.scala.collection.mutable.UnorderedSequentialSet[org.openrdf.model.Statement] = ???
	def +(elem1: org.openrdf.model.Statement,elem2: org.openrdf.model.Statement,elems: org.openrdf.model.Statement*): org.contakt.data.scala.collection.mutable.UnorderedSequentialSet[org.openrdf.model.Statement] = ???
	
	/** adds all elements produced by a TraversableOnce to this triple set. */
	def ++=(xs: TraversableOnce[Statement]) = {
    xs.foreach{add(_)}
		this
	}
	
	/** adds all elements produced by an UnorderedSequentialSet to this triple set. */
	def ++=(xs: UnorderedSequentialSet[Statement]) = {
		xs.iterator.foreach{add(_)}
		this
	}
  
	/** adds two or more elements to this mutable set. */
	def +=(stmt1: Statement, stmt2: Statement, stmts: Statement*) = {
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
	def add(stmt: Statement) = {
		if (contains(stmt)) {
			false
		} else {
			defaultConnection.add(stmt)
			true
		}
	}

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

	/** Counts the number of elements in the mutable set which satisfy a predicate. */
	def count(p: Statement => Boolean) = iterator.count(p)

  /**
   * Returns an empty <strong>in-memory</strong> Sesame triple set (i.e. with no added triples beyond any that
   * Sesame puts there by default).
   *
   * @return a Sesame triple set with no triples.
   */
  def empty = SesameTripleSet.empty

	/**
	 * Compares this set with another object for equality.
	 * WARNING: potentially very slow for large sets.
	 */
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
				if (areEqual) {
					// Now check the reverse.
					val iter = iterator
					while (areEqual && iter.hasNext) {
						areEqual = ussThat.contains(iter.next)
					}
				}
				areEqual
			}
		} else {
			false
		}
	}  

	/** Tests whether a predicate holds for some of the elements of this triple set. */
	def exists(p: Statement => Boolean) = iterator.exists(p)

	/** Selects all elements of this triple set which satisfy a predicate. */
	def filter(p: Statement => Boolean) = {
		val resultSet = empty
		iterator.filter(p).foreach{resultSet.add(_)}
		resultSet
	}

	/** Selects all elements of this triple set which do not satisfy a predicate. */
	def filterNot(p: Statement => Boolean) = {
		val resultSet = empty
		iterator.filterNot(p).foreach{resultSet.add(_)}
		resultSet
	}

	/** Finds an element of the mutable set satisfying a predicate, if any. */
	def find(p: Statement => Boolean) = iterator.find(p)

	def fold[A1 >: org.openrdf.model.Statement](z: A1)(op: (A1, A1) => A1): A1 = ???
	def foldLeft[B](z: B)(op: (B, org.openrdf.model.Statement) => B): B = ???
	def foldRight[B](z: B)(op: (org.openrdf.model.Statement, B) => B): B = ???

	/** Tests whether a predicate holds for all elements of this triple set. */
	def forall(p: Statement => Boolean) = iterator.forall(p)

	/** [use case] Applies a function f to all elements of this triple set. */
	def foreach[B](f: Statement => B) = iterator.foreach(f)

	/** Partitions elements in fixed size mutable sets. */
	def grouped(size: Int) = new GroupIterator[Statement](iterator, size, { () => empty })

	/** Tests whether this triple set is known to have a finite size. */
	def hasDefiniteSize = true

  /** Tests if this triple set is empty. */
	def isEmpty = (longSize == 0)

  /** Tests whether this triple set can be repeatedly traversed. */
	def isTraversableAgain: Boolean = true

  /** [use case] Builds a new collection by applying a function to all elements of this triple set. */
	def map[B](f: Statement => B) = {
    val resultSet = new UnorderedSequentialHashSet[B]()
    iterator.map(f).foreach{b: B => resultSet.add(_)}
    resultSet
  }

  /**
   * Partitions this triple set into two triple sets according to a predicate.
   *
   * @param p the predicate on which to partition.
   * @return a pair of sets: the first set consists of all elements that satisfy the predicate p and the second set consists of all elements that don't. The relative order of the elements in the resulting sets is the same as in the original set.
   */
	def partition(p: Statement => Boolean) = {
    val partition1 = empty
    val partition2 = empty
    foreach{stmt => if (p(stmt)) partition1.add(stmt) else partition2.add(stmt)}
    (partition1, partition2)
  }

	/**
	 * Removes an element from this triple set.
	 *
	 * @param elem The element to be removed.
	 * @return true if the element was previously present in the set, false otherwise.
	 */
	def remove(stmt: Statement) = {
		if (contains(stmt)) {
      defaultConnection.remove(stmt)
      true
		} else {
      false
		}
	}

	/** Removes all elements from the triple set for which do not satisfy a predicate. */
	def retain(p: Statement => Boolean) = {
		iterator.foreach{stmt => if (!p(stmt)) defaultConnection.remove(stmt)}
	}

  /** The size of this triple set. */
  def size = longSize.toInt

	/** [use case] Converts this triple set to an array. */
	def toArray[B >: Statement](implicit arg0: ClassTag[B]) = iterator.toArray[B]

	/** Converts this triple set to a triple buffer. */
	def toBuffer[A1 >: Statement] = iterator.toBuffer[A1]

  /** Converts this triple set to an indexed sequence. */
	def toIndexedSeq: immutable.IndexedSeq[Statement] = iterator.toIndexedSeq

  /** Converts this triple set to an iterable collection. */
	def toIterable: Iterable[Statement] = iterator.toIterable

  /** Returns an Iterator over the elements in this triple set. */
	def toIterator: Iterator[Statement] = iterator

	def toList: List[org.openrdf.model.Statement] = ???
	def toSeq: Seq[org.openrdf.model.Statement] = ???
	def toSet[B >: org.openrdf.model.Statement]: scala.collection.immutable.Set[B] = ???
	def toStream: scala.collection.immutable.Stream[org.openrdf.model.Statement] = ???

  /** Creates a multi-line String representation of this triple set. */
  override def toString = mkString(stringPrefix + "{\n", ",\n", "}")

  def toTraversable: Traversable[org.openrdf.model.Statement] = ???
	def toVector: Vector[org.openrdf.model.Statement] = ???
	def update(elem: org.openrdf.model.Statement,included: Boolean): Unit = ???

	// **** Other methods ****
	private def contexts = SesameTripleSet.contexts(defaultConnection)

  /** The size of this triple set. */
  override def longSize = defaultConnection.size(contexts:_*)

  /** [use case] Builds a new collection by applying a function to all elements of this mutable set. */
  def mapToSet[B](f: (Statement) => B) = map(f)

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
  def contexts(connection: RepositoryConnection) = RepositoryResultIterator(connection.getContextIDs).toSeq

  /**
   * Returns an <strong>in-memory</strong> Sesame triple set
   * (containing any triples that Sesame puts there by default)
   * with RDFS inferencing.
   *
   * @return a Sesame triple set with default triples and RDFS inferencing.
   */
  def default = {
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
  def empty = {
    val sailStack = new MemoryStore()
    val repository = new SailRepository(sailStack)
    repository.initialize()

    new SesameTripleSet(repository)
  }


	/**
	 * Returns an iterator for the statements in the Repository to which the
	 * RepositoryConnection is connected.
	 */
  def iterator(implicit connection: RepositoryConnection) = {
    val results = connection.getStatements(null, null, null, true)
    new RepositoryResultIterator(results)
  }

  /**
   * Returns the results of a SPARQL ASK query on the Repository to which the
   * RepositoryConnection is connected.
   */
  def sparqlAsk(query: String)(implicit connection: RepositoryConnection) = {
  	Try {
	  	val preparedQuery = connection.prepareBooleanQuery(QueryLanguage.SPARQL, query)
	  	preparedQuery.evaluate
  	}
  }

  /**
   * Returns the results of a SPARQL CONSTRUCT query on the Repository to which the
   * RepositoryConnection is connected.
   */
  def sparqlConstruct(query: String)(implicit connection: RepositoryConnection) = {
  	Try {
	  	val preparedQuery = connection.prepareGraphQuery(QueryLanguage.SPARQL, query)
	  	QueryResultIterator(preparedQuery.evaluate)
  	}
  }

  /**
   * Returns the results of a SPARQL DESCRIBE query on the Repository to which the
   * RepositoryConnection is connected.
   */
  def sparqlDescribe(query: String)(implicit connection: RepositoryConnection) = {
  	Try {
	  	val preparedQuery = connection.prepareGraphQuery(QueryLanguage.SPARQL, query)
	  	QueryResultIterator(preparedQuery.evaluate)
  	}
  }

  /**
   * Returns the results of a SPARQL SELECT query on the Repository to which the
   * RepositoryConnection is connected.
   */
  def sparqlSelect(query: String)(implicit connection: RepositoryConnection) = {
  	Try {
	  	val preparedQuery = connection.prepareTupleQuery(QueryLanguage.SPARQL, query)
	  	QueryResultIterator(preparedQuery.evaluate)
  	}
  }

  /**
   * Executes a SPARQL INSERT or DELETE update on the Repository to which the
   * RepositoryConnection is connected.
   */
  def sparqlUpdate(update: String)(implicit connection: RepositoryConnection) = {
  	Try {
	  	val preparedUpdate = connection.prepareUpdate(QueryLanguage.SPARQL, update)
	  	preparedUpdate.execute
  	}
  }

}
