package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.UserRecord;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

@EnableScan
public interface UserRepository extends CrudRepository<UserRecord,
        UUID> {

}
