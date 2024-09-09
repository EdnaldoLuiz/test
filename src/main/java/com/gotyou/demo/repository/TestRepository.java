package com.gotyou.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gotyou.demo.model.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    
}
