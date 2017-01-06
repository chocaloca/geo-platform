/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2017 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.persistence.dao.hibernate;

import com.google.common.base.Preconditions;
import org.geosdi.geoplatform.persistence.dao.GPAbstractBaseDAO;
import org.geosdi.geoplatform.persistence.dao.GPBaseDAO;
import org.geosdi.geoplatform.persistence.dao.exception.GPDAOException;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Transactional
public abstract class GPAbstractHibernateDAO<T extends Object, ID extends Serializable> extends GPAbstractBaseDAO<T, ID>
        implements GPBaseDAO<T, ID> {

    @Autowired
    private SessionFactory sessionFactory;

    public GPAbstractHibernateDAO(Class<T> thePersistentClass) {
        super(thePersistentClass);
    }

    @Override
    public T persist(T entity) {
        Preconditions.checkNotNull(entity, "Entity to persist must not be null.");
        getCurrentSession().persist(entity);
        return entity;
    }

    @Override
    public void update(T entity) {
        Preconditions.checkNotNull(entity, "Entity to update must not be null.");
        getCurrentSession().merge(entity);
    }

    /**
     * @param entities
     * @return {@link Collection <T>}
     */
    @Override
    public Collection<T> persist(Iterable<T> entities) {
        List<T> persistedEntities = new ArrayList<>();
        for (T entity : entities) {
            persistedEntities.add(this.persist(entity));
        }
        return persistedEntities;
    }

    @Override
    public void delete(ID id) {
        T entity = find(id);
        getCurrentSession().delete(entity);
    }

    @Override
    public List<T> findByCriteria(Criterion... criterion) throws GPDAOException {
        try {
            Criteria crit = getCurrentSession().createCriteria(persistentClass);
            Arrays.stream(criterion).forEach(c -> crit.add(c));
            return crit.list();
        } catch (HibernateException ex) {
            logger.error("HibernateException : " + ex);
            throw new GPDAOException(ex);
        }
    }

    @Override
    public T find(ID id) throws GPDAOException {
        Preconditions.checkArgument(id != null, "The Parameter ID must not be null.");
        try {
            Criteria crit = getCurrentSession().createCriteria(persistentClass);
            return (T) crit.add(Restrictions.idEq(id)).uniqueResult();
        } catch (HibernateException ex) {
            logger.error("HibernateException : " + ex);
            throw new GPDAOException(ex);
        }
    }

    @Override
    public List<T> findAll() {
        return getCurrentSession().createCriteria(persistentClass).list();
    }

    @Override
    public List<T> findAll(int start, int end) throws GPDAOException {
        try {
            Criteria crit = getCurrentSession().createCriteria(persistentClass);
            crit.setFirstResult(start);
            crit.setMaxResults(end);
            return crit.list();
        } catch (HibernateException ex) {
            logger.error("HibernateException : " + ex);
            throw new GPDAOException(ex);
        }
    }

    @Override
    public List<T> findAll(int start, int end, Criterion... criterion) throws
            GPDAOException {
        try {
            Criteria crit = getCurrentSession().createCriteria(persistentClass);
            Arrays.stream(criterion).forEach(c -> crit.add(c));
            crit.setFirstResult(start);
            crit.setMaxResults(end);
            return crit.list();
        } catch (HibernateException ex) {
            logger.error("HibernateException : " + ex);
            throw new GPDAOException(ex);
        }
    }

    @Override
    public Integer removeAll() {
        return getCurrentSession().createSQLQuery("delete from "
                + persistentClass.getSimpleName()).executeUpdate();
    }

    @Override
    public Number count() {
        return (Number) getCurrentSession().createCriteria(persistentClass)
                .setProjection(Projections.rowCount()).uniqueResult();
    }

    /**
     * @param criterion
     * @return {@link Number}
     */
    @Override
    public Number count(Criterion criterion) {
        return (Number) getCurrentSession().createCriteria(persistentClass).add(criterion)
                .setProjection(Projections.rowCount()).uniqueResult();
    }

    protected Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }
}
