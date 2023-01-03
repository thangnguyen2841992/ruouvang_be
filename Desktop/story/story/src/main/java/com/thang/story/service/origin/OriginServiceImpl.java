package com.thang.story.service.origin;

import com.thang.story.model.dto.OriginDTO;
import com.thang.story.model.entity.Origin;
import com.thang.story.model.entity.Product;
import com.thang.story.repository.IOriginRepository;
import com.thang.story.repository.IProductRepository;
import com.thang.story.service.accessory.IAccessoryService;
import com.thang.story.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OriginServiceImpl implements IOriginService {
    @Autowired
    private IOriginRepository categoryRepository;
    @Autowired
    private IAccessoryService brandService;
    @Autowired
    private IProductRepository productRepository;

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

    @Override
    public OriginDTO mappingOriginToOriginDTO(Origin origin) {
        OriginDTO originDTO = new OriginDTO();
        originDTO.setId(originDTO.getId());
        originDTO.setName(origin.getName());
        List<Product> products = this.productRepository.findProductsByOriginIdNoPagination(origin.getId());
        originDTO.setTotalProduct(products.size());
        return originDTO;
    }

    @Override
    public List<OriginDTO> getAllOrigin() {
        List<Origin> origins = findAll();
        List<OriginDTO>originDTOS = new ArrayList<>();
        for (Origin origin : origins) {
            originDTOS.add(mappingOriginToOriginDTO(origin));
        }
        return originDTOS;
    }
}

