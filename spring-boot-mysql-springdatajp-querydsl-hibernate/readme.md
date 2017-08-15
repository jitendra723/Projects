## Using MySQL in Spring Boot via Spring Data JPA using querydsl and Hibernate


### Build and run

#### Configurations

Open the `application.properties` file and set your own configurations.

#### Prerequisites

- Java 8
- Maven > 3.0

#### From terminal

Go on the project's root folder, then type:

    $ mvn spring-boot:run

#### From Eclipse
Note:Set Vm option in eclipse.ini before running in eclipse.
-vm
C:\Program Files\Java\jdk1.8.0_91\bin\javaw.exe

Import as *Existing Maven Project* and run it as *Spring Boot App*.


### Usage

- Run the application and go on http://localhost:8080/
- Use the following urls to invoke controllers methods and see the interactions
  http://localhost:8080/myusers?search=lastName:name1,age>20
