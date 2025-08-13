
#!/bin/bash
# Uso: ./test.sh [--select-method ...] [--select-class ...] [otros args]

mvn clean test-compile || exit 1

CP="target/classes:target/test-classes:$(mvn -q dependency:build-classpath -Dmdep.outputFile=/dev/stdout | tail -1)"

java -cp "$CP" com.automationexercise.RunWithDisplayNameListener "$@"
