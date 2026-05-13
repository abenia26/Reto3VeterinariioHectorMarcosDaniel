package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import modelo.Tratamiento;
import util.ConexionBD;

public class TratamientoDAO implements GenericDAO<Tratamiento> {

	@Override
	public boolean insertar(Tratamiento objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Tratamiento> obtenerTodos() {
		// TODO Auto-generated method stub
		return null;
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

	public Tratamiento obtenerPorIdVeterinario(int id) { 

		String sql = "select t.* from tratamientos t inner join historial h on  t.id_tratamiento=h.id_tratamiento  inner join veterinarios v on h.id_veterinario=v.id_veterinario where v.id_veterinario=?";

		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				System.out.println(rs.getString(1) + ": " + rs.getInt(2));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
