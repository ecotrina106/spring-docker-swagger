package com.example.persistence.repository;

import com.example.persistence.entity.ClubEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends ListCrudRepository<ClubEntity, Long> {
}
