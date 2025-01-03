package com.mikeyeom.memorablegram.like.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mikeyeom.memorablegram.like.domain.Like;
import com.mikeyeom.memorablegram.like.repository.LikeRepository;

@Service
public class LikeService {
	
	private LikeRepository likeRepository;
	
	public LikeService(LikeRepository likeRepository) {
		this.likeRepository = likeRepository;
	}

public boolean addLike(int postId, int userId) {
	
	
	Like like = Like.builder().postId(postId).userId(userId).build();
	
	try {
		likeRepository.save(like);
		return true;
	} catch (Exception e) {
		return false;
	}
	}

	public boolean deleteLike(int userId, int postId) {
		
		Optional<Like> optionalLike = likeRepository.findByPostIdAndUserId(postId, userId);
		
		if(optionalLike.isPresent()) {
			Like like = optionalLike.get();
			
			likeRepository.delete(like);
			return true;
		} else {
			return false;
		}
	}
	
	public void deleteLikePostId(int postId) {
		likeRepository.deleteByPostId(postId);
	}

	public int getLikeCount(int postId){
		return likeRepository.countByPostId(postId);
	}
	
	public boolean isLike(int postId, int userId) {
		int count = likeRepository.countByPostIdAndUserId(postId, userId);
		
		if(count > 0) {
			return true;
		} else {
			return false;
		}
	}
}
