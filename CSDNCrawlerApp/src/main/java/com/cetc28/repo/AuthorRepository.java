package com.cetc28.repo;

import com.cetc28.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,String> {
    Author findByName(String name);
}
