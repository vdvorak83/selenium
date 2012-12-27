package org.selenium.base;

import org.selenium.spring.SpringContext;

public class SmokeUriUtil {

    private SmokeUriUtil() {
        throw new AssertionError();
    }

    // API

    public static String getBaseUri() {
        return get("smoke.http.protocol") + "://" + get("smoke.http.host") + ":" + get("smoke.http.port") + get("smoke.http.web.path");
    }

    // util

    static String get(final String key) {
        return SpringContext.context().getEnvironment().getProperty(key);
    }

}
