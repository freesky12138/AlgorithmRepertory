#!/usr/bin/env bash


CREATE PROCEDURE pro()
BEGIN
declare i int;
set i=0;
while i<50 do
insert into
	test_table
set code=i,
tname=i;
set i=i+1;
end while;
end;

call pro();