package org.contakt.data.scala.collection.mutable

import org.contakt.data.semweb.scala.collection.mutable.sesame.{SparqlProcessor, SesameTripleSet}

/**
 * Trait for implementation specific setup of 'SparqlProcessor' types,
 * which allows the same tests to work for different implementations.
 * 'Proc' is the class implementing 'SparqlProcessor' that should be used.
 */
trait SparqlProcessorSpecSetup[Proc <: SparqlProcessor] {

	/**
	 * Returns an empty SPARQL processor.
	 *
	 * @return an empty SPARQL processor.
	 */
	def emptyProc: Proc = SesameTripleSet.empty.asInstanceOf[Proc]

}