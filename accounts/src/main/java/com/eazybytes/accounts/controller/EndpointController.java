package com.eazybytes.accounts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class EndpointController {

    @Autowired
    @Qualifier("requestMappingHandlerMapping")
    private RequestMappingHandlerMapping handlerMapping;

    @GetMapping("/endpoints")
    public void getEndPoints(){
        Map<String, String> collect = handlerMapping.getHandlerMethods().keySet().stream()
                .map(RequestMappingInfo::toString)
                .collect(Collectors.toMap(
                        key -> (String) key ,
                        value -> value,
                        (v1, v2)-> v1));
        System.out.println("Collect Output  ::: "+collect);

    }
}
