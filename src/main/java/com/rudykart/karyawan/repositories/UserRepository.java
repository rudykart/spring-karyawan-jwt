package com.rudykart.karyawan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rudykart.karyawan.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
