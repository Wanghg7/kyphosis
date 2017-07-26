
================================================================================

mvn install:install-file -Dfile=<path-to-file> -DgroupId=<group-id> \
    -DartifactId=<artifact-id> -Dversion=<version> -Dpackaging=<packaging>

mvn install:install-file -Dfile=<path-to-file> -DpomFile=<path-to-pomfile>

================================================================================

For the jar itself:

mvn deploy:deploy-file \
    -DgroupId=com.yourname.jgoodies \
    -DartifactId=jgoodies-forms \
    -Dversion=1.50 \
    -Dfile=/path/to/jgoodies-1.50.jar \
    -Dpackaging=jar \
    -Durl=file://path/to/your/local/repository 

For the sources:

mvn deploy:deploy-file \
    -DgroupId=com.yourname.jgoodies \
    -DartifactId=jgoodies-forms \
    -Dversion=1.50 \
    -Dfile=/path/to/jgoodies-sources.jar \
    -Dpackaging=jar \
    -Durl=file://path/to/your/local/repository \
    -Dclassifier=sources

For the javadoc:

mvn deploy:deploy-file \
    -DgroupId=com.yourname.jgoodies \
    -DartifactId=jgoodies-forms \
    -Dversion=1.50 \
    -Dfile=/path/to/jgoodies-javadoc.jar \
    -Dpackaging=jar \
    -Durl=file://path/to/your/local/repository \
    -Dclassifier=javadoc

================================================================================

mvn install:install-file \
    -DgroupId=net.sf.json-lib \
    -DartifactId=json-lib \
    -Dversion=2.4 \
    -Dfile=json-lib-2.4-jdk15.jar \
    -Dpackaging=jar

mvn install:install-file \
    -DgroupId=net.sf.json-lib \
    -DartifactId=json-lib \
    -Dversion=2.4 \
    -Dfile=json-lib-2.4-jdk15-sources.jar \
    -Dpackaging=jar \
    -Dclassifier=sources


