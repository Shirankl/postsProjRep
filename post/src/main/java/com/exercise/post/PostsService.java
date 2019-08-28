package com.exercise.post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostsService {

	@Autowired
	PostsDto postsDto;
	
	@Autowired
	TopPostsComperator comparator;
	
	List<PostEntity> topPostsCache = new ArrayList<PostEntity>();
	boolean isTopPostsCacheLoaded = false;
	
	public PostEntity cretaePost(Long userId, String content) throws Exception {
		loadCache();		
		PostEntity post = new PostEntity();
		post.setDate(new Date());
		post.setUserId(userId);
		post.setContent(content);
		post.setPoints(0);
		PostEntity res;
		
		try {
			res = postsDto.save(post);
		} catch (Exception e) {
			throw new Exception("Invalid input, post wasn't saved. Error: " + e.getMessage());
		}
		
		topPostsCache.add(res);
		topPostsCache.sort(comparator);
		return res;
	}

	public PostEntity updatePost(PostEntity updatedPost, EUpdateType updateType) throws Exception {
		loadCache();		
		Optional<PostEntity> postOpt = postsDto.findById(updatedPost.getId());
		
		if (!postOpt.isPresent()) 
			throw new Exception("Post doesn't exist");
		
		PostEntity post = postOpt.get();
		
		switch (updateType) {
		case UPVOTE:
			post.setPoints(post.getPoints()+1);
			topPostsCache.sort(comparator);
			break;

		case DOWNVOTE:
			post.setPoints(post.getPoints()-1);
			topPostsCache.sort(comparator);
			break;
			
		case CONTENT:
			post.setContent(updatedPost.getContent());
			break;
			
		default:
			break;
		}
		
		try {
			return postsDto.save(post);
		} catch (Exception e) {
			throw new Exception("Invalid input, post wasn't saved. Error: " + e.getMessage());
		}
	}

	public List<PostEntity> getTopPosts() {
		loadCache();		
		return topPostsCache;
	}

	private void loadCache() {
		if (!isTopPostsCacheLoaded) {
			Iterable<PostEntity> allPostsIter = postsDto.findAll();
			allPostsIter.forEach(topPostsCache::add);
			topPostsCache.sort(comparator);
			isTopPostsCacheLoaded = true;
		}
	}
}
