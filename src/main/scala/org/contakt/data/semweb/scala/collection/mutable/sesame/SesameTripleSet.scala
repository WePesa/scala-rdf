package org.contakt.data.semweb.scala.collection.mutable.sesame

import org.openrdf.model.{Resource, Statement}
import org.openrdf.repository.{Repository, RepositoryConnection}
import org.contakt.data.scala.collection.mutable.MiniSet

/**
 * Sesame implementation of an RDF triple store as
 * a mutable set of statements (triples).
 * This class is abstract as some configuration methods
 * (from SesameTripleSetConfiguration) need to be able
 * to be mixed-in via traits to suit different user choices.
 */
abstract class SesameTripleSet(val rep: Repository) extends MiniSet[Statement, SesameTripleSet] {

	private val defaultConnection: RepositoryConnection = rep.getConnection

	// **** Methods from Set[Statement] ****

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

	def iterator(connection: RepositoryConnection): Iterator[Statement] = {
		val results = connection.getStatements(null, null, null, true)
    new RepositoryResultIterator(results)
  }

  def iterator: Iterator[Statement] = iterator(defaultConnection)

  /** Tests if some statement is contained in this triple set. */
  def contains(stmt: Statement): Boolean = defaultConnection.hasStatement(stmt, false, contexts:_*)

  /** The size of this triple set. */
  def size: Int = defaultConnection.size(contexts:_*).toInt

	// **** Other methods ****

  private def contexts: Seq[Resource] = RepositoryResultIterator(defaultConnection.getContextIDs).toSeq

}
