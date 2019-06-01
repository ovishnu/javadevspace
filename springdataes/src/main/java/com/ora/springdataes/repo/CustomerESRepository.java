package com.ora.springdataes.repo;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.ora.springdataes.model.Customer;

@Repository
public interface CustomerESRepository extends ElasticsearchRepository<Customer, Long> {

	public List<Customer> findByAddress(String address);
}
