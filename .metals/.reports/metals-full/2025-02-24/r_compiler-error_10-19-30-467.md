file://<WORKSPACE>/src/main/java/com/coliship/demo/realTime/core/DependencyContainer.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
uri: file://<WORKSPACE>/src/main/java/com/coliship/demo/realTime/core/DependencyContainer.java
text:
```scala
package com.coliship.demo.realTime.core;

import java.util.List;


import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import com.coliship.demo.realTime.core.behaviors.RootBehavior;
import com.coliship.demo.realTime.core.behaviors.SingletonBehavior;

@Component
public class DependencyContainer {
	private final GenericApplicationContext applicationContext;
	private final RootBehavior rootBehavior;
	private final List<SingletonBehavior<?>> singletonBehaviors;
	public DependencyContainer(
            GenericApplicationContext applicationContext,
            RootBehavior rootBehavior,
            List<SingletonBehavior<?>> singletonBehaviors) {
		this.applicationContext = applicationContext;
		this.rootBehavior = rootBehavior;
		this.singletonBehaviors = singletonBehaviors;
    }

	public GenericApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public RootBehavior getRootBehavior() {
		return rootBehavior;
	}

	public List<SingletonBehavior<?>> getSingletonBehaviors() {
		return singletonBehaviors;
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