#Förberedelser

## Installera JDK 8

  Ladda hem installationsfil för din plattform här: [JDK 8] (https://jdk8.java.net/download.html).
  Koden är testad mot build b115.

  Kontrollera att installationen gått bra genom att köra

    java -version

  Du bör se en utskrift i stil med

    Java version "1.8.0-ea"
    Java(TM) SE Runtime Environment (build 1.8.0-ea-b115)
    Java HotSpot(TM) 64-Bit Server VM (build 25.0-b57, mixed mode)

## Installera Maven 3.x.

  Ladda hem härifrån: [Maven] (http://maven.apache.org/download.cgi).
  Maven måste använda Java 8 vid körning. Kontrollera att så är fallet genom att köra

    mvn -version

  Du ska se en rad som säger något i stil med

    Java version: 1.8.0-ea, vendor: Oracle Corporation

  Om en tidigare version av Java används av Maven behöver du sätta miljövariabeln `JAVA_HOME` att peka
  till din Java 8-installation.

## Ladda hem källkoden.

  Om du är bekväm med att använda git kör du

    git clone git@github.com:soulution/lamda-lab.git

  Det går också bra att ladda ner koden som en Zip-fil från GitHub.

##  Testa bygget.
  Ställ dig i mappen där `pom.xml` ligger och kör

    mvn clean install

  Om allt gick bra ska du se följande i slutet av utskriften

    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD SUCCESS
    [INFO] ------------------------------------------------------------------------
    [INFO] Total time: 2.533s
    [INFO] Finished at: Sun Nov 17 11:23:22 CET 2013
    [INFO] Final Memory: 15M/189M
    [INFO] ------------------------------------------------------------------------


#Instruktioner
Öppna filen **Exercises.java**. Övningarna är utformade som enhetstester med *assertions*. Påbörja
en uppgift genom att ta bort annoteringen ``@Ignore``. Pröva lösningen genom att köra
maven-bygget med

    mvn clean install


#Resurser

 [Java SE 8 API (JavaDoc)] (http://download.java.net/jdk8/docs/api/index.html).
 Speciellt intressanta bibliotek och klasser: **java.util.stream**, **java.util.function**, **java.util.Optional**.