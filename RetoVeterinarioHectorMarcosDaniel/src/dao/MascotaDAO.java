package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;
import modelo.Historial;
import modelo.Mascota;
import util.ConexionBD;

public class MascotaDAO implements GenericDAO<Mascota> {

	@Override
	public boolean insertar(Mascota objeto) {
		String sql = "INSERT INTO mascotas (id_cliente, nombre, especie, fecha_nacimiento, peso) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = ConexionBD.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			pstmt.setInt(1, objeto.getId_cliente());
			pstmt.setString(2, objeto.getNombre());
			pstmt.setString(3, objeto.getEspecie());
			pstmt.setObject(4, LocalDate.class);
			pstmt.setDouble(5, objeto.getPeso());

			int filas = pstmt.executeUpdate();

			if (filas > 0) {
				try (ResultSet rs = pstmt.getGeneratedKeys()) {
					if (rs.next()) {
						objeto.setId_mascota(1);// asigna el ID
						return true;
					}
				}
			}
			return false;

		} catch (SQLException e) {
			System.err.println("Error SQL al insertar '" + objeto.getId_mascota() + "': " + e.getMessage());
			return false;
		}
	}

	@Override
	public List<Mascota> obtenerTodos() {
		List<Mascota> mascota = new ArrayList<>();
		String sql = "SELECT id_mascota, id_cliente, nombre, especie, fecha_nacimiento, peso FROM mascotas ORDER BY id_mascota";

		try (Connection conn = ConexionBD.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				mascota.add(mapearFila(rs));
			}

		} catch (SQLException e) {
			System.err.println("Error SQL al obtener todos los clientes: " + e.getMessage());
		}
		return mascota;
	}

	@Override
	public Mascota obtenerPorId(int id) {
		String sql = "SELECT id_mascota, id_cliente, nombre, especie, fecha_nacimiento, peso FROM mascotas WHERE id_mascota = ?";

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
	public boolean actualizar(Mascota objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	private Mascota mapearFila(ResultSet rs) throws SQLException {
		Mascota a = new Mascota();
		a.setId_mascota(rs.getInt("id_mascota"));
		a.setEspecie(rs.getString("id_especie"));
		a.setFecha_nacimiento(rs.getObject("fecha_nacimiento", LocalDate.class));
		a.setNombre(rs.getString("nombre"));
		a.setPeso(rs.getDouble("peso"));
		return a;
	}

}
