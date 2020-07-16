//package io.spring.trucker.config;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebMvc {
//    @Bean
//    public WebMvcConfigurer webMvc(){
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**").allowedMethods("POST","GET", "PUT","DELETE","PATCH").allowedHeaders("*");
//            }
//        };
//    }
//}