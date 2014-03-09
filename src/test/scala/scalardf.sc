// Scratch area

import java.math.BigInteger
import org.contakt.data.scala.collection.mutable.UnorderedSequentialHashSet
import org.openrdf.model.impl.{IntegerLiteralImpl, URIImpl, StatementImpl}
import org.openrdf.model.Statement
val elem1: Statement = new StatementImpl(
  new URIImpl("http://www.example.org/subject1"),
  new URIImpl("http://www.example.org/predicate1"),
  new URIImpl("http://www.example.org/object1")
)

val elem2: Statement = new StatementImpl(
  new URIImpl("http://www.example.org/subject2"),
  new URIImpl("http://www.example.org/predicate2"),
  new IntegerLiteralImpl(new BigInteger("2"))
)

val elem3: Statement = new StatementImpl(
  new URIImpl("http://www.example.org/subject2"),
  new URIImpl("http://www.example.org/predicate1"),
  new URIImpl("http://www.example.org/object3")
)

val newSet = new UnorderedSequentialHashSet[Statement]()
newSet += (elem1, elem2, elem3)
println("#0")

println(elem1)

println("#1")

println(newSet.mkString)


println("#2")

println(newSet.mkString(";;; "))


println("#2")

println(newSet.mkString("{ ", ";;; ", " }"))



