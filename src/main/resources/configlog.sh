#!/bin/sh


curl http://localhost:8080/crossoverbackup/agent/configuration?sourceIP=10.135.140.11

#:<<COMM
curl -X POST -H "Content-Type: application/json"  -d @- http://localhost:8080/crossoverbackup/agent/configlog <<-EOF
{
"log" : "finished",
"srcip" : "10.135.140.11",
"configid" : 1,
"status": 2
}
EOF
#COMM
