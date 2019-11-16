package edu.mum.cs.restaurants.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(SpringDataRestConfiguration.class)
public class Swagger2Configuration extends WebMvcConfigurationSupport {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors
                        .basePackage("edu.mum.cs.restaurants.controllers"))
                .paths(PathSelectors.regex("/rest.*"))
                .build().apiInfo(apiEndPointsInfo());
    }
    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Restaurants REST API")
                .description("Restaurant Service")
                .contact(new Contact("Fabrice Nduwayo", "https://www.linkedin.com/in/fabrice-nduwayo/", "fnduwayo@mum.edu"))
                .license("Restaurant service 2.0")
                .licenseUrl("https://www.linkedin.com/in/fabrice-nduwayo/")
                .version("1.0")
                .build();
    }
}
