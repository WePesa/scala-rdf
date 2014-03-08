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

  /**
   * Class used to add extra methods to 'Proc' objects under test.
   */
  private class RichProc(proc: Proc) {
    def longSize: Try[Long] = {
      var count: Long = 0
      proc.sparqlSelect("SELECT * WHERE { ?s ?p ?q . }") match {
        case Failure(e) => Failure(e)
        case Success(iterator) =>
          iterator.foreach{ stmt => count += 1 }
          Success(count)
      }
    }   
  }

  /** Method which implicitly converts a Proc to a RichProc, as required. */
  private implicit def proc2RichProc(proc: Proc) = new RichProc(proc)

  // **** Tests ****

  "A SPARQL processor" should "have a concrete type for an empty set" in {
    info(empty.getClass.getName)
  }

  it should "be empty if created empty" in {
    val emptySet = empty
    emptySet.longSize match {
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
    newSet.longSize match {
      case Failure(e) => assert(false, s"failed counting statements in a processor: ${e.getMessage}")
      case Success(size) => assert(size == 1, s"found wrong number of statements in a processor, expected 1: count = $size")
    }
  }

  it should "allow deletion of a statement" in {
    val newSet = empty
    newSet.sparqlUpdate("INSERT { <http://example.com#subject> <http://example.com#predicate> <http://example.com#object> . } WHERE {}") match {
      case Failure(e) => assert(false, s"failed inserting statement into an empty processor: ${e.getMessage}")
      case Success(nothing) => // ignore
    }
    newSet.longSize match {
      case Failure(e) => assert(false, s"failed counting statements in a processor: ${e.getMessage}")
      case Success(size) => assert(size == 1, s"found wrong number of statements in a processor, expected 1: count = $size")
    }
    newSet.sparqlUpdate("DELETE { <http://example.com#subject> <http://example.com#predicate> <http://example.com#object> . } WHERE {}") match {
      case Failure(e) => assert(false, s"failed deleting statement from a processor: ${e.getMessage}")
      case Success(nothing) => // ignore
    }
    newSet.longSize match {
      case Failure(e) => assert(false, s"failed counting statements in a processor: ${e.getMessage}")
      case Success(size) => assert(size == 0, s"found wrong number of statements in a processor, expected 0: count = $size")
    }
  }

  it should "allow selection of a statement" in {
    val newSet = empty
    newSet.sparqlUpdate("INSERT { <http://example.com#subject> <http://example.com#predicate> <http://example.com#object> . } WHERE {}") match {
      case Failure(e) => assert(false, s"failed inserting statement into an empty processor: ${e.getMessage}")
      case Success(nothing) => // ignore
    }
    newSet.longSize match {
      case Failure(e) => assert(false, s"failed counting statements in a processor: ${e.getMessage}")
      case Success(size) => assert(size == 1, s"found wrong number of statements in a processor, expected 1: count = $size")
    }
    newSet.sparqlSelect("SELECT * WHERE { <http://example.com#subject> <http://example.com#predicate> <http://example.com#object> . }") match {
      case Failure(e) => assert(false, s"failed selecting statement from a processor: ${e.getMessage}")
      case Success(iterator) =>
        val size = iterator.size
        assert(size == 1, s"found wrong number of statements in selection, expected 1: count = $size")
    }
  }

  it should "only allow selection of actual statements" in {
    val newSet = empty
    newSet.sparqlUpdate("INSERT { <http://example.com#subject> <http://example.com#predicate> <http://example.com#object> . } WHERE {}") match {
      case Failure(e) => assert(false, s"failed inserting statement into an empty processor: ${e.getMessage}")
      case Success(nothing) => // ignore
    }
    newSet.longSize match {
      case Failure(e) => assert(false, s"failed counting statements in a processor: ${e.getMessage}")
      case Success(size) => assert(size == 1, s"found wrong number of statements in a processor, expected 1: count = $size")
    }
    newSet.sparqlSelect("SELECT * WHERE { <http://example.com#nosuch> <http://example.com#predicate> <http://example.com#object> . }") match {
      case Failure(e) => assert(false, s"failed selecting statement from a processor: ${e.getMessage}")
      case Success(iterator) =>
        val size = iterator.size
        assert(size == 0, s"found wrong number of statements in selection, expected 0: count = $size")
    }
  }

  it should "allow 'ASK' queries" in {
    val newSet = empty
    newSet.sparqlUpdate("INSERT { <http://example.com#subject> <http://example.com#predicate> <http://example.com#object> . } WHERE {}") match {
      case Failure(e) => assert(false, s"failed inserting statement into an empty processor: ${e.getMessage}")
      case Success(nothing) => // ignore
    }
    newSet.longSize match {
      case Failure(e) => assert(false, s"failed counting statements in a processor: ${e.getMessage}")
      case Success(size) => assert(size == 1, s"found wrong number of statements in a processor, expected 1: count = $size")
    }
    newSet.sparqlAsk("ASK { <http://example.com#subject> <http://example.com#predicate> <http://example.com#object> . }") match {
      case Failure(e) => assert(false, s"failed 'ASK' query from a processor: ${e.getMessage}")
      case Success(bool) => assert(bool, s"found wrong result to 'ASK' query, expected true: result = $bool")
    }
    newSet.sparqlAsk("ASK { <http://example.com#nosuch> <http://example.com#predicate> <http://example.com#object> . }") match {
      case Failure(e) => assert(false, s"failed 'ASK' query from a processor: ${e.getMessage}")
      case Success(bool) => assert(!bool, s"found wrong result to 'ASK' query, expected false: result = $bool")
    }
  }

  it should "allow 'DESCRIBE' queries" in {
    val newSet = empty
    newSet.sparqlUpdate("INSERT { <http://example.com#subject> <http://example.com#predicate> <http://example.com#object> . } WHERE {}") match {
      case Failure(e) => assert(false, s"failed inserting statement into an empty processor: ${e.getMessage}")
      case Success(nothing) => // ignore
    }
    newSet.longSize match {
      case Failure(e) => assert(false, s"failed counting statements in a processor: ${e.getMessage}")
      case Success(size) => assert(size == 1, s"found wrong number of statements in a processor, expected 1: count = $size")
    }
    newSet.sparqlDescribe("DESCRIBE <http://example.com#subject>") match {
      case Failure(e) => assert(false, s"failed 'DESCRIBE' query from a processor: ${e.getMessage}")
      case Success(iterator) =>
        val size = iterator.size
        assert(size == 1, s"found wrong number of statements in 'DESCRIBE' results, expected 1: count = $size")
    }
    newSet.sparqlDescribe("DESCRIBE <http://example.com#nosuch>") match {
      case Failure(e) => assert(false, s"failed 'DESCRIBE' query from a processor: ${e.getMessage}")
      case Success(iterator) =>
        val size = iterator.size
        assert(size == 0, s"found wrong number of statements in 'DESCRIBE' results, expected 0: count = $size")
    }
  }

}
