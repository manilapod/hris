# Human Resource Information System (HRIS)
HRIS provides console based human resource management features.

## Development Notes

If you are setting up this project in your local environment, here are the things you need to consider:

### Pre-requisites
You need to install the following software:
1. MySQL Server
2. MySQL Workbench
3. IntelliJ
4. Git
5. Github account
6. Postman (recommended for testing the REST APIs)

### Checkout Project from GitHub

Clone this project from GitHub into your local directory. If you are not sure how to do that, here is the 
[documentation](https://docs.github.com/en/repositories/creating-and-managing-repositories/cloning-a-repository).

### Importing Project into IntelliJ

After you have cloned the project, open IntelliJ. Go to `File` menu -> `Open` -> Browser to the [pom.xml](pom.xml) file 
of this repository in your file system -> `Open as Project`.

This should start loading your project and dependencies in your IDE.

### Importing Database

This is a two step process:
1. After you have cloned the project, open the [Hris-schema.sql](Hris-schema.sql) file in your MySQL
Workbench then execute the script. This will create the HRIS database in your local MySQL server.
2. Now open the [Hris.data.sql](Hris-data.sql) file in your MySQL workbench and execute the script. 
This should populate your table with sample data.

### Initial Project Configuration Changes

Please rename the [application.properties.sample](src/main/resources/application.properties.sample) to 
`application.properties` after updating the database username and pasword in the file.

### Running the Project

After the project has been imported, you should see the `HrisApplication` configuration. Just press the 
green run button that you use to run your projects in IntelliJ.

**NOTE**: Please don't worry about the `Error executing DDL create table employee ...` and similar other 
errors. It is just complaining that those tables already exist in the database and SpringBoot will ignore
recreating those tables based on your entity definitions.

### Testing HRIS APIs with Postman

After you have installed the Postman REST client, you can import the 
[HRIS.postman_collection.json](HRIS.postman_collection.json) file in your Postman. You can try
out sending the sample GET, POST, PUT, DELETE calls in the collection while this project is running.

### Unit Testing
Here is a [tutorial](https://www.springcloud.io/post/2022-03/spring-boot-integration-testing-mysql-crud-rest-api-tutorial/#gsc.tab=0) 
that the project references for creating entities,repository, controller, and for writing integration test cases.

A good starting point would be to look at the following classes:
- [Employee](src/main/java/org/goup10/hris/entities/Employee.java) - which defines an entity
- [EmployeeRepository](src/main/java/org/goup10/hris/repositories/EmployeeRepository.java) - which defines the repository
- [EmployeeController](src/main/java/org/goup10/hris/controllers/EmployeeController.java) - which defines REST API
- [EmployeeControllerTest](src/test/java/org/goup10/hris/controllers/EmployeeControllerTest.java) - which tests the `EmployeeController`

There is also relevant [HELP](HELP.md) document for you to explore further.

### Command Line Interface (CLI)

The frontend CLI for this project resides in the [cli](src/main/java/org/goup10/hris/cli) package. The design
has an abstraction for a command, which is an action such as getting all employees information from database,
retrieving an employee information using id, and so on. A group of commands makes up a component, such as
employee management, benefit management, and so on.

Take a look at the [EmployeeComponent](src/main/java/org/goup10/hris/cli/employee/EmployeeComponent.java) for an example
of component. We register a set of commands that the Employee component supports, in that class. Use
the [GetEmployeeByIdCommand](src/main/java/org/goup10/hris/cli/employee/GetEmployeeByIdCommand.java) class
as a reference for how to create a command.

The [RootComponent](src/main/java/org/goup10/hris/cli/RootComponent.java) represents a group of all
HRIS components such as EmployeeComponent, BenefitComponent, and so on.

As these components get developed, we will need to update the `RootComponent` class to be able to
run the commands under those components. 

You can simply run the CLI, by running the [HrisCliApplication](src/main/java/org/goup10/hris/cli/HrisCliApplication.java)
on IntelliJ.
