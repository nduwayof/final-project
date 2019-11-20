#!/bin/bash

echo "substituting environment variables and generating yaml ..."


for f in *.template
do
	echo "Processing $f"

	# output file name: *.yaml
	output=$(awk '{split($0, a, "."); print a[1]"."a[2]}'<<< $f)

	echo "writing to: $output"
	cat $f| envsubst > ../$output
done