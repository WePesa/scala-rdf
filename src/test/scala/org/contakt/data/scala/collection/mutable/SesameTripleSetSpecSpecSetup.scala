package org.contakt.data.scala.collection.mutable

import java.math.BigInteger
import org.openrdf.model.Statement
import org.contakt.data.semweb.scala.collection.mutable.sesame.{DefaultSesameTripleSet, SesameTripleSet}
import org.apache.log4j.{BasicConfigurator, Level, Logger}
import org.openrdf.model.impl.{IntegerLiteralImpl, StatementImpl, URIImpl}

/**
 * Trait for implementation specific setup of Scala 'Set' types,
 * in this case for SesameTripleSet.
 * 'T' is the element type of the Set implementation,
 * and 'TSet' is the class implementing 'Set[T]' that should be used.
 */
trait SesameTripleSetSpecSpecSetup extends SetSpecSetup[Statement, SesameTripleSet] {

  override def beforeSetup() = {
    BasicConfigurator.configure()
    Logger.getRootLogger.setLevel(Level.INFO)
  }

  /**
   * Returns an empty set.
   *
   * @return an empty set.
   */
  def empty = DefaultSesameTripleSet.empty

  /** Returns a new element of type Statement. */
  def newElem1: Statement = new StatementImpl(
    new URIImpl("http://www.example.org/subject1"),
    new URIImpl("http://www.example.org/predicate1"),
    new URIImpl("http://www.example.org/object1")
  )

  /** Returns a different new element of type Statement. */
  def newElem2: Statement = new StatementImpl(
    new URIImpl("http://www.example.org/subject2"),
    new URIImpl("http://www.example.org/predicate2"),
    new IntegerLiteralImpl(new BigInteger("2"))
  )

}