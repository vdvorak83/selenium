package org.selenium.util;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Preconditions;

public final class Selenium2Utils {

    private Selenium2Utils() {
        throw new AssertionError();
    }

    // isX?

    public static boolean isElementPresentByXPath(final WebDriver driver, final String xpathExpression) {
        Preconditions.checkNotNull(driver);
        Preconditions.checkNotNull(xpathExpression);

        try {
            driver.findElement(By.xpath(xpathExpression));
        } catch (final NoSuchElementException e) {
            return false;
        }

        return true;

    }

    public static boolean isElementPresentById(final WebDriver driver, final String id) {
        Preconditions.checkNotNull(driver);
        Preconditions.checkNotNull(id);

        try {
            driver.findElement(By.id(id));
        } catch (final NoSuchElementException e) {
            return false;
        }

        return true;
    }

    /**
     * - note: if the element is not present, then false is returned
     */
    public static boolean isElementEnabledById(final WebDriver driver, final String id) {
        Preconditions.checkNotNull(driver);
        Preconditions.checkNotNull(id);

        if (!isElementPresentById(driver, id)) {
            return false;
        }

        return driver.findElement(By.id(id)).isEnabled();
    }

    /**
     * - note: if the element is not present, then false is returned
     */
    public static boolean isElementDisplayedById(final WebDriver driver, final String id) {
        Preconditions.checkNotNull(driver);
        Preconditions.checkNotNull(id);

        if (!isElementPresentById(driver, id)) {
            return false;
        }

        return driver.findElement(By.id(id)).isDisplayed();
    }

    public static boolean isElementDisplayedByXPath(final WebDriver driver, final String xpath) {
        Preconditions.checkNotNull(driver);
        Preconditions.checkNotNull(xpath);

        if (!isElementPresentByXPath(driver, xpath)) {
            return false;
        }

        return driver.findElement(By.xpath(xpath)).isDisplayed();
    }

    public static final class Wait {

        // found

        public static void waitForElementFoundByClassName(final WebDriver driver, final String className, final long timeout) {
            Preconditions.checkNotNull(className);
            new WebDriverWait(driver, timeout).until(new ExpectedCondition<Boolean>() {
                @Override
                public final Boolean apply(final WebDriver theDriver) {
                    theDriver.findElement(By.className(className));
                    return true;
                }
            });
        }

        public static void tryWaitForElementFoundByClassName(final WebDriver driver, final String className, final long timeout) {
            try {
                waitForElementFoundByClassName(driver, className, timeout);
            } catch (final TimeoutException e) {
                // do nothing
            }
        }

        public static void waitForElementFoundByLinkText(final WebDriver driver, final String linkText, final long timeout) {
            Preconditions.checkNotNull(linkText);
            new WebDriverWait(driver, timeout).until(new ExpectedCondition<Boolean>() {
                @Override
                public final Boolean apply(final WebDriver theDriver) {
                    theDriver.findElement(By.linkText(linkText));
                    return true;
                }
            });
        }

        public static void waitForElementFoundById(final WebDriver driver, final String id, final long timeout) {
            Preconditions.checkNotNull(id);
            new WebDriverWait(driver, timeout).until(new ExpectedCondition<Boolean>() {
                @Override
                public final Boolean apply(final WebDriver theDriver) {
                    theDriver.findElement(By.id(id));
                    return true;
                }
            });
        }

        public static void tryWaitForElementFoundById(final WebDriver driver, final String id, final long timeout) {
            try {
                waitForElementFoundById(driver, id, timeout);
            } catch (final TimeoutException e) {
                // do nothing
            }
        }

        public static void tryWaitForElementNotFoundByLinkText(final WebDriver driver, final String id, final long timeout) {
            try {
                waitForElementNotFoundByLinkText(driver, id, timeout);
            } catch (final TimeoutException e) {
                // do nothing
            }
        }

        public static void waitForElementNotFoundById(final WebDriver driver, final String id, final long timeout) {
            Preconditions.checkNotNull(id);

            new WebDriverWait(driver, timeout).until(new ExpectedCondition<Boolean>() {
                @Override
                public final Boolean apply(final WebDriver theDriver) {
                    try {
                        theDriver.findElement(By.id(id));
                    } catch (final NoSuchElementException e) {
                        return true;
                    }
                    return false;
                }
            });
        }

