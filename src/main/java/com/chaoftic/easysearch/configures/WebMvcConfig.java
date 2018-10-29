package com.chaoftic.easysearch.configures;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
        Gson gson = new GsonBuilder().serializeNulls().setDateFormat("dd-MM-yyyy").create();
        converter.setGson(gson);
//        FastJsonConfig config = new FastJsonConfig();
//        config.setSerializerFeatures(SerializerFeature.WriteMapNullValue);//保留空的字段
        //SerializerFeature.WriteNullStringAsEmpty,//String null -> ""
        //SerializerFeature.WriteNullNumberAsZero//Number null -> 0
        // 按需配置，更多参考FastJson文档哈

//        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
        converters.clear();
        converters.add(converter);
    }

}
