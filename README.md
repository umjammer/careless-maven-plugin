[![Release](https://jitpack.io/v/umjammer/careless-maven-plugin.svg)](https://jitpack.io/#umjammer/careless-maven-plugin)
[![Java CI](https://github.com/umjammer/careless-maven-plugin/actions/workflows/maven.yml/badge.svg)](https://github.com/umjammer/careless-maven-plugin/actions/workflows/maven.yml)
[![CodeQL](https://github.com/umjammer/careless-maven-plugin/actions/workflows/codeql.yml/badge.svg)](https://github.com/umjammer/careless-maven-plugin/actions/workflows/codeql.yml)
![Java](https://img.shields.io/badge/Java-17-b07219)

# careless-maven-plugin

 ⚠️ eliminates human errors.

### 🐣️ this project is a good exsample of

 * maven plugin
 * maven plugin junit5 test

## Install

 * [maven](https://jitpack.io/#umjammer/careless-maven-plugin)

## Usage

```xml
 <properties><javaVersion>21</javaVersion></properties>
   <plugin>
     <artifactId>careless-maven-plugin</artifactId>
     <configuration>
       <targets>
         <target>
           <path>.github/workflows/codeql.yml</path>
           <ri>jobs.analyze.steps[2].name</ri>
           <value>Set up JDK ${javaVersion}</value>
         </target>
         <target>
           <path>pom.xml</path>
           <ri>//*[local-name()='artifactId' and text()='maven-compiler-plugin']/..//*[local-name()='release' or local-name()='source']/text()</ri>
           <value>${javaVersion}</value>
         </target>
         <target>
           <path>README.md</path>
           <ri>img\.shields\.io\/badge\/Java-(\d+?)-b07219</ri>
           <value>${javaVersion}</value>
         </target>
         <target>
           <path>pom.xml</path>
           <ri>//*[local-name()='project']/*[local-name()='version']/text()</ri>
           <value>.*-SNAPSHOT</value>
           <negative>true</negative>
         </target>
       </targets>
     </configuration>
   </plugin>
```

 * ri: meaning is changed implicitly
   * file is xml(.xml, .pom) ... xpath 1.0 (java doesn't support xpath 2.0...)
   * file is yaml(.yml) ... yaml-path
   * else (.md) ... word extracted by regex (), line is extracted by grep

```shell
$ mvn -f src/test/resources/test-pom.xml validate                                                                                                                                                                main
[INFO] Scanning for projects...
[INFO] 
[INFO] Using the MultiThreadedBuilder implementation with a thread count of 24
[INFO] 
[INFO] ----------------------------< vavi:sample >-----------------------------
[INFO] Building sample 0.0.1-SNAPSHOT
[INFO]   from test-pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- careless:0.0.1-SNAPSHOT:careless (default) @ sample ---
[INFO] OK: yml: matched: for target: { path='.github/workflows/codeql.yml', ri='jobs.analyze.steps[2].name', value='Set up JDK 17' }
[INFO] OK: yml: matched: for target: { path='.github/workflows/codeql.yml', ri='jobs.analyze.steps[2].with.java-version', value='17' }
[INFO] OK: yml: matched: for target: { path='.github/workflows/maven.yml', ri='jobs.build.steps[2].name', value='Set up JDK 17' }
[INFO] OK: yml: matched: for target: { path='.github/workflows/maven.yml', ri='jobs.build.steps[2].with.java-version', value='17' }
[INFO] OK: xml: matched: for target: { path='pom.xml', ri='//*[local-name()='artifactId' and text()='maven-compiler-plugin']/..//*[local-name()='release' or local-name()='source']/text()', value='17' }
[INFO] OK: yml: matched: for target: { path='jitpack.yml', ri='jdk[0]', value='openjdk17' }
[INFO] OK: md: matched: for target: { path='README.md', ri='img\.shields\.io\/badge\/Java-(\d+?)-b07219', value='17' }, lines: [3]
[ERROR] NG: xml: matched: for target: { path='pom.xml', ri='//*[local-name()='project']/*[local-name()='version']/text()', value='.*-SNAPSHOT' }, actual: 0.0.1-SNAPSHOT
```
## Preference

 * https://github.com/yaml-path/YamlPath
 * https://github.com/snakeyaml/snakeyaml
 * https://github.com/copilot/c/22ee9d4a-39ad-4e28-a3fb-6c9ea6130fce (maybe private)
 * testing
   * https://github.com/codeteapot/maven-plugin-testing-harness ... no document
   * https://github.com/takari/takari-plugin-testing-project ... unclear document

## TODO

 * checkstyle
