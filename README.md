
### CI with TeamCity and Dokcer

Working with TeamCity + Docker to setup a CI pipeline for a Java REST app

#### Java REST App
Here is the Docker command to run the app with a Tomcat 8.0 server. Please take care that we have to use Java 1.7 because of this specifical Tomcat version. I added the war copy command, so our app will be deployed automatically. 
```sh
docker run --rm -v /your_path/java-teamcity_test/java test/target/javatest.war:/usr/local/tomcat/webapps/javatest.war -it -p 8585:8080 --name=tomcat tomcat:8.0
```

If you need to check the deployment, logs, or setup of Tomcat container use this command:
```sh
docker exec -it tomcat /bin/bash
```

Test the app going to:
```sh
http://localhost:8585/javatest
```
I added two more rest services a dummy hello call */javatest/hello* and a test/id validation *test/error*.

#### TeamCity Server 
First create two folders */data* and */logs* to storage what TC needs. This image will take a while to download (current local size used: 1.52GB).
```sh
docker run -it --name tcserver -v /your_path/teamcity/data:/data/teamcity_server/datadir -v /your_path/teamcity/logs:/opt/teamcity/logs -p 8111:8111 jetbrains/teamcity-server
```
I'm using the internal HSQLDB database provided by the image to avoid futher setup, if you have time use an external option!

#### TeamCity Agent 
There is two version for agents, in my case, I used the standard one, you can try with the minimal. The standard version is another heavy image to download, so be patient (current local size used: 1.03GB).
```sh
docker run --name tcagent -it -e SERVER_URL="your_ip:8111" -p 9090:9090 -v /your_path/teamcity/agent:/data/teamcity_agent/conf jetbrains/teamcity-agent
```
TeamCity Agents will not work with *localhost* as SERVER_URL, so please use your IP. If you change your IP or network connection, modify *serverUrl* inside buildAgent.properties then restart the docker
```sh
docker start tcagent
```

#### Adding local agent to the server
The final step, you have to authorize manually your local agent here:
```sh
http://localhost:8111/agents.html?tab=unauthorizedAgents
```

#### Deploying the artifact to a Tomcat container

##### Tomcar container
In the new version on TeamCity is possible to deploy directly to a container locally or remote, we are going to use the same Tomcat 8.0 image, but adding context and user setup. Go to */tomcat* folder and then build the Dockerfile.
```sh
docker build -t twogg/tomcat .
```
Now run the container, we re using admin/admin as user and password, and 8787 is going to be our deploy port for TeamCity.
```sh
docker run -d -p 8787:8080 --name tctomcat twogg/tomcat
```
