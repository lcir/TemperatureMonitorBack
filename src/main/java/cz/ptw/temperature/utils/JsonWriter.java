package cz.ptw.temperature.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * User: T945135
 * Date: 9.9.14
 * Time: 11:57
 */
public class JsonWriter {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object objectToWrite) throws JsonProcessingException {
        return objectMapper.writeValueAsString(objectToWrite);
    }
}
