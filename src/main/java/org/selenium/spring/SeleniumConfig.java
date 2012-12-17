package org.selenium.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan( { "org.selenium" } )
@PropertySource( { "classpath:smoke-${smokeTarget:dev}.properties" } )
public class SeleniumConfig{
	
	public SeleniumConfig(){
		super();
	}
	
	// beans
	
}
