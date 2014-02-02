package org.contakt.data.scala.collection.mutable

import org.openrdf.model.Statement
import org.contakt.data.semweb.scala.collection.mutable.sesame.{SesameTripleSet, DefaultSesameTripleSet}
import org.apache.log4j.{BasicConfigurator, Level, Logger}

/**
 * Trait for implementation specific setup of Scala 'Set' types,
 * in this case for SesameTripleSet.
 * 'T' is the element type of the Set implementation,
 * and 'TSet' is the class implementing 'Set[T]' that should be used.
 */
trait SesameTripleSetSpecSpecSetup extends SetSpecSetup[Statement, SesameTripleSet] {

  override def beforeSetup = {
    BasicConfigurator.configure
    Logger.getRootLogger.setLevel(Level.INFO)
  }

  /**
   * Returns an empty set.
   *
   * @return an empty set.
   */
  def empty = DefaultSesameTripleSet.empty

}