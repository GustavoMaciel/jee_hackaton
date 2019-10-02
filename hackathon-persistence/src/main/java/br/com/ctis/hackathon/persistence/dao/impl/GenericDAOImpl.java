package br.com.ctis.hackathon.persistence.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;

import br.com.ctis.hackathon.persistence.ValidatorBase;
import br.com.ctis.hackathon.persistence.dao.GenericDAO;
import br.com.ctis.hackathon.persistence.model.EntidadeBase;

/**
 * Fornece as funções básicas de um DAO
 *
 * @param <K>
 *            - Chave
 * @param <T>
 *            - Tipo
 */
public abstract class GenericDAOImpl<K extends Serializable, T extends EntidadeBase<K>> implements GenericDAO<K, T> {

	protected static final String AND = " AND ";
	protected static final String OR = " OR ";

	@PersistenceContext(unitName = "br.com.ctis.hackathon")
	private EntityManager em;

	private Class<T> clazz;

	public GenericDAOImpl() {
		this.clazz = (Class<T>) getTypeClass();
	}

	@Override
	public List<T> listar() {
		return em.createQuery(getCriteriaBuilder().createQuery(clazz)).getResultList();
	}

	@Override
	public T consultarPorId(K id) {
		return em.find(clazz, id);
	}

	@Override
	public T gravar(T objeto) {
		ValidatorBase.validate(objeto);

		if (objeto.getId() == null) {
			em.persist(objeto);
			return objeto;
		}

		return em.merge(objeto);
	}

	@Override
	public void excluir(K id) {
		T objeto = consultarPorId(id);
		excluir(objeto);

	}

	@Override
	public void excluir(T objeto) {
		em.remove(objeto);
	}

	private Class<?> getTypeClass() {
		return (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}

	protected CriteriaBuilder getCriteriaBuilder() {
		return em.getCriteriaBuilder();
	}

	public EntityManager getEntityManager() {
		return em;
	}

}