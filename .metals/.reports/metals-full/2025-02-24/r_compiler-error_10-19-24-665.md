file://<WORKSPACE>/src/main/java/com/coliship/demo/realTime/core/PekkoConfigurationUtils.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
uri: file://<WORKSPACE>/src/main/java/com/coliship/demo/realTime/core/PekkoConfigurationUtils.java
text:
```scala
package com.coliship.demo.realTime.core;


import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class PekkoConfigurationUtils {

	public static String toPropertiesString(PekkoConfiguration config) {
		Map<String, Object> map = toMap(config);
		StringBuilder propertiesString = new StringBuilder();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			final String key = entry.getKey();
			final Object value = entry.getValue();
			if (value instanceof String[]) {
				final String[] listValues = (String[]) value;
				propertiesString.append(key).append(" = [\n");
				for (String listValue : listValues) {
					propertiesString.append("    \"").append(listValue).append("\",\n");
				}
				propertiesString.append("]\n");
			} else {
				propertiesString.append(key).append(" = \"").append(value).append("\"\n");
			}
		}
		return propertiesString.toString();
	}

	private static Map<String, Object> toMap(PekkoConfiguration config) {
		Map<String, Object> propertiesMap = new HashMap<>();
		populateMap(config, propertiesMap, "pekko");
		return propertiesMap;
	}

	private static void populateMap(Object obj, Map<String, Object> map, String prefix) {
		if (obj == null) {
			return;
		}

		Method[] methods = obj.getClass().getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().startsWith("get") && method.getParameterCount() == 0) {
				try {
					Object value = method.invoke(obj);
					if (value != null) {
						String propertyName = prefix + "." + toKebabCase(method.getName().substring(3));
						if (value instanceof String || value instanceof Number || value instanceof Boolean) {
							map.put(propertyName, value.toString());
						} else if (value instanceof String[]) {
							map.put(propertyName, value);
						} else {
							populateMap(value, map, propertyName);
						}
					}
				} catch (Exception e) {
					//
				}
			}
		}
	}

	private static String toKebabCase(String str) {
		if (str == null || str.isEmpty()) {
			return str;
		}
		StringBuilder result = new StringBuilder();
		result.append(Character.toLowerCase(str.charAt(0)));
		for (int i = 1; i < str.length(); i++) {
			char c = str.charAt(i);
			if (Character.isUpperCase(c)) {
				result.append('-').append(Character.toLowerCase(c));
			} else {
				result.append(c);
			}
		}
		return result.toString();
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