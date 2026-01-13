package com.wayinfotechsolutions.wayinfotechsolutionswebsite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.lang.reflect.Array;
import java.util.Arrays;

@Configuration
public class CorsGlobalConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
//        setAllowedOrigins jb use krna h jb koi fix url ko support krna h development k liye setAllowedOriginsPattern ka use krte h yh h
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173",
//                "http://192.168.29.*:5173","https://www.wayinfotechsolutions.com"));
        configuration.setAllowedOriginPatterns(Arrays.asList(
                "http://localhost:5173",
                "http://192.168.29.*:5173",
                "http://192.168.29.*:3001",
                "https://www.wayinfotechsolutions.com"
        ));

        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);
        return source;
    }


}
