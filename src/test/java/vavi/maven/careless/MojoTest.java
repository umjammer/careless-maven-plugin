/*
 * Copyright (c) 2024 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.maven.careless;

import java.io.File;

import io.takari.maven.testing.TestResources5;
import io.takari.maven.testing.executor.MavenRuntime;
import io.takari.maven.testing.executor.MavenRuntime.MavenRuntimeBuilder;
import io.takari.maven.testing.executor.MavenVersions;
import io.takari.maven.testing.executor.junit.MavenJUnitTestRunner;
import io.takari.maven.testing.executor.junit.MavenPluginTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.runner.RunWith;


/**
 * MojoTest.
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (nsano)
 * @version 0.00 2024-12-22 nsano initial version <br>
 */
@Disabled("`Could not resolve maven versions` for every maven version")
@RunWith(MavenJUnitTestRunner.class)
@MavenVersions({"3.9.9"})
public class MojoTest {

    @RegisterExtension
    final TestResources5 resources = new TestResources5();

    private final MavenRuntime maven;

    MojoTest(MavenRuntimeBuilder mavenBuilder) throws Exception {
        this.maven = mavenBuilder.withCliOptions("-B", "-U").build();
    }

    @MavenPluginTest
    void test() throws Exception {
        File basedir = resources.getBasedir("basic");
        maven.forProject(basedir)
                .withCliOption("-f")
                .withCliOption("src/test/resources/test-pom.xml")
                .execute("validate")
                .assertErrorFreeLog();
    }
}
