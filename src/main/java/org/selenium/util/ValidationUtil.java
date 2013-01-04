package org.selenium.util;

import org.openqa.selenium.WebDriver;

public class ValidationUtil {

    private ValidationUtil() {
        throw new UnsupportedOperationException();
    }

    // API

    public static boolean isValidationMessagePresent(final WebDriver driver) {
        return Selenium2Utils.isElementPresentById(driver, "gwt-debug-validationElement");
    }

}
