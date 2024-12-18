package com.mikeyeom.memorablegram.post.dto;

import java.util.List;

import com.mikeyeom.memorablegram.comment.domain.Comment;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CardDTO {
	
	private int userId;
	private int postId;
	
	private String contents;
	private String imagePath;
	
	private String loginId;
	
	private int likeCount;
	private boolean isLike;
	
	List<Comment> commentList; 
}