package com.thang.story.service.accessory;

import com.thang.story.model.dto.AccessoryDTO;
import com.thang.story.model.dto.OriginDTO;
import com.thang.story.model.entity.Accessory;
import com.thang.story.model.entity.Origin;
import com.thang.story.model.entity.Product;
import com.thang.story.repository.IAccessoryRepository;
import com.thang.story.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccessoryServiceImpl implements IAccessoryService {
    @Autowired
    private IAccessoryRepository brandRepository;

    @Autowired
    private IProductRepository productRepository;
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

    @Override
    public AccessoryDTO mappingAccessoryToAccessoryDTO(Accessory accessory) {
        AccessoryDTO accessoryDTO = new AccessoryDTO();
        accessoryDTO.setId(accessory.getId());
        accessoryDTO.setName(accessory.getName());
        List<Product> products = this.productRepository.findProductsByAccessoryIdNoPagination(accessory.getId());
        accessoryDTO.setTotalProduct(products.size());
        return accessoryDTO;
    }

    @Override
    public List<AccessoryDTO> getAllAccessory() {
        List<Accessory> accessories = findAll();
        List<AccessoryDTO> accessoryDTOS = new ArrayList<>();
        for (Accessory accessory : accessories) {
            accessoryDTOS.add(mappingAccessoryToAccessoryDTO(accessory));
        }
        return accessoryDTOS;
    }
}
