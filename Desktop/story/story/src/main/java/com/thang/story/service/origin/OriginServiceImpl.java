package com.thang.story.service.origin;

import com.thang.story.model.entity.Origin;
import com.thang.story.repository.IOriginRepository;
import com.thang.story.service.accessory.IAccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OriginServiceImpl implements IOriginService {
    @Autowired
    private IOriginRepository categoryRepository;
    @Autowired
    private IAccessoryService brandService;
    @Override
    public List<Origin> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Optional<Origin> findById(Long id) {
        return this.categoryRepository.findById(id);
    }

    @Override
    public Origin save(Origin origin) {
        return this.categoryRepository.save(origin);
    }

    @Override
    public void delete(Long id) {
        this.categoryRepository.deleteById(id);
    }

}
