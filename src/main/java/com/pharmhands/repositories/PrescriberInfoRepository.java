package com.pharmhands.repositories;

import com.pharmhands.models.PrescriberInfo;
import com.pharmhands.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriberInfoRepository extends JpaRepository<PrescriberInfo, Long>{

    PrescriberInfo findByUser(User user);

}
