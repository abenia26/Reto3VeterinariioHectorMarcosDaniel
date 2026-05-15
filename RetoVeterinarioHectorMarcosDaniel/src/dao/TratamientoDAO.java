package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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


}