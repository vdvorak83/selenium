package org.selenium.base;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.selenium.setup.SeleniumComponent;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = {//@formatter:off
	"classpath:/selenium_scan.xml",
	"classpath:/selenium_config.xml"
} )// @formatter:on
public abstract class AbstractTest{
	protected static WebDriver webDriver;
	private AbstractDriver pageDriver;
	
	/**
	 * - note: this makes the concurrent execution of tests impossible (for now); when that is a goal - move this around as a parameter
	 */
	public static AbstractTest currentTest;
	
	// fixtures
	
	@Before
	public void before(){
		currentTest = this;
	}
	
	@BeforeClass
	public static void beforeClass(){
		webDriver = new SeleniumComponent().getDriver();
	}
	@AfterClass
	public static void afterClass(){
		webDriver.quit();
	}
	
	//
	
	public final < D extends AbstractDriver >D getDriver(){
		return (D) this.pageDriver;
	}
	
	public final WebDriver getWebDriver(){
		return webDriver;
	}
	
	public final void setPageDriver( final AbstractDriver pageDriverToSet ){
		this.pageDriver = pageDriverToSet;
	}
	
}
