package com.amcom.cities.dao;

import com.amcom.cities.entity.BaseEntity;
import com.amcom.cities.entity.LogicExclusion;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Leitura e manutenção de cidades de um arquivo CSV feito com Java
 *
 * @author  Jose RF Junior
 * @version 1.0
 * @since   18/07/2018
 */
public abstract class LogicExclusionCrudDao<E extends BaseEntity<ID> & LogicExclusion, ID extends Serializable> extends CrudDao<E, ID> {

    @Override
    public void remove(ID id) {
        E entity = findById(id);
        if (entity != null) {
            entity.setExcluded(true);
        }
    }

    @Override
    public List<E> findAll() {
        return em.createQuery("SELECT e FROM " + getClassE().getSimpleName() + " e WHERE e.excluded = false ", getClassE())
                .getResultList();
    }
}
