package com.example.demo.repository;

import com.example.demo.model.Ranking;
import org.springframework.data.repository.CrudRepository;

public interface RankingRepository extends CrudRepository<Ranking, String> {
}
