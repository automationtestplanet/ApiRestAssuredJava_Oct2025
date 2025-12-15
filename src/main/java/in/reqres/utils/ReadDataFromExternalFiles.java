package in.reqres.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.reqres.request.models.CreateUser;

import java.nio.file.Paths;
import java.nio.file.Files;

public class ReadDataFromExternalFiles {

    public CreateUser readDataFromJsonFile(String jsonFilePath) {
        try {
            ObjectMapper objMapper = new ObjectMapper();
            return objMapper.readValue(Paths.get(jsonFilePath).toFile(), CreateUser.class);
        } catch (Exception e) {
            System.out.println("Exception Occurred while reading the data from Json: " + jsonFilePath + " : " + e.getMessage());
            return null;
        }
    }

    public String readDataFromJsonFileAsString(String jsonFilePath) {
        try {
            return Files.readString(Paths.get(jsonFilePath));
        } catch (Exception e) {
            System.out.println("Exception Occurred while reading the data from Json: " + jsonFilePath + " : " + e.getMessage());
            return null;
        }
    }
}
