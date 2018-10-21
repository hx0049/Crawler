package com.cetc28.repo;

import com.cetc28.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,String> {
    Article findByUrl(String url);
}
