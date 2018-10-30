#!/usr/bin/env bash
echo this is a test
echo 'this is "abc" test'

echo $HOME
HOME='/home/huppert'

echo $HOME

testing=$(date)
echo "this is time" $testing

date > testfile

#rpm -qa | sort | more

var1=123
var1=$(expr $var1 + $var1)
var1=$(expr $var1 \* 2)
echo $var1

var1=$[ $var1 / $var1]
echo $var1

for(( i=1;i<5;i++))
do
	if [ $i -gt 3 ]
	then		continue
	elif [ $i -lt 2 ]
	then		echo -n  "i $i"
	else
		echo "$i"
	fi
done

exit
