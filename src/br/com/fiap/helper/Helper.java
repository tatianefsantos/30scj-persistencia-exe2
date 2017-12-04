package br.com.fiap.helper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.entity.Clientes;
import br.com.fiap.entity.Pedidos;

public class Helper {
	private EntityManager em;

	public Helper(EntityManager em) {
		this.em = em;
	}

	public void salvarCliente(Clientes cliente) throws Exception {
		try {
			em.getTransaction().begin();
			em.persist(cliente);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		} finally {
			em.close();
		}
	}

	public List<Clientes> listarClientes() {
		TypedQuery<Clientes> tQuery = em.createQuery("select c from Clientes c", Clientes.class);
		return tQuery.getResultList();
	}

	public Clientes buscarCliente(Integer idCliente) {
		TypedQuery<Clientes> tQuery = em.createQuery("select c from Clientes c where id = :id", Clientes.class);
		tQuery.setParameter("id", idCliente);
		return tQuery.getSingleResult();

	}
	
	public void salvarPedido(Pedidos pedido) throws Exception {
		try {
			em.getTransaction().begin();
			em.persist(pedido);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		} finally {
			em.close();
		}
	}

	public List<Pedidos> listarPedidos() {
		TypedQuery<Pedidos> tQuery = em.createQuery("select p from Pedidos p", Pedidos.class);
		return tQuery.getResultList();
	}

	public List<Pedidos> buscarPedidoCliente(Integer idCliente) {
		TypedQuery<Pedidos> tQuery = em.createQuery("select p from Pedidos p where idCliente = :idCliente", Pedidos.class);
		tQuery.setParameter("idCliente", idCliente);
		return tQuery.getResultList();

	}
	
	public Pedidos buscarPedido(Integer idPedido) {
		TypedQuery<Pedidos> tQuery = em.createQuery("select p from Pedidos p where id = :id", Pedidos.class);
		tQuery.setParameter("id", idPedido);
		return tQuery.getSingleResult();

	}


}