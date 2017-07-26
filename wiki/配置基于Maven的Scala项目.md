
关键字：
scala maven

参考：
http://docs.scala-lang.org/tutorials/scala-with-maven.html

工程模版：
net.alchim31.maven:scala-archetype-simple

Scala插件：
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

