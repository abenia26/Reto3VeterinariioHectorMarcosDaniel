package modelo;

import java.time.LocalDate;

public class Historial {
	protected int id_historial;
	protected int id_mascota;
	protected int id_tratamiento;
	protected LocalDate fecha;

	public Historial(int id_historial, int id_mascota, int id_tratamiento, LocalDate fecha) {
		super();
		this.id_historial = id_historial;
		this.id_mascota = id_mascota;
		this.id_tratamiento = id_tratamiento;
		this.fecha = fecha;
	}

	public Historial(int id_mascota, int id_tratamiento, LocalDate fecha) {
		super();
		this.id_mascota = id_mascota;
		this.id_tratamiento = id_tratamiento;
		this.fecha = fecha;
	}

	public Historial() {
		super();
	}

	public int getId_historial() {
		return id_historial;
	}

	public void setId_historial(int id_historial) {
		this.id_historial = id_historial;
	}

	public int getId_mascota() {
		return id_mascota;
	}

	public void setId_mascota(int id_mascota) {
		this.id_mascota = id_mascota;
	}

	public int getId_tratamiento() {
		return id_tratamiento;
	}

	public void setId_tratamiento(int id_tratamiento) {
		this.id_tratamiento = id_tratamiento;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Historial [id_historial=" + id_historial + ", id_mascota=" + id_mascota + ", id_tratamiento="
				+ id_tratamiento + ", fecha=" + fecha + "]";
	}

}
