package ru.unclediga.saburov.register.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.unclediga.saburov.register.dao.PersonDao;

@Configuration
@PropertySource("classpath:/register.properties")
public class MarriageConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(MarriageConfig.class);
    @Bean
    public PersonDao buildPersonDao(){
        LOGGER.info("PersonDao is being created...");
        return new PersonDao();
    }
}
