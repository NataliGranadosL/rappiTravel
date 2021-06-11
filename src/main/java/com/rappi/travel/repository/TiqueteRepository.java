package com.rappi.travel.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rappi.travel.entity.Tiquete;

@Repository
public interface TiqueteRepository  extends CrudRepository<Tiquete, Long>{

}
