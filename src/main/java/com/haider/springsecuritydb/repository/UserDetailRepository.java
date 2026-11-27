package com.haider.springsecuritydb.repository;

import com.haider.springsecuritydb.repository.api.UserDetailDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetailDO, Long> {

    UserDetailDO findByUsernameAndIsActive(String username, Boolean isActive);
}
