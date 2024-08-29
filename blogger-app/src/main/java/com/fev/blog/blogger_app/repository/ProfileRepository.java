package com.fev.blog.blogger_app.repository;

import com.fev.blog.blogger_app.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,String> {
}
