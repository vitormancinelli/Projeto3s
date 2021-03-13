package com.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.domain.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {

}
