/*
 * Copyright (c) 2024 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.maven.careless;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;


/**
 * CarelessMojo.
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (nsano)
 * @version 0.00 2024-09-07 nsano initial version <br>
 */
@Mojo(name = "careless")
public class CarelessMojo implements org.apache.maven.plugin.Mojo {

    private Log log;


    @Parameter(property = "constantName", required = true)
    private String constantName;

    @Parameter(property = "expectedValue", required = true)
    private String expectedValue;

    @Override
    public void execute() {
        log.info("Hello, world");
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
