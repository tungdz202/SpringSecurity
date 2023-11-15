/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.SpringSecurity.Repository;

import com.example.SpringSecurity.Entity.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ADMIN
 */

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    
    UserEntity findByName(String name);
    
    @Query(value = "SELECT * FROM anonymous.Users u WHERE u.name = :username AND u.email = :email",nativeQuery = true)
    List<UserEntity> findUsersByUsernameAndEmail(@Param("username") String username, @Param("email") String email);
}

