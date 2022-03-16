package de.thro.vv.exercise01_server;

import de.thro.vv.exercise01_server.services.ConfigurationService;
import de.thro.vv.exercise01_server.services.InvoiceCreationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static de.thro.vv.exercise01_server.services.ConfigurationService.readEnvironmentVariable;

@SpringBootApplication
public class Exercise01ServerApplication
{
    private static final Logger LOGGER = LogManager.getLogger(Exercise01ServerApplication.class);

    public static void main(String[] args)
    {
        boolean validStartupParameters = startupChecks();
        if(!validStartupParameters){
            LOGGER.warn("Startup Parameter not valid - Exiting App");
           System.exit(-1);
        }

        InvoiceCreationService c = new InvoiceCreationService();
        c.periodicallySendInvoices();

        SpringApplication.run(Exercise01ServerApplication.class, args);
    }

    private static boolean startupChecks(){
        try{
            LOGGER.info("Reading Environment Variables...");
            String jsonStoragePath = readEnvironmentVariable(ConfigurationService.JSON_STORAGE_PATH);
            String socketHost = readEnvironmentVariable(ConfigurationService.SOCKET_HOST);
            String socketPort = readEnvironmentVariable(ConfigurationService.SOCKET_PORT);

            LOGGER.info(String.format("JsonStoragePath: %s", jsonStoragePath));
            LOGGER.info(String.format("SocketURL: %s", socketHost));
            LOGGER.info(String.format("SocketPort: %s", socketPort));

            LOGGER.info("Check Startup Parameters for valid Values");
            if(jsonStoragePath == null || jsonStoragePath.isEmpty() || socketHost == null ||  socketHost.isEmpty()){
                LOGGER.warn("INVALID: JsonStoragePath or SocketHost is empty");
                return false;
            }
            int parsedPort = Integer.parseInt(socketPort);
            if(parsedPort <= 0){
                LOGGER.warn("INVALID: SocketPort could not be parsed to valid Integer");
                return false;
            }

            LOGGER.info("Startup Parameter are valid -- Starting APP");
            return true;

        }
        catch(Exception ex){
            LOGGER.error(ex);
            return false;
        }
    }
}
