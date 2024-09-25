package Repositories;

import Interfaces.DevisInterface;
import Models.Devis;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DevisRepository implements DevisInterface {
    private final Connection connection;

    public DevisRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public double calculateTax(double baseAmount, double taxRate) {
        return baseAmount * (taxRate / 100);
    }

    @Override
    public void add(Devis devis) {
        String sql = "INSERT INTO devis (montantestime, dateemission, accepte, datevalidate, project_id) VALUES (?, ?, ?, ?, ?) RETURNING id";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, devis.getMontantEstime());
            pstmt.setDate(2, java.sql.Date.valueOf(devis.getDateEmission()));
            pstmt.setBoolean(3, devis.isAccepte());
            pstmt.setDate(4, devis.getDateValide() != null ? java.sql.Date.valueOf(devis.getDateValide()) : null);
            pstmt.setInt(5, devis.getProjectId());

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                devis.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            // Consider logging instead of printStackTrace in production
            e.printStackTrace();
        }
    }

    @Override
    public List<Devis> findAll() {
        List<Devis> devisList = new ArrayList<>();
        String sql = "SELECT * FROM devis";

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                LocalDate dateValide = rs.getDate("datevalidate") != null ? rs.getDate("datevalidate").toLocalDate() : null;
                Devis devis = new Devis(
                        rs.getInt("id"),
                        rs.getDouble("montantestime"),
                        rs.getDate("dateemission").toLocalDate(),
                        rs.getBoolean("accepte"),
                        dateValide,
                        rs.getInt("project_id")
                );
                devisList.add(devis);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider logging instead
        }
        return devisList;
    }

    @Override
    public Devis findById(int id) {
        Devis devis = null;
        String sql = "SELECT * FROM devis WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                LocalDate dateValide = rs.getDate("datevalidate") != null ? rs.getDate("datevalidate").toLocalDate() : null;
                devis = new Devis(
                        rs.getInt("id"),
                        rs.getDouble("montantestime"),
                        rs.getDate("dateemission").toLocalDate(),
                        rs.getBoolean("accepte"),
                        dateValide,
                        rs.getInt("project_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider logging instead
        }
        return devis;
    }

    @Override
    public void update(Devis devis) {
        String sql = "UPDATE devis SET montantestime = ?, dateemission = ?, accepte = ?, datevalidate = ?, project_id = ? WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, devis.getMontantEstime());
            pstmt.setDate(2, java.sql.Date.valueOf(devis.getDateEmission()));
            pstmt.setBoolean(3, devis.isAccepte());
            pstmt.setDate(4, devis.getDateValide() != null ? java.sql.Date.valueOf(devis.getDateValide()) : null);
            pstmt.setInt(5, devis.getProjectId());
            pstmt.setInt(6, devis.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Consider logging instead
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM devis WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Consider logging instead
        }
    }
}
