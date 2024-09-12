# Description
Repository contains two apps. First (SmartMeterReader) is springboot app whis is periodicly read data from smart meter, process it, validate it and store into database.
For proper run we have to first register all smart meters via POST request.

Second app (DummyReader) is very simple node.js server which after run register all smart meters. And when server handle GET, then return randomly generated data for last hour. 

## Prerequisites
* Java 17
* Maven
* node.js

## Prepare app
* Execute the command ```mvn clean install package``` in the SmartMeterReader (there is pom.xml)
* Execute the commands ```npm install express``` and ```npm install axios``` in DummyReader (there is dummyReader.js)

## Run apps
* First we have to run springboot app (for communication simulation we have to regiter smart meters)
* Execute the command ```java -jar SmartMeterReader-0.0.1-SNAPSHOT.jar``` in target folder where is placed SmartMeterReader-0.0.1-SNAPSHOT.jar
* Execute the command ```node .\dummyReader.js``` in DummyReader folder where is placed dummyReader.js
