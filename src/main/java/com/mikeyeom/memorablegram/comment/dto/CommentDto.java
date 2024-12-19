package com.mikeyeom.memorablegram.comment.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentDto {
	
	private int commentId;
	private int userId;
	
	private String loginId;
	private String contents;
}
