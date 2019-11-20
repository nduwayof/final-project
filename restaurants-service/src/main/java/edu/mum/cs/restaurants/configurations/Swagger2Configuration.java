package edu.mum.cs.restaurants.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The type Swagger 2 configuration.
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * Api docket docket.
     *
     * @return the docket
     */
    @Bean
    public Docket apiDocket() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("edu.mum.cs.restaurants.controllers"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getApiInfo() {

        return new ApiInfoBuilder()
                .title("Restaurants REST API Doc")
                .description("More description about Restaurant REST API")
                .contact(new Contact("Fabrice Nduwayo","https://www.linkedin.com/in/fabrice-nduwayo/","fnduwayo@mum.edu"))
                .licenseUrl("https://www.linkedin.com/in/fabrice-nduwayo/")
                .termsOfServiceUrl("https://www.linkedin.com/in/fabrice-nduwayo/")
                .version("1.0")
                .build();
    }
}
