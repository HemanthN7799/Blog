package com.ssd.blog.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ssd.blog.Exceptions.TitleAlreadyExistedException;
import com.ssd.blog.entity.Post;
import com.ssd.blog.payload.PaginationAndSortResponse;
import com.ssd.blog.payload.PostDto;
import com.ssd.blog.repository.PostRepository;

@Service
public class PostServiceImpl {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public PostDto sendPost(PostDto dto) {
		
		Post post = this.modelMapper.map(dto, Post.class);
		try {
		post = postRepository.save(post);
		
		}
		catch (Exception e) {
			e.getMessage();
			throw new TitleAlreadyExistedException(dto.getTitle(),"Already "+dto.getTitle()+" existed");
		}
		dto = this.modelMapper.map(post, PostDto.class);
		return dto;
	}
	
	public PaginationAndSortResponse getAllPosts(int pageNo,int size,String sortDirc,String sortBy){
		  System.out.println("fetched data from db");
		Sort sort=sortDirc.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		Pageable pages=PageRequest.of(pageNo, size,sort);
		
		Page<Post> posts= postRepository.findAll(pages); 

	
		List<PostDto> dtoList=posts.stream().map((post)->
			 modelMapper.map(post, PostDto.class)
		).collect(Collectors.toList());
		
		PaginationAndSortResponse paginationAndSortResponse =new PaginationAndSortResponse();
		paginationAndSortResponse.setPostsList(dtoList);
		paginationAndSortResponse.setTotalElements(posts.getTotalElements());
		paginationAndSortResponse.setLast(posts.isLast());
		paginationAndSortResponse.setPageNo(posts.getNumber());
		paginationAndSortResponse.setPageSize(posts.getSize());
		paginationAndSortResponse.setTotalPages(posts.getTotalPages());
	
		return paginationAndSortResponse;
	}
	public PostDto getPostById(long id) {
		System.out.println("fetched data from db");
		Post post=postRepository.findById(id).get();
		PostDto dto=this.modelMapper.map(post, PostDto.class);
		
		return dto;
	}
	public void deletePostbyId(long id) {
		postRepository.deleteById(id);
	}

	public PostDto UpdatePostById(PostDto dto) {
		
		Post oldPost=postRepository.findById(dto.getId()).get();
		
		Post newPost=this.modelMapper.map(dto, Post.class);
		if(newPost.getTitle()!=null) {
			oldPost.setTitle(newPost.getTitle());
		}if(newPost.getDescription()!=null) {
			oldPost.setDescription(newPost.getDescription());
		}if(newPost.getContent()!=null) {
			oldPost.setContent(newPost.getContent());
		}
		
		PostDto modifedDto =this.modelMapper.map(postRepository.save(oldPost), PostDto.class);
		
		return modifedDto;
		
	}
	
}
