file://<WORKSPACE>/src/test/java/com/coliship/demo/realTime/Task/repository/TaskRepoTest.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 686
uri: file://<WORKSPACE>/src/test/java/com/coliship/demo/realTime/Task/repository/TaskRepoTest.java
text:
```scala
package com.coliship.demo.realTime.Task.repository;

import com.coliship.demo.realTime.Task.model.TaskModel;
import com.coliship.demo.realTime.Task.model.TaskStatus;
import com.coliship.demo.realTime.Task.model.TaskType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = InfrastructureConfiguration.class)
class TaskRepoTest {

    @Autow@@ired
    private TaskRepo taskRepo;

  

    @Test
    void testFindAll() {
        String searchTerm = "label";
        
        Flux<TaskModel> result = taskRepo.findAll();
        
        StepVerifier.create(result)
            .expectNextMatches(task -> {
                assertThat(task.getTaskType().toString()).containsIgnoringCase(searchTerm);
                return true;
            })
            .expectComplete()
            .verify();
    }

    @AfterEach
    void cleanUp() {
        // Delete all test data
        taskRepo.deleteAll().block();
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