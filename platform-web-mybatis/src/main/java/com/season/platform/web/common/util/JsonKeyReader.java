package com.season.platform.web.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.season.platform.web.exception.BadRequestException;
import com.season.platform.web.exception.RequestMissingKeyException;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Map;

/**
 * Created by jiyc on 2017/2/26.
 */
public class JsonKeyReader {

    private JsonNode node;

    public JsonKeyReader(String body) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            node = mapper.readTree(body);
        } catch (IOException e) {
            throw new BadRequestException();
        }
    }

    /**
     * 读取map值并赋值到JsonNode
     *
     * @param body map键值对参数
     */
    public JsonKeyReader(Map<String, Object> body) {
        // jackson
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(body);
            node = mapper.readTree(json);
        } catch (IOException e) {
            throw new BadRequestException();
        }
    }

    /**
     * @param key
     * @param nullable true:可以为空 false:不能为空
     * @return
     */
    public String readString(final String key, final Boolean nullable) {
        if (node.has(key)) {
            String value = node.get(key).asText();
            if (StringUtils.isBlank(value) && !nullable) {
                throw new RequestMissingKeyException(key);
            }
            return node.get(key).asText();
        } else if (nullable) {
            return null;
        } else {
            throw new RequestMissingKeyException(key);
        }
    }

    public String readDefaultString(final String key, final String defaultValue) {
        return StringUtils.defaultString(readString(key, true), defaultValue);
    }

    public Integer readInteger(final String key, final Boolean nullable) {
        if (node.has(key)) {
            return node.get(key).isInt() ? node.get(key).asInt() : Integer.valueOf(node.get(key).asText());
        } else if (nullable) {
            return null;
        } else {
            throw new RequestMissingKeyException(key);
        }
    }

    public Integer readDefaultInteger(final String key, final Integer defaultValue) {
        Integer value = readInteger(key, true);
        return value == null ? defaultValue : value;
    }

    public Boolean readBoolean(final String key, final Boolean nullable) {
        if (node.has(key)) {
            return node.get(key).isBoolean() ? node.get(key).asBoolean() : Boolean.valueOf(node.get(key).asText());
        } else if (nullable) {
            return null;
        } else {
            throw new RequestMissingKeyException(key);
        }
    }

    public Boolean readDefaultBoolean(final String key, final Boolean defaultValue) {
        Boolean value = readBoolean(key, true);
        return value == null ? defaultValue : value;
    }

    public <T> T readObject(final String key, final Boolean nullable, final Class<T> type) {
        if (node.has(key)) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                return mapper.treeToValue(node.get(key), type);
            } catch (JsonProcessingException e) {
                throw new BadRequestException();
            }
        } else if (nullable) {
            return null;
        } else {
            throw new RequestMissingKeyException(key);
        }
    }
}
