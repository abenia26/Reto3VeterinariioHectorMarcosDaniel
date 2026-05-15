package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;
import modelo.Veterinario;
import util.ConexionBD;

public class VeterinarioDAO implements GenericDAO<Veterinario> {

	@Override
	public boolean insertar(Veterinario veterinario) {
		String sql ="INSERT INTO veterinarios (id_persona,num_colegiado) VALUES (?, ?)"; 


		try (Connection conn = ConexionBD.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql,               Statement.RETURN_GENERATED_KEYS)) {


		
			pstmt.setInt(1, veterinario.getId_persona());
			pstmt.setString(2, veterinario.getNum_colegiado());
		


			int filas = pstmt.executeUpdate();


			if (filas > 0) {
				try (ResultSet rs = pstmt.getGeneratedKeys()) {
					if (rs.next()) {
					veterinario.setId_veterinario(rs.getInt(1)); // asigna el ID
						return true;
					}
				}
			}
			return false;


		} catch (SQLException e) {
			System.err.println("Error SQL al insertar '" + veterinario.getId_veterinario() + "': " + e.getMessage());
			return false;
		}
	}

	@Override
	public List<Veterinario> obtenerTodos() {
		List<Veterinario> veterinarios = new ArrayList<>();
	    String sql = "SELECT id_cliente,id_persona,telefono FROM clientes ORDER BY id_cliente";


			try (Connection conn = ConexionBD.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery()) {


				while (rs.next()) {
					veterinarios.add(mapearFila(rs));
				}


			} catch (SQLException e) {
				System.err.println("Error SQL al obtener todos los clientes: " + e.getMessage());
			}
			return veterinarios;
	}

	@Override
	public Veterinario obtenerPorId(int id) {

		String sql = "select t.* from tratamientos t inner join historial h on  t.id_tratamiento=h.id_tratamiento  inner join veterinarios v on h.id_veterinario=v.id_veterinario where v.id_veterinario=?";



		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, "id_veterinario");

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {

					String titulo = rs.getString("titulo");

					System.out.println(titulo + " ");



				}

			}

		} catch (SQLException e) {
			System.err.println("Error SQL al buscar ID " + id + ": " + e.getMessage());
		}
		return null; // no encontrado
		
	}

	@Override
	public boolean actualizar(Veterinario veterinario) {
		String sql = "UPDATE veterinarios SET id_persona=?,num_colegiado=? WHERE id=?";


		try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {


			pstmt.setInt(1,veterinario.getId_persona());
			pstmt.setString(2, veterinario.getNum_colegiado());
			pstmt.setInt(3, veterinario.getId_veterinario());;
		


			int filas = pstmt.executeUpdate();
			return filas > 0; // false si el ID no existía en la BD


		} catch (SQLException e) {
			System.err.println("Error SQL al actualizar ID " + veterinario.getId_veterinario() + ": " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean eliminar(int id) {
		String sql = "DELETE FROM veterinarios WHERE id=?";
 

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {


			pstmt.setInt(1, id);
			int filas = pstmt.executeUpdate();
			return filas > 0; // false si el ID no existía


		} catch (SQLException e) {
			System.err.println("Error SQL al eliminar ID " + id + ": " + e.getMessage());
			return false;
		}
	}

	private Veterinario mapearFila(ResultSet rs) throws SQLException {
		
		Veterinario a = new Veterinario();
		a.setId_veterinario(rs.getInt("id_veterinario"));
		a.setId_persona(rs.getInt("id_persona"));
		a.setNum_colegiado(rs.getString("num_colegiado"));
		return a;
	}
}
