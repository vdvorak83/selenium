package org.selenium.base;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.selenium.util.Selenium2Utils;

import com.google.common.base.Preconditions;

/**
 * @author eparaschiv
 */
/**
 * @author eparaschiv
 */
/**
 * @author eparaschiv
 */
public abstract class AbstractDriver{
	
	protected final WebDriver driver;
	
	public AbstractDriver( final WebDriver driverToSet ){
		super();
		
		Preconditions.checkNotNull( driverToSet );
		this.driver = driverToSet;
	}
	
	//
	public final WebDriver getWebDriver(){
		return this.driver;
	}
	
	// API - errors of any kind
	public final boolean anyProblem(){
		// check out: http://code.google.com/p/selenium/issues/detail?id=2438
		/*
		if( this.isAlertPresent() ){
			return true;
		}
		// TODO: at some point, this could check if an basic element of the page is active and if it is not, only then check for an alert
		*/
		
		if( this.isErrorPopupPresent() ){
			return true;
		}
		
		if( this.isErrorEmbeddedPresent() ){
			return true;
		}
		
		return false;
	}
	
	@SuppressWarnings( "static-method" )
	public boolean isErrorPopupPresent(){
		throw new UnsupportedOperationException();
	}
	@SuppressWarnings( "static-method" )
	public boolean isErrorEmbeddedPresent(){
		throw new UnsupportedOperationException();
	}
	public final boolean isAlertPresent(){
		final long step0 = System.currentTimeMillis();
		Alert alert = null;
		try{
			alert = this.driver.switchTo().alert();
		}
		catch( final NoAlertPresentException e ){
			final long step1 = System.currentTimeMillis();
			System.out.println( "1: " + ( step1 - step0 ) );
			
			return false;
		}
		
		return Selenium2Utils.isAlertPresent( alert );
	}
	
	public final void dismissAlertIfPresent(){
		if( !this.anyProblem() ){
			return;
		}
		this.driver.switchTo().alert().dismiss();
	}
	
	//
	/**
	 * Verifies that the current status of the page indeed matches this driver <br>
	 * - note: this is meant to be overridden <br>
	 */
	@SuppressWarnings( "static-method" )
	public boolean isHere(){
		return true;
	}
	
	/**
	 * Waits for a specific conditions that should be true on the current page (before verifying that it is indeed the current page) <br>
	 * - note: this is meant to be overridden <br>
	 * - note: this can be implemented naively - wait for a specific element from the page, or smarter <br>
	 * for example, when an action will navigate from page A to page B (so the correct page is B) <br>
	 * then the implementation can first wait 1 second, then check that an element from the wrong page (A) is present; if it is, then the action didn't work and there is no point in waiting any longer; <br>
	 * only then does it make sense to wait some more for the condition of the correct page (B)
	 */
	@SuppressWarnings( "static-method" )
	public AbstractDriver wait( @SuppressWarnings( "unused" ) final int seconds ){
		throw new UnsupportedOperationException();
	}
	
	// navigation
	
	/**
	 * This method navigates to the page managed by the current driver <br>
	 * - note: this method is meant to be overridden <br>
	 */
	@SuppressWarnings( "static-method" )
	public AbstractDriver navigateToCurrent(){
		throw new UnsupportedOperationException();
	}
	
	public final String getCurrentUrl(){
		return this.getWebDriver().getCurrentUrl();
	}
	
	public final void back(){
		this.getWebDriver().navigate().back();
	}
	
	public final void forward(){
		this.getWebDriver().navigate().forward();
	}
	
}
