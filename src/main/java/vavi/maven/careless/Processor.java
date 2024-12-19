/*
 * Copyright (c) 2024 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.maven.careless;

import java.io.InputStream;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import io.github.yamlpath.YamlPath;
import org.xml.sax.InputSource;

import static java.lang.System.getLogger;


/**
 * Processor.
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (nsano)
 * @version 0.00 2024-12-19 nsano initial version <br>
 */
public class Processor {

    private static final Logger logger = getLogger(Processor.class.getName());

    static XPath xPath = XPathFactory.newInstance().newXPath();

    //
    //   matches | negative | result
    // ----------+----------+----------
    //   true      true       ERROR
    //   false     true       OK
    //   true      false      OK
    //   false     false      ERROR
    //
    private static String m(boolean ok) {
        return ok ? "matched" : "not matched";
    }

    public void process(Target target) {
        String ext = target.path.substring(target.path.lastIndexOf('.') + 1);
//logger.log(Level.DEBUG, "ext: " + ext);
        switch (ext) {
            case "pom", "xml" -> {
                try {
                    InputSource is = new InputSource(Files.newInputStream(Path.of(target.path)));
                    String actual = (String) xPath.evaluate(target.ri, is, XPathConstants.STRING);
                    boolean m = actual.matches(target.value);
//logger.log(Level.DEBUG, ext + ": " + target.value + ", actual: " + actual + ", matches: " + m);
                    if (target.negative == m)
                        System.out.println("NG: " + ext + ": " + m(m) + ": required: " + target.value + ", actual: " + actual);
                    else
                        System.err.println("OK: " + ext + ": " + m(m) + ": for " + target);
                } catch (Exception e) {
                    logger.log(Level.ERROR, e.getMessage() + ":" + target, e);
                }
            }
            case "yml" -> {
                try {
                    InputStream is = Files.newInputStream(Path.of(target.path));
                    String actual = YamlPath.from(is).readSingle(target.ri);
                    boolean m = actual != null && actual.matches(target.value);
                    if (target.negative == m)
                        System.out.println("NG: " + ext + ": " + m(m) + ": required: " + target.value + ", actual: " + actual);
                    else
                        System.err.println("OK: " + ext + ": " + m(m) + ": for " + target);
                } catch (Exception e) {
                    logger.log(Level.ERROR, e.getMessage() + ":" + target, e);
                }
            }
            default -> {
                try {
                    Pattern p = Pattern.compile(target.ri);
                    List<Integer> r = new ArrayList<>();
                    AtomicInteger l = new AtomicInteger();
                    Files.readAllLines(Path.of(target.path)).forEach(line -> {
                        Matcher m = p.matcher(line);
//logger.log(Level.DEBUG, ext + ": find: " + p + ": " + m.find() + ": " + line);
                        if (m.find()) {
                            String actual = m.group(1);
                            if (target.value.matches(actual))
                                r.add(l.get());
                        }
                        l.incrementAndGet();
                    });
                    boolean m = !r.isEmpty();
//logger.log(Level.DEBUG, ext + ": " + target.value + ", matches: " + m);
                    if (!target.negative == m)
                        System.err.println("OK: " + ext + ": " + m(m) + ": for " + target + ", lines: " + r);
                    else
                        System.out.println("NG: " + ext + ": " + m(m) + ": for " + target + ", lines: " + r);
                } catch (Exception e) {
                    logger.log(Level.ERROR, e.getMessage() + ":" + target, e);
                }
            }
        }
    }
}
