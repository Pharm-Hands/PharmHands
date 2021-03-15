package com.pharmhands.repositories;

import com.pharmhands.models.User;
import com.pharmhands.models.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {

    @Query("FROM UserRoles WHERE role_name = ?1")
    UserRoles findByRoleName(String role_name);

}
