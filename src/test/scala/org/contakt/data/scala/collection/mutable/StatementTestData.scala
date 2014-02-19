package org.contakt.data.scala.collection.mutable

import java.math.BigInteger
import org.openrdf.model.Statement
import org.openrdf.model.impl.{IntegerLiteralImpl, StatementImpl, URIImpl}

/** Sesame 'Statement' test data. */
trait StatementTestData {
    
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
    
  /** Returns a new element of type Statement. */
  def newElem3: Statement = new StatementImpl(
    new URIImpl("http://www.example.org/subject2"),
    new URIImpl("http://www.example.org/predicate1"),
    new URIImpl("http://www.example.org/object3")
  )

}