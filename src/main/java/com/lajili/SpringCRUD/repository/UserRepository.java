package com.lajili.SpringCRUD.repository;

import com.lajili.SpringCRUD.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    public Long countById(Integer id);
}
