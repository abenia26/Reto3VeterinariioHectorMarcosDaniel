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
import modelo.Mascota;
import modelo.Tratamiento;
import util.ConexionBD;

public class TratamientoDAO implements GenericDAO<Tratamiento> {

	@Override
	public boolean insertar(Tratamiento objeto) {
		String sql = "INSERT INTO tratamientos (id_tratamiento, nombre, precio) VALUES (?, ?, ?)";

		try (Connection conn = ConexionBD.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			pstmt.setInt(1, objeto.getId_tratamiento());
			pstmt.setString(2, objeto.getNombre());
			pstmt.setDouble(3, objeto.getPrecio());

			int filas = pstmt.executeUpdate();

			if (filas > 0) {
				try (ResultSet rs = pstmt.getGeneratedKeys()) {
					if (rs.next()) {
						objeto.setId_tratamiento(1);// asigna el ID
						return true;
					}
				}
			}
			return false;

		} catch (SQLException e) {
			System.err.println("Error SQL al insertar '" + objeto.getId_tratamiento() + "': " + e.getMessage());
			return false;
		}
	}

	@Override
	public List<Tratamiento> obtenerTodos() {
		List<Tratamiento> tratamiento = new ArrayList<>();
		String sql = "SELECT id_tratamiento, nombre, precio FROM mascotas ORDER BY id_tratamiento";

		try (Connection conn = ConexionBD.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				tratamiento.add(mapearFila(rs));
			}

		} catch (SQLException e) {
			System.err.println("Error SQL al obtener todos los clientes: " + e.getMessage());
		}
		return tratamiento;
	}

	@Override
	public Tratamiento obtenerPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean actualizar(Tratamiento objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	private Tratamiento mapearFila(ResultSet rs) throws SQLException {
		Tratamiento h = new Tratamiento();
		h.setId_tratamiento(rs.getInt("id_tratamiento"));
		h.setNombre(rs.getString("nombre"));
		h.setPrecio(rs.getDouble("precio"));
		return h;
	}
}