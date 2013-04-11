package org.selenium.event;

import org.selenium.base.AbstractDriverNew;
import org.springframework.context.ApplicationEvent;

public final class DriverChangeEventNew extends ApplicationEvent {
    private static final long serialVersionUID = 4694101359676710432L;
    private final AbstractDriverNew driver;

    public DriverChangeEventNew(final Object sourceToSet, final AbstractDriverNew driverToSet) {
        super(sourceToSet);

        this.driver = driverToSet;

    }

    //

    public final AbstractDriverNew getDriver() {
        return this.driver;
    }

}
