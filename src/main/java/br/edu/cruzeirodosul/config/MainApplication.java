package br.edu.cruzeirodosul.config;

import java.util.Date;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import br.edu.cruzeirodosul.business.util.CustomDateDeserializer;
import br.edu.cruzeirodosul.business.util.CustomDateSerializer;

@SpringBootApplication
@ComponentScan({"br.edu.cruzeirodosul.*"})
@EnableEurekaClient
@EnableEncryptableProperties
@Configuration
public class MainApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}	

	@Bean
	public MessageSource messageSource() {
	ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	messageSource.setBasename("classpath:br/edu/cruzeirodosul/locale/messages");
	messageSource.setDefaultEncoding("UTF-8");
	return messageSource;
	}	
	
	@Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("2MB");
        factory.setMaxRequestSize("2MB");
        return factory.createMultipartConfig();
    }
	
	@Bean
    public Jackson2ObjectMapperBuilderCustomizer addCustomSerialization() {
      return new Jackson2ObjectMapperBuilderCustomizer() {
         @Override
         public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
        	 jacksonObjectMapperBuilder.deserializerByType(Date.class, new CustomDateDeserializer());
             jacksonObjectMapperBuilder.serializerByType(Date.class, new CustomDateSerializer());
         }
      };
    }
}
