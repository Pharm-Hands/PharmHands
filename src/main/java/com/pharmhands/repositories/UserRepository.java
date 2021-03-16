package com.pharmhands.repositories;

import com.pharmhands.models.PatientInfo;
import com.pharmhands.models.User;
import com.pharmhands.models.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("FROM User WHERE role = ?1 AND id = ?2")
    User getOneByRole(UserRoles role, long id);
    @Query("FROM User where full_name = ?1 and phone_number = ?2")
    User findByUserFullNameAndPhone(String full_name, String phone_number);
 }

