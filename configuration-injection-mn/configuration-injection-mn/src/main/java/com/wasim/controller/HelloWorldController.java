package com.wasim.controller;

import com.wasim.services.HelloWorldTranslationConfig;
import com.wasim.services.MyService;
import io.micronaut.context.annotation.Property;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/hello")
public class HelloWorldController {

    private static final Logger LOG = LoggerFactory.getLogger(HelloWorldController.class);


    @Inject
    private MyService service;
    private String helloFromConfig;
    private HelloWorldTranslationConfig translationConfig;


    public HelloWorldController(MyService service,
                                @Property(name = "hello.world.message") String helloFromConfig, HelloWorldTranslationConfig translationConfig) {
        this.service = service;
        this.helloFromConfig = helloFromConfig;
        this.translationConfig = translationConfig;
    }


    @Get(produces = MediaType.TEXT_PLAIN)
    public String helloWorld(){
        LOG.debug("Called the Hello World API");
        return service.helloFromService();
    }


    @Get(uri = "/config", produces = MediaType.TEXT_PLAIN)
    public String helloConfig(){
        LOG.debug("Return Hello From Config Message: {}",helloFromConfig);
        return helloFromConfig;
    }


    @Get(uri = "/translation", produces = MediaType.APPLICATION_JSON)
    public HelloWorldTranslationConfig helloTranslation(){
        return translationConfig;
    }


}
