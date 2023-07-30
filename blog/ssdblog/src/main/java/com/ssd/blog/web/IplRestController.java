package com.ssd.blog.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssd.blog.entity.iplMatches;
import com.ssd.blog.serviceimpl.IplServiceImpl;

@RestController
@RequestMapping("/api/v1")
public class IplRestController {

	@Autowired
	public IplServiceImpl service;
	
	@GetMapping("/matches")
	public ResponseEntity<List<iplMatches>> getAllMatches(
			@RequestParam(value = "pageNo", defaultValue = "0",required = false )int pageNo,
			@RequestParam(value = "size", defaultValue = "10",required = false)int size,
			@RequestParam(value = "sortBy", defaultValue = "id",required = false)String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc",required = false)String sortDir
			){
		
		List<iplMatches> matchesList =service.getAllIplMatches(pageNo,size,sortDir,sortBy);
		return new ResponseEntity<List<iplMatches>>(matchesList,HttpStatus.OK);
	}
}
