package Interfaces;

import Models.Material;

import java.util.List;
import java.util.Optional;

public interface MaterialInterface {
    void addMaterial(Material material);
    List<Material> getAllMaterials();
    Optional<Material> getMaterialByName(String name);
}
