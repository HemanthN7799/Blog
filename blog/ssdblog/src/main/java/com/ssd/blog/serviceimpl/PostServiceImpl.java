package com.ssd.blog.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssd.blog.Exceptions.TitleAlreadyExistedException;
import com.ssd.blog.entity.Post;
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
	
	public List<PostDto> getAllPosts(){
		List<Post> postlist =postRepository.findAll();
		List<PostDto> dtoList=postlist.stream().map((post)->
			 modelMapper.map(post, PostDto.class)
		).collect(Collectors.toList());
		return dtoList;
	}
	public PostDto getPostById(long id) {
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
