
# How to create a Scala project mixed with Java #

*	Versions
	*	java version "1.8.0_121"
	*	Apache Maven 3.3.9
	*	IntelliJ IDEA 2017.1
	*	Scala 2.12.1

*	archetype

	*	net.alchim31.maven:scala-archetype-simple:1.6

	*	https://mvnrepository.com/

	*	mvn dependency:get -DgroupId=net.alchim31.maven -DartifactId=scala-archetype-simple -Dversion=1.6 -Dtransitive=true

*   create a project on GitHub

*   check out the project in IDEA(without creating a project)

*   Create new project

	*	Select Maven based project, tick `Create from archetype`, and `Add Archetype...`
    
		*	GroupId	net.alchim31.maven
		*	ArtifactId	scala-archetype-simple
		*	Version	1.6

	*	Project information
		*	GroupId	wanghg
		*	ArtifactId	javalovescala

	*	Click `Next` through

*	do some adjust

	*	scala version

			<scala.version>2.11.5</scala.version>
			<scala.compat.version>2.11</scala.compat.version>

	*	pick dependency

	*	remove source dir config

			<sourceDirectory>src/main/scala</sourceDirectory>
			<testSourceDirectory>src/test/scala</testSourceDirectory>

	*	replace plugin

			<plugin>
			  <!-- see http://davidb.github.com/scala-maven-plugin -->
			  <groupId>net.alchim31.maven</groupId>
			  <artifactId>scala-maven-plugin</artifactId>
			  <version>3.2.0</version>
			  <executions>
				<execution>
				  <id>scala-compile-first</id>
				  <phase>process-resources</phase>
				  <goals>
					<goal>add-source</goal>
					<goal>compile</goal>
				  </goals>
				</execution>
				<execution>
				  <id>scala-test-compile</id>
				  <phase>process-test-resources</phase>
				  <goals>
					<goal>testCompile</goal>
				  </goals>
				</execution>
			  </executions>
			</plugin>

*   Write a java class and its unit test

*   write a scala class and its unit tets

*   run `mvn test`, make sure all pass

