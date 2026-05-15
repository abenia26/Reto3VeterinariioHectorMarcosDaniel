package app;

import java.util.Scanner;

import dao.ClienteDAO;
import modelo.Cliente;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		ClienteDAO cliDAO = new ClienteDAO();
		
		System.out.println("Obtener todos los clientes: ");
		for (Cliente c : cliDAO.obtenerTodos()) {
			System.out.println(c);
		}
		System.out.println();
		System.out.println("Selecciona un id cliente de los mostrados: ");
		String entrada1 = sc.nextLine();
		System.out.println(cli);
		
	}

}
