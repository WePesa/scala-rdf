package org.contakt.data.scala.collection.mutable

import org.openrdf.model.Statement
import org.contakt.data.semweb.scala.collection.mutable.sesame.SesameTripleSet
import org.apache.log4j.{BasicConfigurator, Level, Logger}

/**
 * Trait for implementation specific setup of 'UnorderedSequentialSet' types,
 * in this case for SesameTripleSet.
 */
trait SesameTripleSetSpecSetup extends UnorderedSequentialSetSpecSetup[Statement, SesameTripleSet] with StatementTestData {

  override def beforeSetup() = {
    BasicConfigurator.configure()
    Logger.getRootLogger.setLevel(Level.INFO)
  }

  /**
   * Returns an empty set.
   *
   * @return an empty set.
   */
  def empty = SesameTripleSet.empty

}