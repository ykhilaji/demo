file://<WORKSPACE>/src/main/java/com/coliship/demo/realTime/core/service/ActorClusterService.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 953
uri: file://<WORKSPACE>/src/main/java/com/coliship/demo/realTime/core/service/ActorClusterService.java
text:
```scala
package com.coliship.demo.realTime.core.service;

import org.apache.pekko.actor.typed.ActorSystem;
import org.apache.pekko.cluster.ClusterEvent.CurrentClusterState;
import org.apache.pekko.cluster.sharding.typed.javadsl.EntityRef;
import org.apache.pekko.cluster.sharding.typed.javadsl.EntityTypeKey;
import org.springframework.stereotype.Component;

import com.coliship.demo.realTime.core.PekkoCluster;
import com.coliship.demo.realTime.core.PekkoClusterSharding;

@Component
public class ActorClusterService {

    private final ActorSystem<?> actorSystem;
    private final PekkoCluster cluster;
    private final PekkoClusterSharding clusterSharding;

    private final boolean clusterConfigured;

    public ActorClusterService(
            ActorSystem<?> actorSystem,
            PekkoCluster pekkoCluster,
            PekkoClusterSharding clusterSharding) {
        this.actorSystem = actorSystem;
        this.cluster = pekkoCluster;
        thi@@s.clusterSharding = clusterSharding;
        this.clusterConfigured = pekkoCluster.isClusterConfigured();
    }

    public <T> EntityRef<T> getShardedActor(EntityTypeKey<T> entityTypeKey, String entityId) {
        return clusterSharding.getClusterSharding().entityRefFor(entityTypeKey, entityId);
    }

    public CurrentClusterState getClusterState() {
        if (!clusterConfigured) throw new RuntimeException("Cluster isn't configured");
        return cluster.state();
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
	dotty.tools.pc.HoverProvider$.hover(HoverProvider.scala:40)
	dotty.tools.pc.ScalaPresentationCompiler.hover$$anonfun$1(ScalaPresentationCompiler.scala:376)
```
#### Short summary: 

java.util.NoSuchElementException: next on empty iterator