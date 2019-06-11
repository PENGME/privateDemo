package com.pm.demo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    //定义jackson对象
    private static final ObjectMapper MAPPER=new ObjectMapper();

    /**
     * 将对象转化成json字符串
     * @param data
     * @return
     */
    public static String objectToJson(Object data){
        try{
            String string=MAPPER.writeValueAsString(data);
            return string;
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json结果集转化为对象
     * @param jsonData json数据
     * @param beanType
     * @param <T>
     * @return
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
