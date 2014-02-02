package org.contakt.data.semweb.scala.collection.mutable.sesame

import scala.collection.mutable.Set
import org.openrdf.model.Statement
import org.openrdf.repository.Repository
import org.openrdf.repository.sail.SailRepository
import org.openrdf.sail.inferencer.fc.ForwardChainingRDFSInferencer
import org.openrdf.sail.memory.MemoryStore

/**
 * Default Sesame implementation of an RDF triple store as
 * a mutable set of statements (triples).
 * This class assumes some resonable default configuration choices.
 */
class DefaultSesameTripleSet(override val rep: Repository) extends SesameTripleSet(rep) {

  // **** Methods from Set[Statement] ****

  /**
   * Returns an empty <strong>in-memory</strong> Sesame triple set (i.e. with no added triples beyond any that
   * Sesame puts there by default).
   *
   * @return a Sesame triple set with no triples.
   */
  override def empty = DefaultSesameTripleSet.empty

  // **** Other methods ****

}

object DefaultSesameTripleSet {

  // **** Methods from Set[Statement] ****

  /**
   * Returns an empty <strong>in-memory</strong> Sesame triple set (i.e. with no added triples beyond any that
   * Sesame puts there by default).
   *
   * @return a Sesame triple set with no triples.
   */
  def empty: SesameTripleSet = {
    val sailStack = new ForwardChainingRDFSInferencer(new MemoryStore())
    val repository = new SailRepository(sailStack)
    repository.initialize

    new DefaultSesameTripleSet(repository)
  }

  // **** Other methods ****

}