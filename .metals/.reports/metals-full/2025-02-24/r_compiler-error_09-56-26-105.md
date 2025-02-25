file://<WORKSPACE>/src/test/java/com/coliship/demo/student/StudentRepoTest.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 441
uri: file://<WORKSPACE>/src/test/java/com/coliship/demo/student/StudentRepoTest.java
text:
```scala
package com.coliship.demo.student;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentRepoTest {
    @Autowired
    private StudentRepo repo;

    public void findAllTest(){
        var result = repo.findAll();

		assertThat(result).ha@@
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
	dotty.tools.pc.completions.CompletionProvider.completions(CompletionProvider.scala:50)
	dotty.tools.pc.ScalaPresentationCompiler.complete$$anonfun$1(ScalaPresentationCompiler.scala:146)
```
#### Short summary: 

java.util.NoSuchElementException: next on empty iterator