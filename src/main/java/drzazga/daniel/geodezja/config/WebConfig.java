package drzazga.daniel.geodezja.config;

import drzazga.daniel.geodezja.Dtos.UserDto;
import drzazga.daniel.geodezja.model.User;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

@Configuration
public class WebConfig {

    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public MapperFacade mapper(){
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(User.class, UserDto.class)
                .field("roles","rolesDto")
                .byDefault()
                .register();

        return mapperFactory.getMapperFacade();
    }
}
