package org.example.config;

import org.example.bean.Lion;
import org.example.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages= {"org.example.proxy", "org.example.service", "org.example.repository"})
public class ProjectConfig {

//    @Bean
//    public Lion lion() {
//        Lion lion = new Lion();
//        lion.setName("babyLion");
//        return lion;
//    }
//
//    @Bean
//    public Person person(Lion lion) {
//        Person p = new Person();
//        p.setName("hyeji");
//        p.setLion(lion);
//        return p;
//    }

}
