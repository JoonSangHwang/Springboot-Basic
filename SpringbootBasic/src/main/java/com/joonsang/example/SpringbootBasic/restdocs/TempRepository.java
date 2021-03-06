package com.joonsang.example.SpringbootBasic.restdocs;

import com.joonsang.example.SpringbootBasic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempRepository extends JpaRepository<User, Long> {

}
