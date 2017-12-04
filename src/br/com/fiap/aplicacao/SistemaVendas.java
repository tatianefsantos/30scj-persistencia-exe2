package br.com.fiap.aplicacao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.fiap.entity.Clientes;
import br.com.fiap.entity.Pedidos;
import br.com.fiap.helper.Helper;

public class SistemaVendas {

	static EntityManager em;

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SCJ PER Exercício 2");

		em = emf.createEntityManager();
		incluirCliente();
		em = emf.createEntityManager();
		listarClientes();
		buscarCliente(1);
		listarPedidosCliente(1);
		listarPedidosCliente(500);
		em.close();
		System.exit(1);
	}

	private static void incluirCliente() {
		System.out.println("Incluindo cliente...");
		Helper dao = new Helper(em);
		Clientes cliente = new Clientes();
		cliente.setEmail("taiane.d.santos@accenture.com");
		cliente.setNome("Tatiane dos Santos");

		Pedidos pedido = new Pedidos();
		pedido.setCliente(cliente);
		pedido.setData(new Date());
		pedido.setDescricao("Notebook");
		pedido.setValor(30000);

		cliente.getPedidos().add(pedido);

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -10);

		Pedidos pedido2 = new Pedidos();
		pedido2.setCliente(cliente);
		pedido2.setData(cal.getTime());
		pedido2.setDescricao("Impressora");
		pedido2.setValor(1000);

		cliente.getPedidos().add(pedido2);

		try {
			dao.salvarCliente(cliente);
			System.out.println("Cliente incluído com sucesso.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void listarClientes() {
		System.out.println("*** Lista de cliente ***");
		Helper dao = new Helper(em);
		List<Clientes> clientes = dao.listarClientes();
		for (Clientes cliente : clientes) {
			System.out.println(cliente.getNome() + ": " + cliente.getEmail());
		}
	}

	private static void buscarCliente(Integer idCliente) {
		System.out.println("Buscando cliente de código: " + idCliente);
		Helper dao = new Helper(em);
		Clientes f = dao.buscarCliente(idCliente);
		System.out.println(f.getNome() + ": " + f.getEmail());
	}

	private static void listarPedidosCliente(Integer idCliente) {
		System.out.println();
		System.out.println("*** Listar pedidos do cliente ***");
		Helper dao = new Helper(em);
		List<Pedidos> pedidos = dao.buscarPedidoCliente(idCliente);
		if (pedidos.size() > 0) {
			System.out.println("Foram encontrados " + pedidos.size() + " pedido(s) do cliente "
					+ pedidos.get(0).getCliente().getNome());
			for (Pedidos pedido : pedidos) {
				System.out.println("**********************");
				System.out.println("Descrição: " + pedido.getDescricao());
				System.out.println("Valor: " + pedido.getValor());
			}
		} else {
			System.out.println("Não foram encontrados pedidos para o cliente de código: " + idCliente);
		}
	}

}