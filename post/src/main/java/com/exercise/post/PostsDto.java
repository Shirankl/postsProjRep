package com.exercise.post;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsDto extends CrudRepository<PostEntity, Long>{

}
