package com.fev.blog.blogger_app.authentication.repository;

import com.fev.blog.blogger_app.authentication.entity.SecurityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecurityUserDetailsRepository extends JpaRepository<SecurityUser, String> {

    Optional<SecurityUser> findBySecurityUserName(String username);
}
