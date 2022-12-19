package com.thang.story.service.accessory;

import com.thang.story.model.entity.Accessory;
import com.thang.story.repository.IAccessoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccessoryServiceImpl implements IAccessoryService {
    @Autowired
    private IAccessoryRepository brandRepository;
    @Override
    public List<Accessory> findAll() {
        return this.brandRepository.findAll();
    }

    @Override
    public Optional<Accessory> findById(Long id) {
        return this.brandRepository.findById(id);
    }

    @Override
    public Accessory save(Accessory accessory) {
        return this.brandRepository.save(accessory);
    }

    @Override
    public void delete(Long id) {
        this.brandRepository.deleteById(id);
    }

}
