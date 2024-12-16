package com.mikeyeom.memorablegram.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mikeyeom.memorablegram.post.domain.Post;
import com.mikeyeom.memorablegram.post.service.PostService;

@RequestMapping("/post")
@Controller
public class PostController {
	
	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping("/timeline-view")
	public String timeline(Model model) {
		
		List<Post> postList = postService.getPostList();
		
		model.addAttribute("postList", postList);
		
		return "post/timeline";
	}
	
	@GetMapping("/upload-view")
	public String upload() {
		return "post/upload";
	}
}
