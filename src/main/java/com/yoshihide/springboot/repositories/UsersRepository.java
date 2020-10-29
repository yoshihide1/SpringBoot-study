package com.yoshihide.springboot.repositories;

import org.springframework.data.repository.CrudRepository;

import com.yoshihide.springboot.Users;

public interface UsersRepository extends CrudRepository<Users, Integer> {

}
