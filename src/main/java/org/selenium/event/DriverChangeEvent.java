package org.selenium.event;

import org.selenium.base.AbstractDriver;
import org.springframework.context.ApplicationEvent;

public final class DriverChangeEvent extends ApplicationEvent {
    private static final long serialVersionUID = 4694101359676710432L;
    private final AbstractDriver driver;

    public DriverChangeEvent(final Object sourceToSet, final AbstractDriver driverToSet) {
        super(sourceToSet);

        this.driver = driverToSet;

    }

    //

    public final AbstractDriver getDriver() {
        return this.driver;
    }

}
