package com.mikeyeom.memorablegram.post.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mikeyeom.memorablegram.comment.dto.CommentDto;
import com.mikeyeom.memorablegram.comment.service.CommentService;
import com.mikeyeom.memorablegram.common.FileManager;
import com.mikeyeom.memorablegram.domain.User;
import com.mikeyeom.memorablegram.like.service.LikeService;
import com.mikeyeom.memorablegram.post.domain.Post;
import com.mikeyeom.memorablegram.post.dto.CardDTO;
import com.mikeyeom.memorablegram.post.repository.PostRepository;
import com.mikeyeom.memorablegram.service.UserService;

@Service
public class PostService {
	
	private PostRepository postRepository;
	private UserService userService;
	private LikeService likeService;
	private CommentService commentService;
	
	public PostService(PostRepository postRepository
			, UserService userService
			, LikeService likeService
			, CommentService commentService) {
		this.postRepository = postRepository;
		this.userService = userService;
		this.likeService = likeService;
		this.commentService = commentService;
	}
	
	public boolean addPost(int userId, String contents, MultipartFile file) {
		
		String imagePath = FileManager.saveFile(userId, file);
		
		Post post = Post.builder()
		.userId(userId)
		.contents(contents)
		.imagePath(imagePath)
		.build();
		
		try {
			Post result = postRepository.save(post);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public boolean deletePost(int id, int userId) {
		
		Optional<Post> optionalPost = postRepository.findById(id);
		
		if(optionalPost.isPresent()) {
			Post post = optionalPost.get();
			
			if(post.getUserId() == userId) {
			
			FileManager.removeFile(post.getImagePath());
			commentService.deleteCommentByPostId(id);
			likeService.deleteLikePostId(id);
			postRepository.delete(post);
			
			return true;
			} else {
				return false;
			}
			
		} else {
			return false;
		}
	}
	
	public List<CardDTO> getPostList(int loginUserId) {
		
		List<Post> postList = postRepository.findAllByOrderByIdDesc(); 
		
		List<CardDTO> cardList = new ArrayList<>();
		for (Post post:postList) {
			
			int userId = post.getUserId();
			User user = userService.getUserById(userId);
			
			int likeCount = likeService.getLikeCount(post.getId());
			boolean isLike = likeService.isLike(post.getId(), loginUserId);
			List<CommentDto> commentList = commentService.getCommentList(post.getId());
			
			CardDTO card = CardDTO.builder()
			.postId(post.getId())
			.userId(userId)
			.contents(post.getContents())
			.imagePath(post.getImagePath())
			.loginId(user.getLoginId())
			.likeCount(likeCount)
			.isLike(isLike)
			.commentList(commentList)
			.build();
			
			cardList.add(card);
		}
		
		return cardList;
	}
	
}
