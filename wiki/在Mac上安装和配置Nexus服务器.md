
$ system_profiler SPSoftwareDataType
System Version: macOS 10.12.5 (16F73)
      Kernel Version: Darwin 16.6.0

$ java -version
java version "1.8.0_131"

/Users/wanghg/WANGHG/repo/nexus-3.3.1-01-mac.tgz

$ tar xzvf /Users/wanghg/WANGHG/repo/nexus-3.3.1-01-mac.tgz
$ cd ~/Applications/
$ ln -sf ~/WANGHG/repo/sonatype-work/ sonatype-work/
$ nexus start

admin/admin123@http://localhost:8081/

$ vi ~/.m2/settings.xml


<?xml version="1.0"?>
<settings>
  <mirrors>
    <mirror>
      <!--This sends everything else to /public -->
      <id>nexus</id>
      <mirrorOf>*</mirrorOf>
      <url>http://localhost:8081/repository/maven-public/</url>
    </mirror>
  </mirrors>
  <profiles>
    <profile>
      <id>nexus</id>
      <!--Enable snapshots for the built in central repo to direct -->
      <!--all requests to nexus via the mirror -->
      <repositories>
        <repository>
          <id>central</id>
          <url>http://central</url>
          <releases>
            <enabled>true</enabled>
          </releases>
          <snapshots>
            <enabled>true</enabled>
          </snapshots>
        </repository>
      </repositories>
      <pluginRepositories>
        <pluginRepository>
          <id>central</id>
          <url>http://central</url>
          <releases>
            <enabled>true</enabled>
          </releases>
          <snapshots>
            <enabled>true</enabled>
          </snapshots>
        </pluginRepository>
      </pluginRepositories>
    </profile>
  </profiles>
  <activeProfiles>
    <!--make the profile active all the time -->
    <activeProfile>nexus</activeProfile>
  </activeProfiles>
</settings>


$ find ~/.m2/repository -iname '*junit*4.12*' -exec rm -rf \{\} \;

$ mvn dependency:get -DgroupId=junit -DartifactId=junit -Dversion=4.12 -Dtransitive=true

