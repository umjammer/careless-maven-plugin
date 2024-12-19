/*
 * Copyright (c) 2024 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.maven.careless;

import java.util.List;

import org.junit.jupiter.api.Test;


/**
 * TestCase.
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (nsano)
 * @version 0.00 07 nsano initial version <br>
 */
class TestCase {

    //
    // WTF java still has not supported xpath 2.0
    // use saxon for xpath 2.0
    //
    @Test
    void test1() throws Exception {
        String javaVersion = "17";
        var targets = List.of(
                // version check
                new Target().path(".github/workflows/codeql.yml").ri("jobs.analyze.steps[2].name").value("Set up JDK " + javaVersion),
                new Target().path(".github/workflows/codeql.yml").ri("jobs.analyze.steps[2].with.java-version").value(javaVersion),
                new Target().path(".github/workflows/maven.yml").ri("jobs.build.steps[2].name").value("Set up JDK " + javaVersion),
                new Target().path(".github/workflows/maven.yml").ri("jobs.build.steps[2].with.java-version").value(javaVersion),
                new Target().path("pom.xml").ri("//*[local-name()='artifactId' and text()='maven-compiler-plugin']/..//*[local-name()='release' or local-name()='source']/text()").value(javaVersion),
                new Target().path("jitpack.yml").ri("jdk[0]").value("openjdk" + javaVersion),
                new Target().path("README.md").ri("img\\.shields\\.io\\/badge\\/Java-(\\d+?)-b07219").value(javaVersion),
                // SNAPSHOT check
                new Target().path("pom.xml").ri("//*[local-name()='project']/*[local-name()='version']/text()").value(".*-SNAPSHOT").negative(true)
        );

        Processor p = new Processor();
        targets.forEach(p::process);
    }
}