        public static void waitForElementNotFoundByLinkText(final WebDriver driver, final String linkText, final long timeout) {
            Preconditions.checkNotNull(linkText);

            new WebDriverWait(driver, timeout).until(new ExpectedCondition<Boolean>() {
                @Override
                public final Boolean apply(final WebDriver theDriver) {
                    try {
                        theDriver.findElement(By.linkText(linkText));
                    } catch (final NoSuchElementException e) {
                        return true;
                    }
                    return false;
                }
            });
        }

        public static void waitForElementFoundByXPath(final WebDriver driver, final String xpath, final long timeout) {
            Preconditions.checkNotNull(xpath);
            new WebDriverWait(driver, timeout).until(new ExpectedCondition<Boolean>() {
                @Override
                public final Boolean apply(final WebDriver theDriver) {
                    theDriver.findElement(By.xpath(xpath));
                    return true;
                }
            });
        }

        public static void tryWaitForElementFoundByXPath(final WebDriver driver, final String xpath, final long timeout) {
            try {
                waitForElementFoundByXPath(driver, xpath, timeout);
            } catch (final TimeoutException e) {
                // do nothing
            }
        }

        // has value

        public static void waitForElementHasValueById(final WebDriver driver, final String id, final long timeout) {
            Preconditions.checkNotNull(id);
            new WebDriverWait(driver, timeout).until(new ExpectedCondition<Boolean>() {
                @Override
                public final Boolean apply(final WebDriver theDriver) {
                    final WebElement element = theDriver.findElement(By.id(id));
                    final String textValueOfElement = element.getText();
                    final boolean hasText = (textValueOfElement != null) && !textValueOfElement.isEmpty();
                    return hasText;
                }
            });
        }

        public static void waitForElementHasValueByXPath(final WebDriver driver, final String xpath, final long timeout) {
            Preconditions.checkNotNull(xpath);

            new WebDriverWait(driver, timeout).until(new ExpectedCondition<Boolean>() {
                @Override
                public final Boolean apply(final WebDriver theDriver) {
                    final WebElement element = theDriver.findElement(By.xpath(xpath));
                    final String textValueOfElement = element.getText();
                    final boolean hasText = (textValueOfElement != null) && !textValueOfElement.isEmpty();
                    return hasText;
                }
            });
        }

        // contains

        public static void waitForElementContainsById(final WebDriver driver, final String id, final String value, final long timeout) {
            Preconditions.checkNotNull(id);
            Preconditions.checkNotNull(value);

            new WebDriverWait(driver, timeout).until(new ExpectedCondition<Boolean>() {
                @Override
                public final Boolean apply(final WebDriver theDriver) {
                    final WebElement element = theDriver.findElement(By.id(id));
                    final String textValueOfElement = element.getText();
                    final boolean hasText = (textValueOfElement != null) && !textValueOfElement.isEmpty() && textValueOfElement.contains(value);
                    return hasText;
                }
            });
        }

        public static void waitForElementContainsByXPath(final WebDriver driver, final String xpath, final String value, final long timeout) {
            Preconditions.checkNotNull(xpath);
            Preconditions.checkNotNull(value);

            new WebDriverWait(driver, timeout).until(new ExpectedCondition<Boolean>() {
                @Override
                public final Boolean apply(final WebDriver theDriver) {
                    final WebElement element = theDriver.findElement(By.xpath(xpath));
                    final String textValueOfElement = element.getText();
                    final boolean hasText = (textValueOfElement != null) && !textValueOfElement.isEmpty() && textValueOfElement.contains(value);
                    return hasText;
                }
            });
        }

        public static void tryWaitForElementContainsByXPath(final WebDriver driver, final String xpath, final String value, final long timeout) {
            try {
                waitForElementContainsByXPath(driver, xpath, value, timeout);
            } catch (final TimeoutException e) {
                // do nothing
            } catch (final StaleElementReferenceException e) {
                // do nothing (but do investigate why it happens)
            }
        }

        // is enabled

