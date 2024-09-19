package Repositories;

import Interfaces.MainDoeuvreInterface;
import Models.MainDoeuvre;

import java.util.List;
import java.util.Optional;

public class MainDoeuvreRepository  implements MainDoeuvreInterface {
    @Override
    public void addLabor(MainDoeuvre mainDoeuvre) {

    }

    @Override
    public List<MainDoeuvre> getAllLabor() {
        return List.of();
    }

    @Override
    public Optional<MainDoeuvre> getLaborByType(String laborType) {
        return Optional.empty();
    }
}
