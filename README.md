[![GitHub Packages](https://github.com/umjammer/careless-maven-plugin/actions/workflows/maven-publish.yml/badge.svg)](https://github.com/umjammer?tab=packages&repo_name=careless-maven-plugin)
[![Release](https://jitpack.io/v/umjammer/careless-maven-plugin.svg)](https://jitpack.io/#umjammer/careless-maven-plugin)
[![Java CI](https://github.com/umjammer/careless-maven-plugin/actions/workflows/maven.yml/badge.svg)](https://github.com/umjammer/careless-maven-plugin/actions/workflows/maven.yml)
[![CodeQL](https://github.com/umjammer/careless-maven-plugin/actions/workflows/codeql.yml/badge.svg)](https://github.com/umjammer/careless-maven-plugin/actions/workflows/codeql.yml)
![Java](https://img.shields.io/badge/Java-17-b07219)

# careless-maven-plugin

 ⚠️ eliminates human errors.

## Install

 * [maven](https://jitpack.io/#umjammer/careless-maven-plugin)

## Usage

```xml
 <config>
  <checks>
   <check path="pom.xml" where="" match="21" />
   <check path=".github/workflow/maven.yml" where="project.version" match="21" />
   <check path=".github/workflow/maven-publish.yml" where="project.version" match="21" />
   <check path=".github/workflow/cldeql.yml" where="project.version" match="21" />
   <check path=".jitpack.yml" where="jdk.version" match="21" />
   <check path="README.md" where="grep:-(\d\d)-pink" match="21" />
  </checks>
 </config>
```

 * where
   * file is xml(.xml) ... xpath
   * file is yaml(.yml) ... yaml-path
   * prefix (prefix:...)
     * grep:regex 

## Preference

 * https://github.com/yaml-path/YamlPath
 * https://github.com/snakeyaml/snakeyaml

## TODO

 * checkstyle
