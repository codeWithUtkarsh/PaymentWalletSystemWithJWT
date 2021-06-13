package com.codeWithUtkarsh.wallet.config;
//package com.utkarsh.demo.config;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.context.SecurityContext;
//
//@Configur/ation
//public class SwaggerConfig {
//
//  private SecurityContext securityContext() {
//    return SecurityContext.builder()
//        .securityReferences(defaultAuth())
//        .forPaths(PathSelectors.any())
//        .build();
//  }
//
//  private List<SecurityReference> defaultAuth() {
//    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//    authorizationScopes[0] = authorizationScope;
//    return Arrays.asList(new SecurityReference("Authorization", authorizationScopes));
//  }
//
//}
