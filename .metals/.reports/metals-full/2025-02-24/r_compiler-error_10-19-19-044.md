file://<WORKSPACE>/src/main/java/com/coliship/demo/realTime/core/PekkoClusterSharding.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
uri: file://<WORKSPACE>/src/main/java/com/coliship/demo/realTime/core/PekkoClusterSharding.java
text:
```scala
package com.coliship.demo.realTime.core;

import org.apache.pekko.cluster.sharding.typed.javadsl.ClusterSharding;
import org.apache.pekko.cluster.sharding.typed.javadsl.Entity;

import com.coliship.demo.realTime.core.behaviors.ShardBehavior;

public class PekkoClusterSharding {

    private final ClusterSharding clusterSharding;

    public PekkoClusterSharding(ClusterSharding clusterSharding) {this.clusterSharding = clusterSharding;}

    public ClusterSharding getClusterSharding() {
        if (clusterSharding == null) {
            throw new IllegalStateException("ClusterSharding is not configured");
        }
        return clusterSharding;
    }

    public <T> void initEntity(ShardBehavior<T> shardBehavior) {
        assert clusterSharding != null;
        clusterSharding.init(
                Entity.of(shardBehavior.getEntityTypeKey(), shardBehavior::create)
                        .withMessageExtractor(shardBehavior.extractor())
        );
    }
}


```



#### Error stacktrace:

```
scala.collection.Iterator$$anon$19.next(Iterator.scala:973)
	scala.collection.Iterator$$anon$19.next(Iterator.scala:971)
	scala.collection.mutable.MutationTracker$CheckedIterator.next(MutationTracker.scala:76)
	scala.collection.IterableOps.head(Iterable.scala:222)
	scala.collection.IterableOps.head$(Iterable.scala:222)
	scala.collection.AbstractIterable.head(Iterable.scala:935)
	dotty.tools.dotc.interactive.InteractiveDriver.run(InteractiveDriver.scala:164)
	dotty.tools.pc.MetalsDriver.run(MetalsDriver.scala:45)
	dotty.tools.pc.WithCompilationUnit.<init>(WithCompilationUnit.scala:31)
	dotty.tools.pc.SimpleCollector.<init>(PcCollector.scala:345)
	dotty.tools.pc.PcSemanticTokensProvider$Collector$.<init>(PcSemanticTokensProvider.scala:63)
	dotty.tools.pc.PcSemanticTokensProvider.Collector$lzyINIT1(PcSemanticTokensProvider.scala:63)
	dotty.tools.pc.PcSemanticTokensProvider.Collector(PcSemanticTokensProvider.scala:63)
	dotty.tools.pc.PcSemanticTokensProvider.provide(PcSemanticTokensProvider.scala:88)
	dotty.tools.pc.ScalaPresentationCompiler.semanticTokens$$anonfun$1(ScalaPresentationCompiler.scala:109)
```
#### Short summary: 

java.util.NoSuchElementException: next on empty iterator