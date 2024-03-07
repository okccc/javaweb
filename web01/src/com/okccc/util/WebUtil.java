package com.okccc.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.okccc.common.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @Author: okccc
 * @Date: 2023/12/13 10:22:57
 * @Desc: 后面会用框架代替
 */
public class WebUtil {

    private static final ObjectMapper objectMapper;

    // 初始化objectMapper
    static {
        objectMapper = new ObjectMapper();
        // 设置JSON和Object转换时的时间日期格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    // 从请求中获取JSON串并转换为Object
    public static <T> T readJson(HttpServletRequest request, Class<T> clazz){
        T t;
        BufferedReader reader;
        try {
            reader = request.getReader();
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null){
                sb.append(line);
            }
            t = objectMapper.readValue(sb.toString(), clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return t;
    }

    // 将Result对象转换成JSON串并放入响应对象
    public static void writeJson(HttpServletResponse response, Result result){
        response.setContentType("application/json;charset=UTF-8");
        try {
            String json = objectMapper.writeValueAsString(result);
            response.getWriter().write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
