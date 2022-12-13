package com.thang.story.service.brand;

import com.thang.story.model.entity.Brand;
import com.thang.story.repository.IBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements IBrandService{
    @Autowired
    private IBrandRepository brandRepository;
    @Override
    public List<Brand> findAll() {
        return this.brandRepository.findAll();
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return this.brandRepository.findById(id);
    }

    @Override
    public Brand save(Brand brand) {
        return this.brandRepository.save(brand);
    }

    @Override
    public void delete(Long id) {
        this.brandRepository.deleteById(id);
    }

    @Override
    public List<Brand> getBrandsByCategoryId(Long categoryId) {
        return this.brandRepository.getBrandsByCategoryId(categoryId);
    }
}
