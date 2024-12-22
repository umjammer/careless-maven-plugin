/*
 * Copyright (c) 2024 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.maven.careless;

import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;


/**
 * CarelessMojo.
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (nsano)
 * @version 0.00 2024-09-07 nsano initial version <br>
 */
@Mojo(name = "careless", defaultPhase = LifecyclePhase.VALIDATE)
public class CarelessMojo implements org.apache.maven.plugin.Mojo {

    private Log log;

    @Parameter(required = true)
    private List<Target> targets;

    /** checker */
    private final Processor processor = new Processor();

    @Override
    public void execute() throws MojoExecutionException {
        for (Target target : targets) {
            try {
                String result = processor.process(target);
                if (result.startsWith("OK")) {
                    log.info(result);
                } else {
                    log.error(result);
                }
            } catch (IllegalStateException e) {
                throw new MojoExecutionException(e.getCause());
            }
        }
    }

    @Override
    public void setLog(Log log) {
        this.log = log;
    }

    @Override
    public Log getLog() {
        return log;
    }
}
