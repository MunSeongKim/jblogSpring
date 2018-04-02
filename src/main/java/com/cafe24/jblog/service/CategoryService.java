package com.cafe24.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.CategoryRepository;
import com.cafe24.jblog.vo.CategoryVO;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryVO> getAllCategories( String id ) {
	return categoryRepository.readAll( id );
    }

    public CategoryVO addition( CategoryVO vo ) {
	CategoryVO result = categoryRepository.create( vo );
	result = categoryRepository.read( vo );
	return result;
    }

    public boolean remove( CategoryVO vo ) {
	return categoryRepository.delete( vo );
    }

    public boolean updatePostCount( Long no ) {
	return categoryRepository.update( no );
    }
}
