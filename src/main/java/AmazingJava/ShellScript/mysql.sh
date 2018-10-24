#!/usr/bin/env bash
mysqlDir="/home/huppert/mysql"

if [ ! -d "$mysqlDir" ];then
    mkdir "$mysqlDir"
fi

cd "$mysqlDir"

exit