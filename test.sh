#!/bin/bash
host=${1:-http://localhost:8080}
login="${host}/api/login"
logout="${host}/api/logout"
check="${host}/api/is-authenticated"

user=${2:-test}
pass=${3:-test}

echo "POST $login"
login_res=$(curl -d "{\"username\":\"${user}\", \"password\":\"${pass}\"}" -s -H "Content-Type: application/json" -X POST $login)
exp='\{"session":[ ]*"([a-zA-Z0-9\-]*)"\}'

session=$(echo "$login_res" | sed -r "s/^$exp/\1/")


echo "login session id is $session"
echo "GET $check"
curl -o /dev/null -s -w "%{http_code}\n" -d "{\"session\":\"$session\"}" -H "Content-Type: application/json" -X GET $check
echo "PUT $logout"
curl -o /dev/null -s -w "%{http_code}\n" -d "{\"session\":\"$session\"}" -H "Content-Type: application/json" -X PUT $logout
echo "GET $check"
curl -o /dev/null -s -w "%{http_code}\n" -d "{\"session\":\"$session\"}" -H "Content-Type: application/json" -X GET $check


#curl -d '{"session":"32a48abf-4fe9-4254-b479-6386dbb10c2f"}' -H "Content-Type: application/json" -X GET http://localhost:8080/api/is-authenticated -i
#curl -d '{"session":"32a48abf-4fe9-4254-b479-6386dbb10c2f"}' -H "Content-Type: application/json" -X PUT http://localhost:8080/api/logout -i