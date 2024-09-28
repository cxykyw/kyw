package com.kyw.spring.repository;

import com.kyw.spring.domain.Demo01;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Demo01Repository extends JpaRepository<Demo01, String> {

    List<Demo01> findByUserNameLikeOrderByUserNameDesc(String userName, Pageable pageable);
}
