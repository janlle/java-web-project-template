package com.domain.dao.repository;

import com.domain.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <p>
 *
 * @author leone
 * @since 1.8
 **/
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    
}
