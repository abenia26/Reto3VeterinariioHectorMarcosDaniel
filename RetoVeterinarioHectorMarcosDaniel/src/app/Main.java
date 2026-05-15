package app;

import java.util.Scanner;

import dao.ClienteDAO;
import dao.HistorialDAO;
import dao.MascotaDAO;
import modelo.Cliente;
import modelo.Historial;
import modelo.Mascota;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		ClienteDAO cliDAO = new ClienteDAO();
		MascotaDAO masDAO = new MascotaDAO();
		HistorialDAO hisDAO = new HistorialDAO();


		System.out.println("Todos los clientes:");
		for (Cliente c : cliDAO.obtenerTodos()) {
			System.out.println(c);
		}

		System.out.println();
		System.out.println("Selecciona un id cliente de los mostrados:");
		int idCliente = Integer.parseInt(sc.nextLine());

		System.out.println();
		System.out.println("Mascotas del cliente " + idCliente);
		for (Mascota m : masDAO.obtenerTodos()) {
			if (m.getId_cliente() == idCliente) {
				System.out.println(m);
			}
		}

		System.out.println();
		System.out.println("Todas las mascotas:");
		for (Mascota m : masDAO.obtenerTodos()) {
			System.out.println(m);
		}

		System.out.println();
		System.out.println("Selecciona un id mascota de los mostrados:");
		int idMascota = Integer.parseInt(sc.nextLine());

		System.out.println();
		System.out.println("Historial de la mascota " + idMascota + ":");
		for (Historial h : hisDAO.obtenerTodos()) {
			if (h.getId_mascota() == idMascota) {
				System.out.println(h);
			}
		}

		sc.close();
	}

}