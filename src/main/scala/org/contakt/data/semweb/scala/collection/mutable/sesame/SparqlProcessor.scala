package org.contakt.data.semweb.scala.collection.mutable.sesame

import scala.util.Try
import org.openrdf.model.Statement
import org.openrdf.query.BindingSet

/**
 * Trait for objects that implement SPARQL queries and updates.
 */
trait SparqlProcessor {

  /**
   * Returns the results of a SPARQL ASK query on the Repository to which the
   * RepositoryConnection is connected.
   */
  def sparqlAsk(query: String): Try[Boolean]

  /**
   * Returns the results of a SPARQL CONSTRUCT query on the Repository to which the
   * RepositoryConnection is connected.
   */
  def sparqlConstruct(query: String): Try[Iterator[Statement]]

  /**
   * Returns the results of a SPARQL DESCRIBE query on the Repository to which the
   * RepositoryConnection is connected.
   */
  def sparqlDescribe(query: String): Try[Iterator[Statement]]

  /**
   * Returns the results of a SPARQL SELECT query on the Repository to which the
   * RepositoryConnection is connected.
   */
  def sparqlSelect(query: String): Try[Iterator[BindingSet]]

  /**
   * Executes a SPARQL INSERT or DELETE update on the Repository to which the
   * RepositoryConnection is connected.
   */
  def sparqlUpdate(update: String): Try[Unit]

}
