package br.edu.cruzeirodosul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Configuração para a documentação de Serviços Rest
 * @author alefsilva
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	 @Bean
	    public Docket api(){
	        return new Docket(DocumentationType.SWAGGER_2)
	            .select()
	            .apis(RequestHandlerSelectors.basePackage("br.edu.cruzeirodosul.controller"))
	            .paths(PathSelectors.any())
	            .build().apiInfo(apiInfo());
	    }

	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	            .title("PROJETO ESPECIALISTA")
	            .description("Mapeamento dos Endpoints do projeto")
	            .version("V-1.0.0")
	            .termsOfServiceUrl("http://terms-of-services.url")
	            .license("LICENSE")
	            .licenseUrl("http://url-to-license.com")
	            .build();
	    }

}
