package com.gotyou.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gotyou.demo.model.GeoLocation;

@Repository
public interface GeoLocationRepository extends JpaRepository<GeoLocation, Long> {}
