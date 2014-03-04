package org.contakt.data.scala.collection.mutable

import org.openrdf.model.Statement
import org.contakt.data.semweb.scala.collection.mutable.sesame.SesameTripleSet

/**
 * Test class to apply generic 'UnorderedSequentialSet' tests to the SesameTripleSet class.
 */
class SesameTripleSetSpec extends UnorderedSequentialSetSpec[Statement, SesameTripleSet] with SesameTripleSetSpecSetup with SparqlProcessorSpec[SesameTripleSet] {}
