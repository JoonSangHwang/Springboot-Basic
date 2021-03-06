package com.joonsang.example.SpringbootBasic.restdocs;

import com.joonsang.example.SpringbootBasic.controller.SampleController;
import com.joonsang.example.SpringbootBasic.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
public class SampleRestController {

    @Autowired
    private TempService tempService;


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




    @GetMapping(value = "AA")
    public ResponseEntity aa() {

        List<User> all = tempService.readUserAll();

        return ResponseEntity.ok().body(all);
    }


}
