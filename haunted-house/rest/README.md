Run mvn clean install && cd rest && mvn cargo:run

curl -i -X GET http://localhost:8080/pa165/rest/bogeymen

curl -i -X GET http://localhost:8080/pa165/rest/bogeymen/1

curl -X POST -i -H "Content-Type: application/json" --data '{"name":"oldWitch","description":"Throws fire","type":"WITCH","formHouse":"House 1"}' http://localhost:8080/pa165/rest/bogeymen

curl -i -X DELETE http://localhost:8080/pa165/rest/bogeymen/1
