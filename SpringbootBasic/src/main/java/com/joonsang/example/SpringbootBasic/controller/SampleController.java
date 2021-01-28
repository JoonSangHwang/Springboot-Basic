package com.joonsang.example.SpringbootBasic.controller;

import com.joonsang.example.SpringbootBasic.init.JoonsangProperties;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class SampleController {

    @Value("${app.master}")
    private String name;

    @Autowired
    JoonsangProperties joonsangProperties;

    @GetMapping("hateoasTest")
    public EntityModel<Hello> hello() {
        Hello hello = new Hello();
        hello.setName(this.name);
        hello.setPrefix("Prefix");

        System.out.println("==============================");
        System.out.println("joonsangProperties");
        System.out.println("master           : " + joonsangProperties.getMaster());
        System.out.println("fullname         : " + joonsangProperties.getFullname());
        System.out.println("exam1            : " + joonsangProperties.getExam1());
        System.out.println("exam2            : " + joonsangProperties.getExam2());
        System.out.println("exam3            : " + joonsangProperties.getExam3());
        System.out.println("sessionTimeout   : " + joonsangProperties.getSessionTimeout());
        System.out.println("==============================");

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
