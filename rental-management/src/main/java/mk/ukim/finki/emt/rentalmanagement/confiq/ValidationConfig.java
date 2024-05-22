package mk.ukim.finki.emt.rentalmanagement.confiq;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;
import java.util.Set;

@Configuration
public class ValidationConfig {

    @Bean
    public Validator validator() {
        return new Validator() {
            @Override
            public <T> Set<ConstraintViolation<T>> validate(T t, Class<?>... classes) {
                return null;
            }

            @Override
            public <T> Set<ConstraintViolation<T>> validateProperty(T t, String s, Class<?>... classes) {
                return null;
            }

            @Override
            public <T> Set<ConstraintViolation<T>> validateValue(Class<T> aClass, String s, Object o, Class<?>... classes) {
                return null;
            }

            @Override
            public BeanDescriptor getConstraintsForClass(Class<?> aClass) {
                return null;
            }

            @Override
            public <T> T unwrap(Class<T> aClass) {
                return null;
            }

            @Override
            public ExecutableValidator forExecutables() {
                return null;
            }
        };
    }
}
