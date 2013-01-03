package org.selenium.base;

import org.selenium.spring.SpringContext;

public class SmokeUriUtil{
	
	private SmokeUriUtil(){
		throw new AssertionError();
	}
	
	// API
	
	public static String getStoreBaseUri(){
		return get( "http.protocol" ) + "://" + get( "http.host" ) + ":" + get( "http.port" ) + get( "http.store.web.path" );
	}
	
	public static String getProductCockpitBaseUri(){
		return get( "http.protocol" ) + "://" + get( "http.host" ) + ":" + get( "http.port" ) + get( "http.cockpit.product.web.path" );
	}
	
	// util
	
	static String get( final String key ){
		return SpringContext.context().getEnvironment().getProperty( key );
	}
	
}
