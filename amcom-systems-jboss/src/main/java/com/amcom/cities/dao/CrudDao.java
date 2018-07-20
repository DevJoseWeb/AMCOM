package com.amcom.cities.dao;

import com.amcom.cities.entity.BaseEntity;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Leitura e manutenção de cidades de um arquivo CSV feito com Java
 *
 * @author  Jose RF Junior
 * @version 1.0
 * @since   18/07/2018
 */
public abstract class CrudDao<E extends BaseEntity<ID>, ID> {

    @Produces
    @PersistenceContext
    protected EntityManager em;

    protected Class<E> classE;

    public E insert(final E entity) {
        em.persist(entity);
        return entity;
    }

    public E update(final E entity) {
        return em.merge(entity);
    }

    public void remove(final ID id) {
        throw new IllegalStateException("Logic exclusion should be implemented!");
    }

    public E findById(final ID id) {
        if (id == null) {
            return null;
        }
        return em.find(getClassE(), id);
    }

    public List<E> findAll() {
        return em.createQuery("SELECT e FROM " + getClassE().getSimpleName() + " e", getClassE())
                .getResultList();
    }

    public Class<E> getClassE() {
        if (classE == null) {
            classE = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return classE;
    }
}
