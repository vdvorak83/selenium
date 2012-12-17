package org.selenium.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = {//@formatter:off
	"classpath:/selenium_scan.xml",
	"classpath:/selenium_config.xml"
} )// @formatter:on
public abstract class AbstractTest extends AbstractBaseTest{
	//
}
