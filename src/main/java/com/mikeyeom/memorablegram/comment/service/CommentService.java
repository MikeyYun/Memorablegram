package com.mikeyeom.memorablegram.comment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mikeyeom.memorablegram.comment.domain.Comment;
import com.mikeyeom.memorablegram.comment.dto.CommentDto;
import com.mikeyeom.memorablegram.comment.repository.CommentRepository;
import com.mikeyeom.memorablegram.domain.User;
import com.mikeyeom.memorablegram.post.repository.PostRepository;
import com.mikeyeom.memorablegram.service.UserService;

@Service
public class CommentService {
	
	private CommentRepository commentRepository;
	private UserService userService;
	
	public CommentService(CommentRepository commentRepository, UserService userService) {
		this.commentRepository = commentRepository;
		this.userService = userService;
	}
	
	public boolean addComment(int postId, int userId, String contents) {
		
		Comment comment = Comment.builder()
		.postId(postId)
		.userId(userId)
		.contents(contents)
		.build();
		
		try {
			commentRepository.save(comment);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void deleteCommentByPostId(int postId) {
		commentRepository.deleteByPostId(postId);
	}
	
	public List<CommentDto> getCommentList(int postId) {
		
		List<Comment> commentList = commentRepository.findBypostId(postId);
		
		List<CommentDto> commentDtoList = new ArrayList<>();
		
		for(Comment comment : commentList) {
			
			int userId = comment.getUserId();
			
			User user = userService.getUserById(userId);
			
			CommentDto commentDto = CommentDto.builder()
			.commentId(comment.getId())
			.userId(userId)
			.loginId(user.getLoginId())
			.contents(comment.getContents())
			.build();
			
			commentDtoList.add(commentDto);
		}
		
		return commentDtoList;
	}
}
