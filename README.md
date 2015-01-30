# Spring-Hibernate Todo

A Java EE web application using the Spring and Hibernate frameworks. The front-
end is powered Angular.js and Bootstrap. Tested on Tomcat and Java 8 with 
Postgres 9.3.

## Setup

*To Use in Eclipse*
<ol>
<li>Open shared.gradle and update the 'ext.serverHome' variable to the root dir.
of tomcat</li>
<li>Open a command line terminal at the top directory</li>
<li>Run the gradle-wrapper script to download Gradle, the app dependencies and
create the eclipse project files:
    $ gradlew cleanEclipse eclipse</li>
<li>Start Eclipse IDE and choose File -> Import</li>
<li>Select 'Existing Projects into Workspace'</li>
<li>Choose the root project folder for the 'root directory.' 'todo-dao', and
'todo-web' should appear. Click Next to continue.</li>
</ol>