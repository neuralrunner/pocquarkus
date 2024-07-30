# learning-quarkus-demo

## Small POC project, concept using LinkedIn Learning - by Frank P Moley III
*With .bat scripts for Windows users.*

This POC, project uses Quarkus, the Supersonic Subatomic Java Framework.
If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

The project is made of the Main Module(Learning-quarkus-demo)
And other Modules:
### cmd-app: A module to run some queries on the Quarkus Command, which is pretty good for Cronjobs and other specific processing. It was done to run with Postgres.
### cmd-cassandra: same thing as the first module but to run with Cassandra.
### room-service: A Main RESTFUL Service, that uses the majority of the examples and the POC of this learning activities. It runs on Cassandra, has Jaeger configured with the log pattern of quarkus-smallrye-opentracing, Swagger, Healthchecks with an implementation of a Readiness Probe and Metrics.
### room-batch: A simple Rest Client.
### room-service-graphql: A Simple implementation of a GraphQL module. 

## Requirements of Running
cmd-app: Requires Postgres to be Running(with the provided scripts on /bin).
cmd-cassandra: Requires Cassandra to be Running(with the provided scripts on /bin).
room-service-graphql: Requires Cassandra to be Running(with the provided scripts on /bin).
room-service: Requires Cassandra and Jaeger to be Running(with the provided scripts on /bin).  
room-batch: requires room-service to be running.

# Note Before Run
Use the scripts at the bin folder. You will need Docker installed in order to run these.

## Note: If you are on windows, use the .bat file, on other Unix based, use the .sh files.

### start_postgres: Download a Docker Container of PostGreSQL and uses the scripts(data and schema) to populate it.
### stop_postgres: Just a quality of life script to stop the Container running.
### start_cassandra: Download a Docker Container of Cassandra NoSQL database, uses the scripts to populate the database itself. 
### stop_cassandra: Just a quality of life script to stop the Container running.
### start_jaeger: Download a Docker Container of Jaeger for using in Tracing logs(mainly used in the room-service application)
### stop_jaeger: Just a quality of life script to stop the Container running.


