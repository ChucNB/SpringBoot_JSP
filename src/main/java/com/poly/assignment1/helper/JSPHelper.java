package com.poly.assignment1.helper;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class JSPHelper {

    private static Map<String, String> stringToMap(String string) {
        Map<String, String> mapParam = new HashMap<>();
        if (string == null) {
            return new HashMap<>();
        }
        for (String s : string.split("&")) {
            String[] temp = s.split("=");
            try {
                String k = temp[0];
                String v = temp[1];
                mapParam.put(k, v);
            } catch (Exception e) {
                System.out.println("Lỗi param");
            }
        }
        return mapParam;
    }

    public static String initParam(Map<String, String> query, String paramAdd) {
        Map<String, String> mapParam1 = new HashMap<>();
        mapParam1.putAll(query);
        Map<String, String> mapParam2 = stringToMap(paramAdd);
        String sortOder;
        try {
            if (mapParam1.get("sortBy").equals(mapParam2.get("sortBy"))) {
                sortOder = mapParam1.get("sortOrder").equals("ASC") ? "DESC" : "ASC";
            } else
                sortOder = "ASC";

        } catch (Exception e) {
            sortOder = "ASC";
        }
        mapParam1.put("sortOrder", sortOder);
        mapParam1.putAll(mapParam2);

        if (mapParam1 != null && mapParam1.size() > 0)
            for (String key : new HashSet<>(mapParam1.keySet()))
                if (mapParam1.get(key).equals("-1") || mapParam1.get(key).equals("") || mapParam1.get(key) == null)
                    mapParam1.remove(key);
        mapParam1.remove("page");
        String result = "?" + mapParam1.entrySet().stream().map(es -> es.getKey() + "=" + es.getValue())
                .reduce("", (s, s2) -> s + (s.isBlank() ? s2 : "&" + s2));
        System.out.println(result);

        return result;

    }

    public static String mapToQuery(Map<String, String> mapParam) {
        if (mapParam != null && mapParam.size() > 0)
            for (String key : new HashSet<>(mapParam.keySet()))
                if (mapParam.get(key).equals("-1"))
                    mapParam.remove(key);
        String result = "?" + mapParam.entrySet().stream().map(es -> es.getKey() + "=" + es.getValue())
                .reduce("", (s, s2) -> s + (s.isBlank() ? s2 : "&" + s2));
        return result;
    }

}
