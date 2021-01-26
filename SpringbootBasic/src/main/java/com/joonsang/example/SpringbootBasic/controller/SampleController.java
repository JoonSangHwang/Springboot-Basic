package com.joonsang.example.SpringbootBasic.controller;

import lombok.Data;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class SampleController {

    @GetMapping("hateoasTest")
    public EntityModel<Hello> hello() {
        Hello hello = new Hello();
        hello.setName("준상");
        hello.setPrefix("Prefix");

        EntityModel<Hello> entityModel = new EntityModel(hello);
        entityModel.add(linkTo(methodOn(SampleController.class).hello()).withSelfRel());
        return entityModel;
    }

    @Data
    private class Hello {
        private String prefix;
        private String name;
    }
}
