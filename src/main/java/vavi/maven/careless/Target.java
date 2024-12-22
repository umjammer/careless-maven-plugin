/*
 * Copyright (c) 2024 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.maven.careless;

import java.util.StringJoiner;

import org.apache.maven.plugins.annotations.Parameter;


/**
 * Target.
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (nsano)
 * @version 0.00 2024-12-19 nsano initial version <br>
 */
public class Target {

    /** target path */
    private String path;

    /**
     * resource indicator.
     * scheme is assumed implicitly like,
     * <ol>
     *  <li>.xml, .pom -> xpath</li>
     *  <li>.json -> jsonpath (WIP)</li>
     *  <li>.yml -> ymlpath</li>
     *  <li>.txt -> regexed line and location</li>
     *  </ol>
     */
    private String ri;

    private String value;

    private boolean negative;

    /**
     * resource indicator
     */
    @Parameter(property = "path", required = true)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Parameter(property = "ri", required = true)
    public String getRI() {
        return ri;
    }

    public void setRI(String ri) {
        this.ri = ri;
    }

    @Parameter(property = "value", required = true)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Parameter(property = "negative")
    public boolean isNegative() {
        return negative;
    }

    public Target path(String path) {
        this.path = path;
        return this;
    }

    public void setNegative(boolean negative) {
        this.negative = negative;
    }

    public Target ri(String ri) {
        this.ri = ri;
        return this;
    }

    public Target value(String value) {
        this.value = value;
        return this;
    }

    public Target negative(boolean negative) {
        this.negative = negative;
        return this;
    }

    @Override public String toString() {
        return new StringJoiner(", ",  "target: { ", " }")
                .add("path='" + path + "'")
                .add("ri='" + ri + "'")
                .add("value='" + value + "'")
                .toString();
    }
}
