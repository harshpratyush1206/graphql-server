package org.oaknorth.graphql.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApi {

    @RequestMapping("ping")
    public String ping(){
        return "ping";
    }
}
