package com.exercise.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostsController {

	@Autowired
	PostsService service;
	
	@PostMapping(value = "post/create", consumes = "application/json", produces = "application/json")
	public ResponseEntity<PostEntity> createPost(@RequestBody PostEntity newPost) {
		PostEntity res = null;
		
		try {
			res = service.cretaePost(newPost.getUserId(), newPost.getContent());
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<PostEntity>(res, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<PostEntity>(res, HttpStatus.OK);
	}
	
	@PutMapping(value = "post/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<PostEntity> updatePost(@RequestBody PostEntity updatedPost) {
		PostEntity res = null;
		
		try {
			res = service.updatePost(updatedPost, EUpdateType.CONTENT);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<PostEntity>(res, HttpStatus.BAD_REQUEST);
		}	
		
		return new ResponseEntity<PostEntity>(res, HttpStatus.OK);
	}
	
	@PutMapping(value = "post/upvote", consumes = "application/json", produces = "application/json")
	public ResponseEntity<PostEntity> upvotePost(@RequestBody PostEntity updatedPost) {
	PostEntity res = null;
		
		try {
			res = service.updatePost(updatedPost, EUpdateType.UPVOTE);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<PostEntity>(res, HttpStatus.BAD_REQUEST);
		}	
		
		return new ResponseEntity<PostEntity>(res, HttpStatus.OK);
	}
	
	@PutMapping(value = "post/downvote", consumes = "application/json", produces = "application/json")
	public ResponseEntity<PostEntity> downvotePost(@RequestBody PostEntity updatedPost) {
	PostEntity res = null;
		
		try {
			res = service.updatePost(updatedPost, EUpdateType.DOWNVOTE);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<PostEntity>(res, HttpStatus.BAD_REQUEST);
		}	
		
		return new ResponseEntity<PostEntity>(res, HttpStatus.OK);
	}
	
	@GetMapping(value="post/topposts", produces = "application/json")
	public List<PostEntity> topPosts() {
		return service.getTopPosts();	
	}
}
