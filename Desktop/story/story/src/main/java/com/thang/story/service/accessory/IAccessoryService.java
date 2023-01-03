package com.thang.story.service.accessory;

import com.thang.story.model.dto.AccessoryDTO;
import com.thang.story.model.dto.OriginDTO;
import com.thang.story.model.entity.Accessory;
import com.thang.story.model.entity.Origin;
import com.thang.story.service.IGeneralService;

import java.util.List;

public interface IAccessoryService extends IGeneralService<Accessory> {
    AccessoryDTO mappingAccessoryToAccessoryDTO(Accessory accessory);

    List<AccessoryDTO> getAllAccessory();
}
