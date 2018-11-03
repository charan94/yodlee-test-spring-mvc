package com.yodlee.yodleetest.dao;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.yodlee.yodleetest.modal.Provider;

@Repository
public interface ProviderRepository extends MongoRepository<Provider, String> {

	Provider findByName(String name);
}
