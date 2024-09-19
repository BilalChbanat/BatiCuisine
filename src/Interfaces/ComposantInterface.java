package Interfaces;

import Models.Composant;

import java.util.Optional;

public interface ComposantInterface {

    Composant generateComposant(int projectId);
    void saveComposant(Composant composant);
    Optional<Composant> getQuoteByProject(int projectId);
}
