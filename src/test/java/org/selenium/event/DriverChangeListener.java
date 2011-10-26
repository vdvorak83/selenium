package org.selenium.event;

import org.selenium.base.AbstractTest;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public final class DriverChangeListener implements ApplicationListener< DriverChangeEvent >{
	
	@Override
	public final void onApplicationEvent( final DriverChangeEvent event ){
		if( AbstractTest.currentTest != null ){
			AbstractTest.currentTest.setPageDriver( event.getDriver() );
		}
	}
}
