package Interfaces;

import Models.MainDoeuvre;

import java.util.List;
import java.util.Optional;

public interface MainDoeuvreInterface {

    void addLabor(MainDoeuvre mainDoeuvre);
    List<MainDoeuvre> getAllLabor();
    Optional<MainDoeuvre> getLaborByType(String laborType);
}
