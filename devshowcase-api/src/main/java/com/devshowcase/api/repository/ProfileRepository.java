package com.devshowcase.api.repository;

import com.devshowcase.api.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    boolean existsByEmail(String email);
}

