package com.example.rd.service;

import com.example.rd.entity.TempUser;
import com.example.rd.repository.TempUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TempUserService {

    @Autowired
    private TempUserRepository tempUserRepository;


    /** 회원 조회 **/
    public List<TempUser> findAll() {
        return tempUserRepository.findAll();
    }

    /** 회원 상세 **/
    public Optional<TempUser> findUser(Long id) {
        return tempUserRepository.findById(id);
    }

    /** 회원 생성 **/
    public Long createUser(TempUser tempUser) {
        TempUser savedUser = tempUserRepository.save(tempUser);
        return savedUser.getId();
    }

    /** 회원 수정 **/
    @Transactional
    public void updateUser(Long id, TempUser tempUser) {
        Optional<TempUser> user = tempUserRepository.findById(id);

        user.ifPresent(u ->{
            u.setUsername(tempUser.getUsername());
            u.setPassword(tempUser.getPassword());
            tempUserRepository.save(u);
        });
    }

    /** 회원 수정 **/
    public void deleteUser(Long id) {
        tempUserRepository.deleteById(id);
    }
}
