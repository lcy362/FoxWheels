package com.fox.tools.xml;

import org.dom4j.*;

import java.util.*;

/**
 * Created by lcy on 2017/6/8.
 */
public class XmlCommonParser {
    public static Map<String, String> parseDoc(String message) throws DocumentException {
        return parseDoc(message, false);
    }

    public static Map<String, String> parseDoc(String message, boolean simpleKey) throws DocumentException {
        Map<String, String> result = new HashMap<>();
        Document doc = DocumentHelper.parseText(message);
        Element root = doc.getRootElement();
        for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
            Element e = (Element) iterator.next();
            putAll(result, parseElement(e, simpleKey));
        }
        return result;
    }

    private static Map<String, String> parseElement(Element e, Boolean simpleKey) {
        Map<String, String> result = new HashMap<>();
        List list = e.elements();
        if (list.size() == 0) {
            return Collections.emptyMap();
        }
        for (int i = 0; i < list.size(); i++) {
            Element iter = (Element) list.get(i);
            List attributes = iter.attributes();
            for (int j = 0; j < attributes.size(); j++) {
                Attribute attribute = (Attribute) attributes.get(j);
                String key;
                if (simpleKey) {
                    key = attribute.getName();
                } else {
                    key = iter.getName() + attribute.getName();
                }
                put(result, key, attribute.getValue());
            }
            if (iter.elements().size() > 0) {
                putAll(result, parseElement(iter, simpleKey));
            } else {
                String key;
                if (simpleKey) {
                    key = iter.getName();
                } else {
                    key = e.getName() + iter.getName();
                }
                put(result, key, iter.getText());
            }
        }
        return result;
    }

    private static void put(Map<String, String> map, String key, String value) {
        key = key.trim().toLowerCase();
        value = value.trim();
        if (map.containsKey(key)) {
            map.put(key, map.get(key) + "##" + value);
        } else {
            map.put(key, value);
        }
    }

    private static void putAll(Map<String, String> target, Map<String, String> source) {
        for (Map.Entry<String, String> entry : source.entrySet()) {
            put(target, entry.getKey(), entry.getValue());
        }
    }

}
