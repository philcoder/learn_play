# Learn-Play

This is a starter application that shows how Play works. Please see the documentation at https://www.playframework.com/documentation/latest/Home for more details.
## Prepare Machine (install Java SDK 8 and SBT)

Install SBT component on linux debian env. (tested on ubuntu 18.04 lts)

```
echo "deb https://dl.bintray.com/sbt/debian /" | sudo tee -a /etc/apt/sources.list.d/sbt.list
curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x2EE0EA64E40A89B84B2DF73499E82A75642AC823" | sudo apt-key add
sudo apt-get update
sudo apt-get install sbt
```

When you type sbt -version you will see:

sbt version in this project: 1.3.2 (or your version)
sbt script version: 1.3.3 (or your version)

## Use IDEA Community Edition

Inside folder project type:
```
sbt

> compile
> exit
```

- Import code: In IDEA go to File -> New -> Project From Existing Sources -> Select the project folder -> Select sbt (import project from external model)

- Run project: 
1. Create a new Run Configuration – From the main menu, select Run -> Edit Configurations
2. Click on the + to add a new configuration
3. From the list of configurations, choose “sbt Task”
4. In the “tasks” input box, simply put “run”
5. Apply changes and select OK.
6. Now you can choose “Run” from the main Run menu and run your application

## Running (without IDEA)

Run this using [sbt](http://www.scala-sbt.org/).  If you downloaded this project from <http://www.playframework.com/download> then you'll find a prepackaged version of sbt in the project directory:

```bash
sbt run
```

And then go to <http://localhost:9000> to see the running web application.

There are several demonstration files available in this template.

## Running Docker Services with NoSQL, Postgres and etc.
Go inside clone folder and execute follows commands.

For startup all services
```
docker-compose up -d --build
```
For stop all services
```
docker-compose down
```

For stop and cleanup volumes
```
docker-compose down --volumes
```

Access pgAdmin: http://localhost:20000

