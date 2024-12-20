package com.mikeyeom.memorablegram.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mikeyeom.memorablegram.comment.domain.Comment;

import jakarta.transaction.Transactional;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

	public List<Comment> findBypostId(int postId);
	
	@Transactional
	public void deleteByPostId(int postId);
}
