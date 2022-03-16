package de.thro.vv.exercise01_server.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import de.thro.vv.exercise01_server.models.JsonResult;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class InvoiceCreator
{
    public void periodicallySendInvoices()
    {
        Thread t = new Thread(() ->
        {
            String socketUrl = System.getenv("SOCKET_HOST");
            int socketPort = Integer.parseInt(System.getenv("SOCKET_PORT"));
            while (true)
            {

                String jsonString = generateInvoiceData();
                try
                {
                    TimeUnit.SECONDS.sleep(10); // sleep 10 seconds
                    Socket s = new Socket(socketUrl, socketPort);
                    try (OutputStreamWriter out = new OutputStreamWriter(s.getOutputStream(), StandardCharsets.UTF_8))
                    {
                        System.out.println(String.format("Writing to Socket: %s", jsonString));
                        out.write(jsonString);
                    }
                } catch (IOException | InterruptedException e)
                {
                    System.out.println(e);
                }
            }
        });
        t.start();

    }

    private String generateInvoiceData(){

        Faker f = new Faker(new Locale("de"));
        JsonResult j = new JsonResult();
        j.setFirstName(f.name().firstName());
        j.setLastName(f.name().lastName());
        j.setInvoiceDate(Date.from(Instant.now()));
        j.setInvoiceAmount(f.commerce().price());
        try
        {
            var jsonGenerator = new ObjectMapper();
            jsonGenerator.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
            return jsonGenerator.writeValueAsString(j);
        } catch (JsonProcessingException e)
        {
            System.out.println(e);
        }
        return "";
    }
}
