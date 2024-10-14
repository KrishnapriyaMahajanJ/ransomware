package com.scb.ransomware.repository;

import com.scb.ransomware.entities.RansomwareSummary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RansomwareSummaryRepository extends MongoRepository<RansomwareSummary, Long> {

    List<RansomwareSummary> findAllRansomwareSummariesByNameContaining(String name);
}
