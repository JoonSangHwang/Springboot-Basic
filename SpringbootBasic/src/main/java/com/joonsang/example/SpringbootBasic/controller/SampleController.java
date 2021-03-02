package com.joonsang.example.SpringbootBasic.controller;

import com.joonsang.example.SpringbootBasic.entity.User;
import com.joonsang.example.SpringbootBasic.init.JoonsangProperties;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class SampleController {

    @Value("${app.master}")
    private String name;

    @Autowired
    JoonsangProperties joonsangProperties;

    @Autowired
    ModelMapper modelMapper;

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

    @GetMapping(value = "restDocsTest", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity hello2(@RequestBody User user) {

        WebMvcLinkBuilder selfLinkBuilder = linkTo(User.class).slash(user.getId());
        URI createdUri = selfLinkBuilder.toUri();

//        EntityModel<User> entityModel = new EntityModel(u);
//        entityModel.add(linkTo(methodOn(User.class)).withSelfRel());
//        entityModel.add(selfLinkBuilder.withRel("create-restDocsTest"));

//        EntityModel<User> entityModel = new EntityModel(user);
//        entityModel.add(linkTo(methodOn(SampleController.class)).withSelfRel());

        EntityModel<User> userResource = new EntityModel(user);
//        userResource.add(selfLinkBuilder.withRel("update-event"));
        userResource.add(linkTo(SampleController.class).withRel("restDocsTest"));
//        userResource.add(linkTo(methodOn(SampleController.class).hello2(user)).withSelfRel());
        return ResponseEntity.created(createdUri).body(userResource);
    }

    @Data
    private class Hello {
        private String prefix;
        private String name;
    }
}
