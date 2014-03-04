README for scala-rdf
====================

Experimental Scala API for Sesame RDF library, with a focus on composability of chains of SPARQL queries.

Overview
--------

An RDF "triple store" is essentially a set of RDF statements (often triples, possible quads, etc.).
For Scala, it makes sense to present RDF repositories a mutable Set[Statement] interface.
Additionally, for multithreaded use (e.g. with futures & reactive Scala, or with Akka),
it makes sense to be able to present in-memory temporary stores of triples via an *immutable* Set[Statement] interface.

Results of queries should be made available via futures, since a query could take an indeterminate amount of time
depending on the type and size of triple store being queried.

Implementation
--------------

The initial implementation of this code is for Sesame (http://www.openrdf.org/).
It proved too difficult to implement Scala's mutable 'Set' trait directly, due to the complexity of the dependencies
among the Scala collection classes.  Instead, a trait with a subset of the 'Set' methods, 'UnorderedSequentialSet',
is being implemented initially.