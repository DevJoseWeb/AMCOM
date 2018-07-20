package com.amcom.cities.service;

import com.amcom.cities.dao.CrudDao;
import com.amcom.cities.entity.BaseEntity;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.List;

/**
 * Leitura e manutenção de cidades de um arquivo CSV feito com Java
 *
 * @author  Jose RF Junior
 * @version 1.0
 * @since   18/07/2018
 */
public class CrudService<E extends BaseEntity<ID>, ID, D extends CrudDao<E, ID>> {

    @Inject
    protected Instance<D> dao;

    public E insert(final E entity) {
        return dao.get().insert(entity);
    }

    public void insert(final List<E> entities) {
        entities.stream().forEach(entity -> dao.get().insert(entity));
    }

    public E update(final E entity) {
        return dao.get().update(entity);
    }

    public void remove(final ID id) {
        dao.get().remove(id);
    }

    public E findById(final ID id) {
        return dao.get().findById(id);
    }

    public List<E> findAll() {
        return dao.get().findAll();
    }
}
