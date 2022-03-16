package de.thro.vv.exercise01_server.services;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.thro.vv.exercise01_server.models.JsonResult;
import org.springframework.beans.factory.annotation.Value;

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

    public StatusResultService()
    {
        this.filePath = System.getenv("JSON_STORAGE_PATH");
    }

    public List<String> checkForJsonFile()
    {
        List<String> results = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(filePath)))
        {
            paths.filter(path -> Files.isRegularFile(path)).forEach(file -> {
                var result = parseJsonFile(file);
                if(result != null){
                    results.add(result.toString());
                }
            });
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return results;
    }

    private JsonResult parseJsonFile(Path file)
    {
        try
        {
            return new ObjectMapper().readValue(Paths.get(file.toUri()).toFile(), JsonResult.class);
        } catch (JsonMappingException e)
        {
            System.out.println(e);
        } catch (JsonParseException e)
        {
            System.out.println(e);
        } catch (IOException e)
        {
            System.out.println(e);
        }
        return null;


    }
}
