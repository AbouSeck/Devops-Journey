package com.abou.app.rest.Repo;

import com.abou.app.rest.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends  JpaRepository<User, Long>{
}
