package com.ora.springdataes.repo;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.ora.springdataes.model.UserDoc;

@Repository
public interface UserESRepository extends ElasticsearchRepository<UserDoc, Long> {

}
