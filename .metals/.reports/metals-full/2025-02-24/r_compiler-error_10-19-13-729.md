file://<WORKSPACE>/src/main/java/com/coliship/demo/ServiceColishipApplication.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 487
uri: file://<WORKSPACE>/src/main/java/com/coliship/demo/ServiceColishipApplication.java
text:
```scala
package com.coliship.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.coliship.demo.student.Student;
import com.coliship.demo.student.StudentRepo;

@SpringBootApplication
public class ServiceColishipApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceColishi@@pApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(StudentRepo repository) {
		return args -> {
			for (int i = 0; i < 3000; i++) {
				repository.save(
          Student.builder()
            .firstname("Test" + i)
            .lastname("test" + i)
            .age(i)
            .build()
				).subscribe();
			}
		};
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