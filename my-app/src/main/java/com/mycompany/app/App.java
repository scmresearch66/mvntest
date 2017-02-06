package com.mycompany.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class App {
    private static final Logger THE_LOGGER = LogManager.getLogger("App");

    private App() {
    }

    public static void main(final String[] args) {
        THE_LOGGER.debug("This is an debug message");
    }
}
