package com.example.marathon.config.datasource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {
    @Value("${springfox.documentation.enabled}")
    private Boolean swaggerEnable;



    public Docket createRestApi(String groupName, String basePackage) {
        return new Docket(DocumentationType.OAS_30)
                //添加token的参数
                .securityContexts(mySecurityContexts())
                .securitySchemes(mySecuritySchemes())
                .apiInfo(apiInfo()).groupName(groupName)
                // true 启用Swagger3.0， false 禁用（生产环境要禁用）
                .enable(swaggerEnable)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .build()
                .globalRequestParameters(getGlobalRequestParameters());
    }

    /**
     * 这里设置 swagger 认证的安全上下文
     */
    public List<SecurityContext> mySecurityContexts() {
        return Collections.singletonList(SecurityContext.builder()
                .securityReferences(Collections.singletonList(SecurityReference.builder()
                        .reference("Authorization")
                        .scopes(new AuthorizationScope[]{new AuthorizationScope("global",
                                "accessEverything")}).build())).build());
    }

    public List<SecurityScheme> mySecuritySchemes() {
        //注意，这里应对应登录token鉴权对应的k-v
        return Collections.singletonList(new ApiKey("Authorization", "Authorization", "header"));
    }


    /**
     * API 页面上半部分展示信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("马拉松管理系统")
                .description("接口文档")
                .contact(new Contact("", "http://localhost/", ""))
                .version("1.0")
                .build();
    }

    private List<RequestParameter> getGlobalRequestParameters() {
        List<RequestParameter> parameters = new ArrayList<>();
         parameters.add(new RequestParameterBuilder().name("Authorization").description("token").in(ParameterType.HEADER).build());
        return parameters;
    }


    @Bean
    public Docket account() {
        return createRestApi("马拉松管理系统", "com.example.marathon.controller");
    }

}
