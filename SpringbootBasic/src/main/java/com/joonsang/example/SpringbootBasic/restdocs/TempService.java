package com.joonsang.example.SpringbootBasic.restdocs;

import com.joonsang.example.SpringbootBasic.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TempService {

    @Autowired
    private TempRepository tempRepository;

    public List<User> readUserAll() {
        return tempRepository.findAll();
    }

}
