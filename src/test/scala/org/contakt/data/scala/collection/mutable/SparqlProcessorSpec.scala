package org.contakt.data.scala.collection.mutable

import scala.collection.mutable.HashSet
import scala.util.{Failure, Success, Try}
import org.scalatest._
import org.scalatest.matchers._
import org.openrdf.model.impl.StatementImpl
import org.contakt.data.semweb.scala.collection.mutable.sesame.SparqlProcessor

/**
 * Class for testing implementations of
 * the 'SparqlProcessor' trait.
 */
trait SparqlProcessorSpec[Proc <: SparqlProcessor] extends FlatSpec with BeforeAndAfter with ShouldMatchers with SparqlProcessorSpecSetup[Proc] {

  private def longSize(proc: Proc): Try[Long] = {
    var count: Long = 0
    proc.sparqlSelect("SELECT * WHERE { ?s ?p ?q . }") match {
      case Failure(e) => Failure(e)
      case Success(iterator) =>
        iterator.foreach{ stmt => count += 1 }
        Success(count)
    }
  }

  "A SPARQL processor" should "have a concrete type for an empty set" in {
    info(empty.getClass.getName)
  }

  it should "be empty if created empty" in {
    val emptySet = empty
    longSize(emptySet) match {
      case Failure(e) => assert(false, s"failed counting statements in a processor: ${e.getMessage}")
      case Success(size) => assert(size == 0, s"found unexpected statements in an empty processor: count = $size")
    }
  }

  it should "allow insertion of a statement" in {
    val newSet = empty
    newSet.sparqlUpdate("INSERT { <http://example.com#subject> <http://example.com#predicate> <http://example.com#object> . } WHERE {}") match {
      case Failure(e) => assert(false, s"failed inserting statement into an empty processor: ${e.getMessage}")
      case Success(nothing) => // ignore
    }
    longSize(newSet) match {
      case Failure(e) => assert(false, s"failed counting statements in a processor: ${e.getMessage}")
      case Success(size) => assert(size == 1, s"found wrong number of statements in a processor, expected 1: count = $size")
    }
  }

}
