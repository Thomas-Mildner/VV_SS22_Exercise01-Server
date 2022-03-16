package de.thro.vv.exercise01_server;

import de.thro.vv.exercise01_server.services.InvoiceCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Exercise01ServerApplication
{
    public static void main(String[] args)
    {
        System.out.println("Reading Environment Variables...");
        System.out.println(String.format("JsonStoragePath: %s", System.getenv("JSON_STORAGE_PATH")));
        System.out.println(String.format("SocketURL: %s", System.getenv("SOCKET_HOST")));
        System.out.println(String.format("SocketPort: %s", System.getenv("SOCKET_PORT")));

        InvoiceCreator c = new InvoiceCreator();
        c.periodicallySendInvoices();

        SpringApplication.run(Exercise01ServerApplication.class, args);
    }

}
