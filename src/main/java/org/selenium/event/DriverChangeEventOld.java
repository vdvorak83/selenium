package org.selenium.event;

import org.selenium.base.AbstractDriverOld;
import org.springframework.context.ApplicationEvent;

public final class DriverChangeEventOld extends ApplicationEvent {
    private static final long serialVersionUID = 4694101359676710432L;
    private final AbstractDriverOld driver;

    public DriverChangeEventOld(final Object sourceToSet, final AbstractDriverOld driverToSet) {
        super(sourceToSet);

        this.driver = driverToSet;

    }

    //

    public final AbstractDriverOld getDriver() {
        return this.driver;
    }

}
