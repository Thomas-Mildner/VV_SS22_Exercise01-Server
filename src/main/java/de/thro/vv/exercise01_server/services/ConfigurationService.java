package de.thro.vv.exercise01_server.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigurationService
{
    public static final String JSON_STORAGE_PATH = "JSON_STORAGE_PATH";
    public static final String SOCKET_HOST = "SOCKET_HOST";
    public static final String SOCKET_PORT = "SOCKET_PORT";

    private static final Logger LOGGER = LogManager.getLogger(ConfigurationService.class);

    public static String readEnvironmentVariable(String envName){
        try
        {
            return System.getenv(envName);
        }
        catch(Exception ex)
        {
            LOGGER.error(ex);
            return "";
        }
    }
}
