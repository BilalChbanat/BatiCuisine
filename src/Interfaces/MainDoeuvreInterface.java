package Interfaces;

import Models.MainDoeuvre;
import java.util.List;

public interface MainDoeuvreInterface {
    public void add(MainDoeuvre mainDoeuvre, int projectId);
    MainDoeuvre findById(int id);
    List<MainDoeuvre> findByProjectId(int projectId);
}
