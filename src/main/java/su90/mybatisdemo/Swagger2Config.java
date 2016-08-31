/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo;

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
 *
 * @author superman90
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("su90.mybatisdemo.web.endpoints"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("MybatisDemo Swagger2 RESTful APIs")
                .description("API Documentation")
                .contact("superman90")
                .version("1.0")
                .build();
    }
    
//    use the annotation below to annotate the endpoint
//    @ApiOperation
//    @ApiImplicitParam
//    @ApiImplicitParams(@ApiImplicitParam(
//            
//    ))
//    api explain page
//    http://localhost:8080/swagger-ui.html
    
    
}
