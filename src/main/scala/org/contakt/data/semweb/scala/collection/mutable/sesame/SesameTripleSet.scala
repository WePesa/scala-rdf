package org.contakt.data.semweb.scala.collection.mutable.sesame

import scala.collection.mutable.{Set, SetLike}
import org.openrdf.model.Statement
import org.openrdf.query.QueryResult
import org.openrdf.repository.{Repository, RepositoryConnection, RepositoryResult}
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

	def iterator(connection: RepositoryConnection): Iterator[Statement] = {
		val results = connection.getStatements(null, null, null, true)
    new RepositoryResultIterator(results)
  }

  def iterator: Iterator[Statement] = iterator(defaultConnection)

	// **** Other methods ****

}
