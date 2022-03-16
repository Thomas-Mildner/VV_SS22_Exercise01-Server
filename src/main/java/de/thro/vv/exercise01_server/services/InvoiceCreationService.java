package de.thro.vv.exercise01_server.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import de.thro.vv.exercise01_server.models.InvoiceDocument;
import de.thro.vv.exercise01_server.models.InvoiceDocumentBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class InvoiceCreationService
{
    private static final Logger LOGGER = LogManager.getLogger(InvoiceCreationService.class);

    public void periodicallySendInvoices()
    {
        Thread t = new Thread(() ->
        {
            String socketUrl = ConfigurationService.readEnvironmentVariable(ConfigurationService.SOCKET_HOST);
            int socketPort = Integer.parseInt(ConfigurationService.readEnvironmentVariable(ConfigurationService.SOCKET_PORT));
            while (true)
            {
                String jsonString = generateInvoiceData();
                try
                {
                    TimeUnit.SECONDS.sleep(10); // sleep 10 seconds
                    Socket s = new Socket(socketUrl, socketPort);
                    try (OutputStreamWriter out = new OutputStreamWriter(s.getOutputStream(), StandardCharsets.UTF_8))
                    {
                        LOGGER.info(String.format("Writing to Socket: %s", jsonString));
                        out.write(jsonString);
                    }
                } catch (IOException | InterruptedException e)
                {
                    LOGGER.error(e);
                }
            }
        });
        t.start();

    }

    private String generateInvoiceData()
    {

        Faker faker = new Faker(new Locale("de"));
        InvoiceDocument j = new InvoiceDocumentBuilder()
                .setFirstName(faker.name().firstName())
                .setLastName(faker.name().lastName())
                .setInvoiceDate(Date.from(Instant.now()))
                .setInvoiceAmount(faker.commerce().price())
                .createInvoiceDocument();
        try
        {
            var jsonGenerator = new ObjectMapper();
            jsonGenerator.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
            return jsonGenerator.writeValueAsString(j);
        } catch (JsonProcessingException e)
        {
            LOGGER.error(e);
        }
        return "";
    }
}
