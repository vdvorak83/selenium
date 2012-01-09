package org.selenium.setup;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public final class SeleniumComponent{
	private final WebDriver driver;
	
	public SeleniumComponent(){
		super();
		
		final FirefoxProfile profile = new FirefoxProfile();
		profile.setAcceptUntrustedCertificates( true );
		// profile.setEnableNativeEvents( true );
		// TODO: blocks for some reason - solve
		// http://code.google.com/p/selenium/issues/detail?id=1773
		// https://groups.google.com/forum/#!topic/webdriver/q2jEE_F64bo/discussion
		this.driver = new FirefoxDriver( profile );
		this.driver.manage().timeouts().implicitlyWait( 500, TimeUnit.MILLISECONDS );
		this.driver.manage().timeouts().setScriptTimeout( 1000, TimeUnit.MILLISECONDS );
		
		this.driver.manage().window().setPosition( new Point( 0, 0 ) );
		this.driver.manage().window().setSize( new Dimension( 1024, 768 ) );
	}
	// API
	public final WebDriver getDriver(){
		return this.driver;
	}
	
}
