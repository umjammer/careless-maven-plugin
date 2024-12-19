/*
 * Copyright (c) 2024 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.maven.careless;

import java.util.StringJoiner;


/**
 * Target.
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (nsano)
 * @version 0.00 2024-12-19 nsano initial version <br>
 */
public class Target {

    /** target path */
    String path;

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
    String ri;

    String value;

    boolean negative;

    public Target path(String path) {
        this.path = path;
        return this;
    }

    /**
     * resource indicator
     *
     */
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
        return new StringJoiner(", ", Target.class.getSimpleName() + "[", "]")
                .add("path='" + path + "'")
                .add("ri='" + ri + "'")
                .add("value='" + value + "'")
                .toString();
    }
}
