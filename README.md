# PayMyBuddy
**Money exchange with buddies**

This app is develop to manage money exchange between users :
- Add or withdraw money to your balance easily with no fee.
- Send and receive money between users.


## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development
and testing purposes.</br>
See deployment for notes on how to deploy the project on a live system.


### App built with
What things you need to install this App

- Java 11
- SpringBoot
- mySql
- Jpa Repositor
- Thymeleaf
- Maven
- JUnit 5
- Jacoco
- Log4j


### Installing
A step by step series of examples that tell you how to get a development env running:

1.Install Java:
https://docs.oracle.com/javase/11/docs/technotes/guides/install/install_overview.html

2.Install Maven:
https://maven.apache.org/install.html

3.Install SpringBoot:
https://spring.io/projects/spring-boot

4.Install mySql:
https://www.mysql.com/downloads/

After downloading and installing it, no specific setup required.


### App Data
You will have to set up your own data base.

Schemas are provided in folder :

```shell
/DiagramUML
```

**CLASS DIAGRAM**

<img src="https://github.com/xGuix/PayMyBuddy/blob/feature/tests/DiagramUML/DomainModel_UML.jpg" alt="classDiagram"/>


**UML DATA**

<img src="https://github.com/xGuix/PayMyBuddy/blob/feature/tests/DiagramUML/ModelPhysique_UML.jpg" alt="dataDiagram"/>


### Start up
You will have to set up SQL tables.

Scripts are provided in folders :

```shell
/ScriptSQL
```

First run script for data structure

```shell
ScriptSQL-architectureData.sql
```

Setup your config in application.properties file
Launch with :

```shell
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.datasource.username=root --spring.datasource.password=rootroot"
```


## URL Access

Open your browser and get localhost:8080

```html
https://localhost:8080/
```


### Testing
The existing tests need to be triggered from maven-surefire plugin while we try to generate the final executable jar file.
Run the tests from maven, go to the folder that contains the pom.xml file and execute the below command.

* Run unit tests, use command:

```shell
mvn:test
```

* Run integration tests, use command:

```shell
mvn failsafe:integration-test
```

* Run all tests, use command:

```shell
mvn verify
```


## Reports
Maven site to get all reports:

- **SureFire Report** for all unit Tests.
- **Jacoco Report** for tests coverage.
- **SpotBugs Report** for find bugs. 

Run build site, use command:

```shell
mvn site
```

Access file directory : `target/site` 
Run the `index.html` in your web browser.


## Jacoco Coverage
Jacoco coverage is automatically done with tests.

Access file directory : `target/site/jacoco/index.html`

