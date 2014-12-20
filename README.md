# Spring-Hibernate Todo

A Java EE web application using the Spring and Hibernate frameworks. The front-
end is powered Angular.js and Bootstrap. Tested on Tomcat and Java 8 with 
Postgres 9.3.

## Setup

*To Use in Eclipse*
1. Open a command line terminal at the top directory
1. Run the gradle-wrapper script to download Gradle, the app dependencies and
create the eclipse project files:
    $ gradlew cleanEclipse eclipse
1. Start Eclipse IDE and choose File -> Import
1. Select 'Existing Projects into Workspace'
1. Choose the root project folder for the 'root directory.' 'todo-dao', and
'todo-web' should appear. Click Next to continue.