package org.selenium.base;

import org.selenium.spring.SpringContext;

public class SmokeUriUtil {

    private SmokeUriUtil() {
        throw new AssertionError();
    }

    // API

    public static String getBaseUri() {
        return get("http.protocol") + "://" + get("http.host") + ":" + get("http.port") + get("http.web.path");
    }

    // util

    static String get(final String key) {
        return SpringContext.context().getEnvironment().getProperty(key);
    }

}
