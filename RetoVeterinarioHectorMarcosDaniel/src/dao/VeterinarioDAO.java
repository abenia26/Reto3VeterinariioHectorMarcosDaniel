package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import modelo.Cliente;
import modelo.Veterinario;

public class VeterinarioDAO implements GenericDAO<Veterinario> {

	@Override
	public boolean insertar(Veterinario objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Veterinario> obtenerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Veterinario obtenerPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean actualizar(Veterinario objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	private Veterinario mapearFila(ResultSet rs) throws SQLException {
		
		Veterinario a = new Veterinario();
		a.setId_veterinario(rs.getInt("id_veterinario"));
		a.setId_persona(rs.getInt("id_persona"));
		a.setNum_colegiado(rs.getString("num_colegiado"));
		return a;
	}
}
