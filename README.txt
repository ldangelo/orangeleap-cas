To generate the CAS war file, use the ant build file in the build directory and run the
dist task (ant dist). This will create a war file called cas.war in the dist directory.

Before deploying cas.war, copy the cas.properties file from the project root into the
Tomcat/lib directory. This file must be on the classpath and contains configuration
options for CAS. The current version is setup for testing with localhost, so it must
be modified before deployment to a server.

