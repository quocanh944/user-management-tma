package com.tma.user_management.repository;

import com.tma.user_management.model.User;
import com.tma.user_management.model.enumType.TypeUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsernameIgnoreCase(String username);

    boolean existsByEmailIgnoreCase(String email);

    Optional<User> findUserByUsername(String username);

    boolean existsUserByType(TypeUser typeUser);
    @Query("select u from User u where u.type = ?2 and (u.email like %?1% or u.lastname like %?1% or u.firstname like %?1%)")
    Page<User> findUserByType(String search, TypeUser typeUser, Pageable pageable);
}
