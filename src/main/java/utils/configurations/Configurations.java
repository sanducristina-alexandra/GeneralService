package utils.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

@Configuration
public class Configurations {
    @Bean
    public ConversionService conversionService(){
        DefaultConversionService conversionService = new DefaultConversionService();
        return conversionService;
    }
}
