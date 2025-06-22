package com.teach3035.modulo6_desafio.repository;

import com.teach3035.modulo6_desafio.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    boolean existsByUsername(String username);
}
