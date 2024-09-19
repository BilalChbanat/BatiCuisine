package Repositories;

import Interfaces.ComposantInterface;
import Models.Composant;

import java.util.Optional;

public class ComposantRepository implements ComposantInterface {
    @Override
    public Composant generateComposant(int projectId) {
        return null;
    }

    @Override
    public void saveComposant(Composant composant) {

    }

    @Override
    public Optional<Composant> getQuoteByProject(int projectId) {
        return Optional.empty();
    }
}
