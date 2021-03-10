package com.example.rd.controller;

import com.example.rd.entity.TempUser;
import com.example.rd.service.TempUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TempUserRestController {

    @Autowired
    TempUserService tempUserService;

    /** 유저 조회 **/
    @GetMapping(value = "/readAll")
    public ResponseEntity readAll() {
        List<TempUser> all = tempUserService.findAll();
        return ResponseEntity.ok().body(all);
    }

    /** 유저 상세 조회 **/
    @GetMapping(value = "/readDetail/{id}")
    public ResponseEntity readDetail(@PathVariable("id") Long id) {
        Optional<TempUser> user = tempUserService.findUser(id);
        return ResponseEntity.ok().body(user);
    }

    /** 유저 생성 **/
    @PostMapping(value = "/createUser")
    public ResponseEntity createUser(@RequestBody TempUser tempUser) {
        Long a = tempUserService.createUser(tempUser);
        return ResponseEntity.ok().body(a);
    }

    /** 유저 수정 **/
    @PostMapping(value = "/updateUser/{id}")
    public ResponseEntity updateUser(@PathVariable("id") Long id, @RequestBody TempUser tempUser) {
        tempUserService.updateUser(id, tempUser);
        Optional<TempUser> findUser = tempUserService.findUser(id);
        return ResponseEntity.ok().body(findUser);
    }

    /** 유저 삭제 **/
    @PostMapping(value = "/deleteUser/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {
        tempUserService.deleteUser(id);
        Optional<TempUser> findUser = tempUserService.findUser(id);
        return ResponseEntity.ok().body(findUser);
    }




}
