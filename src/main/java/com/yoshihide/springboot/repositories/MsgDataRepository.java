package com.yoshihide.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yoshihide.springboot.MsgData;

@Repository
public interface MsgDataRepository extends JpaRepository<MsgData, Long> {

	public List<MsgData> findAll();

}