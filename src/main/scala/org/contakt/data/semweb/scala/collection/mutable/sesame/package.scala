package org.contakt.data.semweb.scala.collection.mutable

import org.openrdf.query.QueryResult
import org.openrdf.repository.RepositoryResult

/**
 * Utility code for working with Sesame RDF library.
 */
package object sesame {

  class QueryResultIterator[T](results: QueryResult[T]) extends Iterator[T] {

    def hasNext: Boolean = results.hasNext

    def next(): T = results.next

  }

  object QueryResultIterator {

  	def apply[T](results: QueryResult[T]) = new QueryResultIterator[T](results)
  	
  }

  class RepositoryResultIterator[T](results: RepositoryResult[T]) extends Iterator[T] {

    def hasNext: Boolean = results.hasNext

    def next(): T = results.next

  }

  object RepositoryResultIterator {

  	def apply[T](results: RepositoryResult[T]) = new RepositoryResultIterator[T](results)
  	
  }

}
