package com.mikeyeom.memorablegram.like.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mikeyeom.memorablegram.like.domain.Like;

public interface LikeRepository extends JpaRepository<Like, Integer>{
	
	public int countByPostId(int postId);
	
	public int countByPostIdAndUserId(int postId, int userId);
}
