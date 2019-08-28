package com.exercise.post;

import java.util.Comparator;

import org.springframework.stereotype.Component;

@Component
public class TopPostsComperator implements Comparator<PostEntity> {

	@Override
	public int compare(PostEntity post1, PostEntity post2) {
		long date1 = post1.getDate().getTime();
		long date2 = post2.getDate().getTime();
		
		long score1 = date1*post1.getPoints();
		long score2 = date2*post2.getPoints();
		
		if (score1 == score2)
			return 0;
		
		if (score1 > score2)
			return -1;
		
		return 1;
	}

}
