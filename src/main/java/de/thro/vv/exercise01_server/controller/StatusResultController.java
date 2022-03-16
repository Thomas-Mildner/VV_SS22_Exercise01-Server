package de.thro.vv.exercise01_server.controller;

import de.thro.vv.exercise01_server.services.StatusResultService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatusResultController
{
    @GetMapping("/")
    public String home(){
        return String.format("Server up and running - checking for new Json Files in Docker Volume mount: %s", System.getenv("JSON_STORAGE_PATH"));
    }

    @GetMapping("/result")
    public String checkStatusResult(){
        var statusResultService = new StatusResultService();
        List<String> result = statusResultService.checkForJsonFile();

        if((long) result.size() == 0){
            return "No Json File found";
        }

        StringBuilder sb = new StringBuilder();
        for(var s : result){
            sb.append(String.format("%s %s", s, System.lineSeparator()));
        }
        return String.format("Data successfully read: %s", sb);
    }
}
