package org.selenium.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public final class SpringContext implements ApplicationContextAware{
	private static ApplicationContext applicationContext;
	
	private SpringContext(){
		super();
	}
	
	//
	
	public static ApplicationContext context(){
		return applicationContext;
	}
	
	@Override
	public final void setApplicationContext( final ApplicationContext context ) throws BeansException{
		SpringContext.applicationContext = context;
	}
	
}
