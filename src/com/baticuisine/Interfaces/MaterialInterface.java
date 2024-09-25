package com.baticuisine.Interfaces;

import com.baticuisine.Models.Material;

import java.util.List;
import java.util.Optional;

public interface MaterialInterface {
    void addMaterial(Material material);
    List<Material> getAllMaterials();
    Optional<Material> getMaterialByName(String name);
    List<Material> findMaterialProject(int choice);

}
