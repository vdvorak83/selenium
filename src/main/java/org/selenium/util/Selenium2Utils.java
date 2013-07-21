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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

public final class Selenium2Utils {
    static final Logger logger = LoggerFactory.getLogger(Selenium2Utils.class);

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
            logger.warn("", e);
            return false;
        }

        return true;

    }

    public static boolean isElementPresentByLinkText(final WebDriver driver, final String linkText) {
        Preconditions.checkNotNull(driver);
        Preconditions.checkNotNull(linkText);

        try {
            driver.findElement(By.linkText(linkText));
        } catch (final NoSuchElementException e) {
            logger.warn("", e);
            return false;
        }

        return true;

    }

    public static boolean isElementPresentByPartialLinkText(final WebDriver driver, final String partialLinkText) {
        Preconditions.checkNotNull(driver);
        Preconditions.checkNotNull(partialLinkText);

        try {
            driver.findElement(By.partialLinkText(partialLinkText));
        } catch (final NoSuchElementException e) {
            logger.warn("", e);
            return false;
        }

        return true;

    }

    public static boolean isElementPresentByClassName(final WebDriver driver, final String className) {
        Preconditions.checkNotNull(driver);
        Preconditions.checkNotNull(className);

        try {
            driver.findElement(By.className(className));
        } catch (final NoSuchElementException e) {
            logger.warn("", e);
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
            logger.warn("", e);
            return false;
        }

        return true;
    }

    public static boolean isElementEnabledById(final WebDriver driver, final String id) {
        Preconditions.checkNotNull(driver);
        Preconditions.checkNotNull(id);

        if (!isElementPresentById(driver, id)) {
            return false;
        }

        return driver.findElement(By.id(id)).isEnabled();
    }

    public static boolean isElementEnabledByXPath(final WebDriver driver, final String xpath) {
        Preconditions.checkNotNull(driver);
        Preconditions.checkNotNull(xpath);

        if (!isElementPresentByXPath(driver, xpath)) {
            return false;
        }

        return driver.findElement(By.xpath(xpath)).isEnabled();
    }

    public static boolean isElementEnabledByClassName(final WebDriver driver, final String className) {
        Preconditions.checkNotNull(driver);
        Preconditions.checkNotNull(className);

        if (!isElementPresentByClassName(driver, className)) {
            return false;
        }

        return driver.findElement(By.className(className)).isEnabled();
    }

    // is displayed

    public static boolean isElementDisplayedById(final WebDriver driver, final String id) {
        Preconditions.checkNotNull(driver);
        Preconditions.checkNotNull(id);

        if (!isElementPresentById(driver, id)) {
            return false;
        }

        return driver.findElement(By.id(id)).isDisplayed();
    }

    public static boolean isElementDisplayedByPartialText(final WebDriver driver, final String partialText) {
        return isElementDisplayedByXPath(driver, "//*[contains(text(), '" + partialText + "')]");
    }

    public static boolean isElementDisplayedByXPath(final WebDriver driver, final String xpath) {
        Preconditions.checkNotNull(driver);
        Preconditions.checkNotNull(xpath);

        if (!isElementPresentByXPath(driver, xpath)) {
            return false;
        }

        return driver.findElement(By.xpath(xpath)).isDisplayed();
    }

    public static boolean isElementDisplayedByLinkText(final WebDriver driver, final String linkText) {
        Preconditions.checkNotNull(driver);
        Preconditions.checkNotNull(linkText);

        if (!isElementPresentByLinkText(driver, linkText)) {
            return false;
        }

        return driver.findElement(By.linkText(linkText)).isDisplayed();
    }

    public static boolean isElementDisplayedByPartialLinkText(final WebDriver driver, final String partialLinkText) {
        Preconditions.checkNotNull(driver);
        Preconditions.checkNotNull(partialLinkText);

        if (!isElementPresentByPartialLinkText(driver, partialLinkText)) {
            return false;
        }

        return driver.findElement(By.partialLinkText(partialLinkText)).isDisplayed();
    }

    public static boolean isElementDisplayedByClassName(final WebDriver driver, final String className) {
        Preconditions.checkNotNull(driver);
        Preconditions.checkNotNull(className);

        if (!isElementPresentByClassName(driver, className)) {
            return false;
        }

        return driver.findElement(By.className(className)).isDisplayed();
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
                logger.warn("", e);
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
                logger.warn("", e);
            }
        }

        public static void tryWaitForElementNotFoundByLinkText(final WebDriver driver, final String id, final long timeout) {
            try {
                waitForElementNotFoundByLinkText(driver, id, timeout);
            } catch (final TimeoutException e) {
                logger.warn("", e);
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
                        logger.warn("", e);
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
                        logger.warn("", e);
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
                logger.warn("", e);
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
                    final boolean hasText = textValueOfElement != null && !textValueOfElement.isEmpty();
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
                    final boolean hasText = textValueOfElement != null && !textValueOfElement.isEmpty();
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
                    final boolean hasText = textValueOfElement != null && !textValueOfElement.isEmpty() && textValueOfElement.contains(value);
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
                    final boolean hasText = textValueOfElement != null && !textValueOfElement.isEmpty() && textValueOfElement.contains(value);
                    return hasText;
                }
            });
        }

        public static void tryWaitForElementContainsByXPath(final WebDriver driver, final String xpath, final String value, final long timeout) {
            try {
                waitForElementContainsByXPath(driver, xpath, value, timeout);
            } catch (final TimeoutException e) {
                logger.warn("", e);
            } catch (final StaleElementReferenceException e) {
                // do nothing (but do investigate why it happens)
                logger.warn("", e);
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

        public static void waitForElementEnabledByClassName(final WebDriver driver, final String className, final long timeout) {
            Preconditions.checkNotNull(className);
            new WebDriverWait(driver, timeout).until(new ExpectedCondition<Boolean>() {
                @Override
                public final Boolean apply(final WebDriver theDriver) {
                    final WebElement element = theDriver.findElement(By.className(className));
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

        public static void waitForElementDisplayedByClassName(final WebDriver driver, final String className, final long timeout) {
            Preconditions.checkNotNull(className);
            new WebDriverWait(driver, timeout).until(new ExpectedCondition<Boolean>() {
                @Override
                public final Boolean apply(final WebDriver theDriver) {
                    final WebElement element = theDriver.findElement(By.className(className));
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

        // not displayed

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

        public static void waitForElementNotDisplayedByClassName(final WebDriver driver, final String className, final long timeout) {
            Preconditions.checkNotNull(className);

            new WebDriverWait(driver, timeout).until(new ExpectedCondition<Boolean>() {
                @Override
                public final Boolean apply(final WebDriver theDriver) {
                    final WebElement element = theDriver.findElement(By.className(className));
                    return !element.isDisplayed();
                }
            });
        }

        public static void tryWaitForElementDisplayedByXPath(final WebDriver driver, final String xpath, final long timeout) {
            try {
                waitForElementDisplayedByXPath(driver, xpath, timeout);
            } catch (final TimeoutException e) {
                logger.info("", e);
            }
        }

        public static void tryWaitForElementDisplayedById(final WebDriver driver, final String id, final long timeout) {
            try {
                waitForElementDisplayedById(driver, id, timeout);
            } catch (final TimeoutException e) {
                logger.info("", e);
            }
        }

        public static void tryWaitForElementFoundByLinkText(final WebDriver driver, final String link, final int timeout) {
            try {
                waitForElementFoundByLinkText(driver, link, timeout);
            } catch (final TimeoutException e) {
                logger.info("", e);
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
            logger.warn("", e);
            return false;
        }

        return !alertText.equals("false");
    }

}
