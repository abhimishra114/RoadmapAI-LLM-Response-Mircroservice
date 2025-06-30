package com.roadmap.llm_response.service;

import com.roadmap.llm_response.dto.ResourceDTO;
import com.roadmap.llm_response.dto.RoadmapResponse;
import com.roadmap.llm_response.dto.WeekDTO;
import com.roadmap.llm_response.model.Resources;
import com.roadmap.llm_response.repository.ResourcesRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResourcesService {

    private final ResourcesRepo resourcesRepo;
    public ResourcesService(ResourcesRepo resourcesRepo) {
        this.resourcesRepo = resourcesRepo;
    }

    public List<Resources> createResourcesFromResponse(long weekId, List<WeekDTO> weeks) {
        List<Resources> resourcesList = new ArrayList<>();

        for (WeekDTO weekDTO : weeks) {
            for (ResourceDTO resourceDTO : weekDTO.getResources()) {
                Resources resource = new Resources();
                resource.setWeekId(weekId);
                resource.setTitle(resourceDTO.getTitle());
                resource.setUrl(resourceDTO.getUrl());
                resource.setType(Resources.Type.fromString(resourceDTO.getType()));

                resourcesList.add(resource);
            }
        }

        return resourcesList;
    }

    public List<Resources> createResourcesFromResponse(Long weekId, List<ResourceDTO> resourceDTOs) {
        List<Resources> resources = new ArrayList<>();
        for (ResourceDTO dto : resourceDTOs) {
            Resources res = new Resources();
            res.setWeekId(weekId);
            res.setTitle(dto.getTitle());
            res.setUrl(dto.getUrl());
            res.setType(Resources.Type.fromString(dto.getType()));
            resources.add(res);
        }
        return resources;
    }


    public List<Resources> saveResources(List<Resources> resourcesList) {
        List<Resources> savedResources = resourcesRepo.saveAll(resourcesList);
        return savedResources;
    }

    public List<Resources> getResourcesByWeekId(long weekId) {
        return resourcesRepo.findByWeekId(weekId);
    }
}
