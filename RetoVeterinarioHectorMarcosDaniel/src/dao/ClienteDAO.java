package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import modelo.Cliente;
import util.ConexionBD;

public class ClienteDAO implements GenericDAO<Cliente> {

	@Override
	public boolean insertar(Cliente cliente) {
		String sql ="INSERT INTO clientes (id_persona,telefono) VALUES (?, ?)"; 


		try (Connection conn = ConexionBD.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql,               Statement.RETURN_GENERATED_KEYS)) {


		
			pstmt.setInt(1, cliente.getId_persona());
			pstmt.setString(2, cliente.getTelefono());
		


			int filas = pstmt.executeUpdate();


			if (filas > 0) {
				try (ResultSet rs = pstmt.getGeneratedKeys()) {
					if (rs.next()) {
					cliente.setId_cliente(rs.getInt(1)); // asigna el ID
						return true;
					}
				}
			}
			return false;


		} catch (SQLException e) {
			System.err.println("Error SQL al insertar '" + cliente.getId_cliente() + "': " + e.getMessage());
			return false;
		}
	}

	@Override
	public List<Cliente> obtenerTodos() {
		List<Cliente> clientes = new ArrayList<>();
	    String sql = "SELECT id_cliente,id_persona,telefono FROM clientes ORDER BY id_cliente";


			try (Connection conn = ConexionBD.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery()) {


				while (rs.next()) {
					clientes.add(mapearFila(rs));
				}


			} catch (SQLException e) {
				System.err.println("Error SQL al obtener todos los clientes: " + e.getMessage());
			}
			return clientes;
	}

	@Override
	public Cliente obtenerPorId(int id) {
		 String sql = "SELECT id_cliente,id_persona,telefono FROM clientes WHERE id = ?";


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
	public boolean actualizar(Cliente cliente) {
		String sql = "UPDATE clientes SET id_persona=?, telefono=? WHERE id=?";


		try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {


			pstmt.setInt(1, cliente.getId_persona());
			pstmt.setString(2, cliente.getTelefono());
			


			int filas = pstmt.executeUpdate();
			return filas > 0; // false si el ID no existía en la BD


		} catch (SQLException e) {
			System.err.println("Error SQL al actualizar ID " + cliente.getId_cliente() + ": " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean eliminar(int id) {
		String sql = "DELETE FROM clientes WHERE id=?";


		try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {


			pstmt.setInt(1, id);
			int filas = pstmt.executeUpdate();
			return filas > 0; // false si el ID no existía


		} catch (SQLException e) {
			System.err.println("Error SQL al eliminar ID " + id + ": " + e.getMessage());
			return false;
		}
	}

	private Cliente mapearFila(ResultSet rs) throws SQLException {
		Cliente a = new Cliente();
		a.setId_cliente(rs.getInt("id_cliente"));
		a.setId_persona(rs.getInt("id_persona"));
		a.setTelefono(rs.getString("telefono"));
		return a;
	}


}
