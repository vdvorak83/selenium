package org.selenium.setup;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.google.common.collect.Maps;

@Component
public class SeleniumComponent {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Map<DriverType, WebDriver> drivers;

    public SeleniumComponent() {
        super();

        this.drivers = Maps.newHashMap();
    }

    // API

    public final WebDriver getDriver(final DriverType driverType) {
        if (this.drivers.get(driverType) != null) {
            return this.drivers.get(driverType);
        }

        WebDriver theDriver = null;
        switch (driverType) {
        case FIREFOX:
            this.logger.info("Using Firefox Driver");
            theDriver = this.initFirefoxDriver();
            break;
        case HTMLUNIT:
            this.logger.info("Using HtmlUnit Driver");
            theDriver = this.initHtmlUnitDriver();
            break;
        default:
            break;
        }

        this.drivers.put(driverType, theDriver);
        return theDriver;
    }

    //

    private final WebDriver initFirefoxDriver() {
        final FirefoxProfile profile = new FirefoxProfile();
        profile.setAcceptUntrustedCertificates(true);
        // profile.setEnableNativeEvents( true );
        // TODO: blocks for some reason - solve
        // http://code.google.com/p/selenium/issues/detail?id=1773
        // https://groups.google.com/forum/#!topic/webdriver/q2jEE_F64bo/discussion
        final WebDriver driver = new FirefoxDriver(profile);
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().setScriptTimeout(1000, TimeUnit.MILLISECONDS);

        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1280, 1024));

        return driver;
    }

    private final WebDriver initHtmlUnitDriver() {
        final HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_10);
        driver.setJavascriptEnabled(true);
        driver.manage().timeouts().implicitlyWait(750, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().setScriptTimeout(1500, TimeUnit.MILLISECONDS);

        return driver;
    }

}
