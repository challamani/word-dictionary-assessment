package com.happiestminds.assessment;

import com.happiestminds.assessment.property.FileStorageConstraints;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.happiestminds.assessment"})
@EnableConfigurationProperties({
        FileStorageConstraints.class})
public class HappiestMindsAssessmentApp {

    public static void main(String[] args) {
        SpringApplication.run(HappiestMindsAssessmentApp.class, args);
    }
}
