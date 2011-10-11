package org.selenium.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.base.AbstractDriver;
import org.selenium.util.Selenium2Utils;

import com.google.common.base.Preconditions;

public final class ConfirmationDriver< D extends AbstractDriver > extends AbstractDriver{
	
	private final D sourceDriver;
	
	public ConfirmationDriver( final WebDriver driverToSet, final D sourceDriverToSet ){
		super( driverToSet );
		
		Preconditions.checkNotNull( sourceDriverToSet );
		this.sourceDriver = sourceDriverToSet;
		
	}
	
	// API
	
	public final D confirm(){
		Selenium2Utils.Wait.waitForElementFoundById( this.driver, "gwt-debug-confirmAction", 1 );
		this.driver.findElement( By.id( "gwt-debug-confirmAction" ) ).click();
		
		Selenium2Utils.Wait.waitForElementNotFoundById( this.driver, "gwt-debug-waitMask", 2 );
		
		return this.sourceDriver;
	}
	
	public final D cancel(){
		this.driver.findElement( By.id( "gwt-debug-cancelAction" ) ).click();
		return this.sourceDriver;
	}
	
}
