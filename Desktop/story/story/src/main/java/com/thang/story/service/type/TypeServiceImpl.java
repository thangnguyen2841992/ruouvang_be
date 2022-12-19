package com.thang.story.service.type;

import com.thang.story.model.entity.Type;
import com.thang.story.repository.ITypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeServiceImpl implements ITypeService{
    @Autowired
    private ITypeRepository typeRepository;

    @Override
    public List<Type> findAll() {
        return this.typeRepository.findAll();
    }

    @Override
    public Optional<Type> findById(Long id) {
        return this.typeRepository.findById(id);
    }

    @Override
    public Type save(Type type) {
        return this.typeRepository.save(type);
    }

    @Override
    public void delete(Long id) {
        this.typeRepository.deleteById(id);
    }
}
