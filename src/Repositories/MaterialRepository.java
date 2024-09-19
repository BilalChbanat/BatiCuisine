package Repositories;

import Interfaces.MaterialInterface;
import Models.Material;

import java.util.List;
import java.util.Optional;

public class MaterialRepository implements MaterialInterface {
    @Override
    public void addMaterial(Material material) {

    }

    @Override
    public List<Material> getAllMaterials() {
        return List.of();
    }

    @Override
    public Optional<Material> getMaterialByName(String name) {
        return Optional.empty();
    }
}
