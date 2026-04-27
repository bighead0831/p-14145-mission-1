package com.back;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.Map;

// ? -> & -> =
public class Rq {
    private final Map<String, String> paramsMap;
    private final String actionName;

    public Rq(String cmd) {
        String[] cmdBits = cmd.split("\\?", 2);

        actionName = cmdBits[0];
        String queryString = cmdBits.length>1 ? cmdBits[1].trim() : "";

//        String[] queryStringBtis = queryString.split("&");
//        for(String queryParam : queryStringBtis) {
//            String[] keyValue = queryParam.split("=", 2);
//            String key = keyValue[0].trim();
//            String value = keyValue.length>1 ? keyValue[1].trim() : "";
//
//            if(value.isEmpty()) {
//                continue;
//            }
//            paramsMap.put(key, value);
//        }
        paramsMap = Arrays.stream(queryString.split("&"))
                .map(part -> part.split("=", 2))
                .filter(bits -> bits.length>1 && !bits[1].trim().isEmpty())
                .collect(Collectors.toMap(
                        bits -> bits[0].trim(), bits -> bits[1].trim()
                ));
    }

    public String getParam(String paramName, String defaultValue) {
        return paramsMap.getOrDefault(paramName, defaultValue);
    }

    public String getActionName() {
        return actionName;
    }

    public int getParamInt(String paramName, int defaultValue) {
        String value = getParam(paramName, "");

        if(value.isEmpty()) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}