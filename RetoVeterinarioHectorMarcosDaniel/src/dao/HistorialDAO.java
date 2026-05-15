package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelo.Historial;
import util.ConexionBD;

public class HistorialDAO implements GenericDAO<Historial> {

	@Override
	public boolean insertar(Historial objeto) {
		String sql = "INSERT INTO historial (id_mascota, id_tratamiento, id_veterinario, fecha) VALUES (?, ?, ?, ?)";

		try (Connection conn = ConexionBD.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			pstmt.setInt(1, objeto.getId_mascota());
			pstmt.setInt(2, objeto.getId_tratamiento());
			pstmt.setInt(3, objeto.getId_veterinario());
			pstmt.setObject(4, LocalDate.class);

			int filas = pstmt.executeUpdate();

			if (filas > 0) {
				try (ResultSet rs = pstmt.getGeneratedKeys()) {
					if (rs.next()) {
						objeto.setId_historial(rs.getInt(1));// asigna el ID
						return true;
					}
				}
			}
			return false;

		} catch (SQLException e) {
			System.err.println("Error SQL al insertar '" + objeto.getId_historial() + "': " + e.getMessage());
			return false;
		}
	}

	@Override
	public List<Historial> obtenerTodos() {
		List<Historial> historial = new ArrayList<>();
		String sql = "SELECT id_historial, id_mascota, id_tratamiento, id_veterinario, fecha FROM historial ORDER BY id_historial";

		try (Connection conn = ConexionBD.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				historial.add(mapearFila(rs));
			}

		} catch (SQLException e) {
			System.err.println("Error SQL al obtener todos los clientes: " + e.getMessage());
		}
		return historial;
	}

	@Override
	public Historial obtenerPorId(int id) {
		String sql = "SELECT id_historial, id_mascota, id_tratamiento, id_veterinario, fecha FROM clientes WHERE id_historial = ?";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, id);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return mapearFila(rs);
				}
			}

		} catch (SQLException e) {
			System.err.println("Error SQL al buscar ID " + id + ": " + e.getMessage());
		}
		return null; //
	}

	@Override
	public boolean actualizar(Historial objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(int id) {
		String sql = "DELETE FROM clientes WHERE id_historial=?";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, id);
			int filas = pstmt.executeUpdate();
			return filas > 0; // false si el ID no existía

		} catch (SQLException e) {
			System.err.println("Error SQL al eliminar ID " + id + ": " + e.getMessage());
			return false;
		}
	}

	private Historial mapearFila(ResultSet rs) throws SQLException {
		Historial h = new Historial();
		h.setId_tratamiento(rs.getInt("id_tratamiento"));
		h.setId_mascota(rs.getInt("id_mascota"));
		h.setFecha(rs.getObject("fecha", LocalDate.class));
		h.setId_veterinario(rs.getInt("id_veterinario"));
		return h;
	}
}
