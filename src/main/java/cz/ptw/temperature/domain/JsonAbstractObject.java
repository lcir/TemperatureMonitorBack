package cz.ptw.temperature.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

/**
 * User: T945135
 * Date: 9.9.14
 * Time: 11:57
 */
public abstract class JsonAbstractObject {

    protected ObjectMapper objectMapper = new ObjectMapper();

    public String toJson() throws JsonProcessingException {
        return objectMapper.writeValueAsString(this);
    }
}
