package com.example.BlogAppProject.Repository;

import com.example.BlogAppProject.Entity.PostEntity;
;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity,Long> {
}