        public static void waitForElementEnabledById(final WebDriver driver, final String id, final long timeout) {
            Preconditions.checkNotNull(id);
            new WebDriverWait(driver, timeout).until(new ExpectedCondition<Boolean>() {
                @Override
                public final Boolean apply(final WebDriver theDriver) {
                    final WebElement element = theDriver.findElement(By.id(id));
                    return element.isEnabled();
                }
            });
        }

        public static void waitForElementEnabledByXPath(final WebDriver driver, final String xpath, final long timeout) {
            Preconditions.checkNotNull(xpath);

            new WebDriverWait(driver, timeout).until(new ExpectedCondition<Boolean>() {
                @Override
                public final Boolean apply(final WebDriver theDriver) {
                    final WebElement element = theDriver.findElement(By.xpath(xpath));
                    return element.isEnabled();
                }
            });
        }

        // is displayed

        public static void waitForElementDisplayedById(final WebDriver driver, final String id, final long timeout) {
            Preconditions.checkNotNull(id);
            new WebDriverWait(driver, timeout).until(new ExpectedCondition<Boolean>() {
                @Override
                public final Boolean apply(final WebDriver theDriver) {
                    final WebElement element = theDriver.findElement(By.id(id));
                    return element.isDisplayed();
                }
            });
        }

        public static void waitForElementNotDisplayedById(final WebDriver driver, final String id, final long timeout) {
            Preconditions.checkNotNull(id);
            new WebDriverWait(driver, timeout).until(new ExpectedCondition<Boolean>() {
                @Override
                public final Boolean apply(final WebDriver theDriver) {
                    final WebElement element = theDriver.findElement(By.id(id));
                    return !element.isDisplayed();
                }
            });
        }

        public static void waitForElementDisplayedByLinkText(final WebDriver driver, final String linkText, final int timeout) {
            Preconditions.checkNotNull(linkText);
            new WebDriverWait(driver, timeout).until(new ExpectedCondition<Boolean>() {
                @Override
                public final Boolean apply(final WebDriver theDriver) {
                    final WebElement element = theDriver.findElement(By.linkText(linkText));
                    return element.isDisplayed();
                }
            });
        }

        public static void waitForElementDisplayedByXPath(final WebDriver driver, final String xpath, final long timeout) {
            Preconditions.checkNotNull(xpath);

            new WebDriverWait(driver, timeout).until(new ExpectedCondition<Boolean>() {
                @Override
                public final Boolean apply(final WebDriver theDriver) {
                    final WebElement element = theDriver.findElement(By.xpath(xpath));
                    return element.isDisplayed();
                }
            });
        }

        public static void waitForElementNotDisplayedByXPath(final WebDriver driver, final String xpath, final long timeout) {
            Preconditions.checkNotNull(xpath);

            new WebDriverWait(driver, timeout).until(new ExpectedCondition<Boolean>() {
                @Override
                public final Boolean apply(final WebDriver theDriver) {
                    final WebElement element = theDriver.findElement(By.xpath(xpath));
                    return !element.isDisplayed();
                }
            });
        }

        public static void tryWaitForElementDisplayedByXPath(final WebDriver driver, final String xpath, final long timeout) {
            try {
                waitForElementDisplayedByXPath(driver, xpath, timeout);
            } catch (final TimeoutException timeoutEx) {
                // do nothing
            }
        }

        public static void tryWaitForElementDisplayedById(final WebDriver driver, final String id, final long timeout) {
            try {
                waitForElementDisplayedById(driver, id, timeout);
            } catch (final TimeoutException timeoutEx) {
                // do nothing
            }
        }

        public static void tryWaitForElementFoundByLinkText(final WebDriver driver, final String link, final int timeout) {
            try {
                waitForElementFoundByLinkText(driver, link, timeout);
            } catch (final TimeoutException timeoutEx) {
                // do nothing
            }
        }

    }

    // other

    public final static boolean isAlertPresent(final Alert alertArg) {
        Preconditions.checkNotNull(alertArg);
        String alertText = null;
        try {
            alertText = alertArg.getText();
        } catch (final Exception e) {
            return false;
        }

        return !alertText.equals("false");
    }

}
