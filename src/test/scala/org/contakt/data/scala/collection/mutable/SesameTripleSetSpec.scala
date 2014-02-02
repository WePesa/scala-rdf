package org.contakt.data.scala.collection.mutable

import org.openrdf.model.Statement
import org.contakt.data.semweb.scala.collection.mutable.sesame.SesameTripleSet

/**
 * Test class to apply generic Set tests to the SesameTripleSet class.
 */
class SesameTripleSetSpec extends SetSpec[Statement, SesameTripleSet] with SesameTripleSetSpecSpecSetup {}
