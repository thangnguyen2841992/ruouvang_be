package com.thang.story.service.origin;

import com.thang.story.model.dto.OriginDTO;
import com.thang.story.model.entity.Origin;
import com.thang.story.service.IGeneralService;

import java.util.List;

public interface IOriginService extends IGeneralService<Origin> {
    OriginDTO mappingOriginToOriginDTO(Origin origin);

    List<OriginDTO> getAllOrigin();
}
