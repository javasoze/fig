#!/bin/bash

if [ $# -lt 1 ];
then
  echo "USAGE: $0 classname [opts]"
  exit 1
fi

base_dir=$(dirname $0)/..

if [ ! -d "$base_dir/lib" ]; then
  echo "You are running this script from the wrong directory. Please run 'mvn package' and then unzip the example zip file in the target directory."
  exit 1
fi

for file in $base_dir/lib/*.[jw]ar;
do
  CLASSPATH=$CLASSPATH:$file
done

if [ -z "$JAVA_HOME" ]; then
  JAVA="java"
else
  JAVA="$JAVA_HOME/bin/java"
fi

$JAVA -cp $CLASSPATH $@
