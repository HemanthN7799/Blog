package com.ssd.blog.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ssd.blog.entity.iplMatches;
import com.ssd.blog.repository.IplRepository;

@Service
public class IplServiceImpl {
	
	@Autowired
	public IplRepository iplRepository;
	
	
	public List<iplMatches> getAllIplMatches(int pageNo,int size,String sortDirc,String sortBy){
		
		Sort sort=sortDirc.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		Pageable pages=PageRequest.of(pageNo, size,sort);
		Page<iplMatches> mathes= iplRepository.findAll(pages); 
		 return mathes.getContent();
	}

}
