package com.mikeyeom.memorablegram.post.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mikeyeom.memorablegram.common.FileManager;
import com.mikeyeom.memorablegram.post.domain.Post;
import com.mikeyeom.memorablegram.post.repository.PostRepository;

@Service
public class PostService {
	
	private PostRepository postRepository;
	
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
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
	
	public List<Post> getPostList() {
		return postRepository.findAllByOrderByIdDesc();
	}
	
}
