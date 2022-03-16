package de.thro.vv.exercise01_server.services;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.thro.vv.exercise01_server.models.InvoiceDocument;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StatusResultService
{
    private final String filePath;
    private static final Logger LOGGER = LogManager.getLogger(StatusResultService.class);

    public StatusResultService()
    {
        this.filePath = ConfigurationService.readEnvironmentVariable(ConfigurationService.JSON_STORAGE_PATH);
    }

    public List<String> checkForJsonFile()
    {
        List<String> invoiceDocuments = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(filePath)))
        {
            paths.filter(Files::isRegularFile).forEach(file -> {
                var invoiceDocument = parseJsonFile(file);
                if(invoiceDocument != null){
                    invoiceDocuments.add(invoiceDocument.toString());
                }
            });
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return invoiceDocuments;
    }

    private InvoiceDocument parseJsonFile(Path file)
    {
        try
        {
            return new ObjectMapper().readValue(Paths.get(file.toUri()).toFile(), InvoiceDocument.class);
        } catch (IOException e)
        {
            LOGGER.error(e);
        }
        return null;
    }
}
