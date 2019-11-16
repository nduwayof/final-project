package edu.mum.cs.restaurants.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_12).select()
                .apis(RequestHandlerSelectors
                        .basePackage("edu.mum.cs.restaurants.controllers"))
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiEndPointsInfo());
    }
    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Restaurant Service Api")
                .description("Restaurants Service")
                .contact(new Contact("Fabrice Nduwayo", "https://www.linkedin.com/in/fabrice-nduwayo/", "fnduwayo@mum.edu"))
                .license("Restaurant 1.0")
                .licenseUrl("https://www.linkedin.com/in/fabrice-nduwayo/")
                .version("1.0")
                .build();
    }
}
