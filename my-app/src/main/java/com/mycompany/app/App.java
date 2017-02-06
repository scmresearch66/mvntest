package com.mycompany.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App 
{
    private static final Logger logger = LogManager.getLogger("App");

    private App() {}

    public static void main( String[] args )
    {
        logger.debug("This is an debug message");

        /* System.out.println( "Hello World!" ); */
    }
}
