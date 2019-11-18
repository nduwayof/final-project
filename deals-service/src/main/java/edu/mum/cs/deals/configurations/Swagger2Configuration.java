package edu.mum.cs.deals.configurations;

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
 *
 * @author nduwayofabrice
 * @version 1.0
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
        String basePackage = "edu.mum.cs.deals.controllers";
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getApiInfo() {
        String url = "https://www.linkedin.com/in/fabrice-nduwayo/";
        return new ApiInfoBuilder()
                .title("Deals REST API Doc")
                .description("More description about Deal REST API")
                .contact(new Contact("Fabrice Nduwayo", url ,"fnduwayo@mum.edu"))
                .licenseUrl(url)
                .termsOfServiceUrl(url)
                .version("1.0")
                .build();
    }
}
