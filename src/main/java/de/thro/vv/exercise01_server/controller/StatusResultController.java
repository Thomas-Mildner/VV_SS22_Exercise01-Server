package de.thro.vv.exercise01_server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.thro.vv.exercise01_server.models.InvoiceDocument;
import de.thro.vv.exercise01_server.models.StatusResult;
import de.thro.vv.exercise01_server.services.ConfigurationService;
import de.thro.vv.exercise01_server.services.StatusResultService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
public class StatusResultController
{
    @GetMapping("/")
    public String home(){
        return String.format("Server up and running - checking for new Json Files in Docker Volume mount: %s", ConfigurationService.readEnvironmentVariable(ConfigurationService.JSON_STORAGE_PATH));
    }

    @GetMapping("/result")
    public StatusResult checkStatusResult(){
        var statusResultService = new StatusResultService();
        var mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
        var result = new StatusResult();
        List<InvoiceDocument> invoiceDocuments = statusResultService.checkForJsonFile();

        if((long) invoiceDocuments.size() == 0){
            result.setMessage("No Documents found");
            return result;
        }

        result.setMessage("Data successfully read");
        result.setInvoiceDocuments(invoiceDocuments);
        return result;
    }
}
