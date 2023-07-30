package com.ssd.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssd.blog.payload.PaginationAndSortResponse;
import com.ssd.blog.payload.PostDto;
import com.ssd.blog.serviceimpl.ErrorMsgResultsServiceimpl;
import com.ssd.blog.serviceimpl.PostServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class PostRestController {

	@Autowired
	public PostServiceImpl postServiceImpl;

	@Autowired
	public ErrorMsgResultsServiceimpl errorService;


	@GetMapping("/home")
	public ResponseEntity<String> getHomePage() {
		return new ResponseEntity<String>("welcome to SSD blog Writer", HttpStatus.OK);
	}

	@PostMapping("/posts")
	public ResponseEntity<?> sendPost(@Valid @RequestBody PostDto postDto, BindingResult errorResult) {
		if (errorResult.hasErrors()) {
			return errorService.errorMsgResultMap(errorResult);
		} else {
			return new ResponseEntity<PostDto>(postServiceImpl.sendPost(postDto), HttpStatus.CREATED);
		}
	}
	@GetMapping("/posts/{id}")
	@Cacheable(key = "#id",value = "post")
	public PostDto getPostById(@PathVariable long id) {
		PostDto dto = postServiceImpl.getPostById(id);
		return dto;
	}

	@GetMapping("/posts")
	public PaginationAndSortResponse getAllPosts(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "size", defaultValue = "10", required = false) int size,
			@RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
		PaginationAndSortResponse postList = postServiceImpl.getAllPosts(pageNo, size, sortDir, sortBy);
		return postList;
	}

	@DeleteMapping("/posts/{id}")
	@CacheEvict(key = "#id",value = "post",allEntries = false)
	public String deletePostById(@PathVariable long id) {
		postServiceImpl.deletePostbyId(id);
		return "Post is Deleted";
	}

	@PutMapping("/posts/{id}")
	 @CachePut(value = "post",key = "#id")
	public PostDto updatePostBuId(@PathVariable long id, @RequestBody PostDto dto) {
		dto.setId(id);
		return postServiceImpl.UpdatePostById(dto);
	}

}
