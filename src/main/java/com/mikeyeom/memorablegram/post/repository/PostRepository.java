package com.mikeyeom.memorablegram.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mikeyeom.memorablegram.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{
	
	public List<Post> findAllByOrderByIdDesc();
	
}