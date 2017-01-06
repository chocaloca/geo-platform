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
package org.geosdi.geoplatform.persistence.dao.jpa.criteria;

import com.google.common.base.Preconditions;
import org.geosdi.geoplatform.persistence.dao.GPAbstractBaseDAO;
import org.hibernate.Cache;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPCriteriaJpaDAO<T extends Object, ID extends Serializable> extends GPAbstractBaseDAO<T, ID>
        implements BaseCriteriaJpaDAO<T, ID> {

    protected EntityManager entityManager;

    public GPCriteriaJpaDAO(Class<T> thePersistentClass) {
        super(thePersistentClass);
    }

    /**
     * @return {@link CriteriaQuery <T>}
     */
    @Override
    public CriteriaQuery<T> createCriteriaQuery() {
        return this.getCriteriaBuilder().createQuery(this.persistentClass);
    }

    /**
     * @return {@link CriteriaDelete <T>}
     */
    @Override
    public CriteriaDelete<T> createCriteriaDelete() {
        return this.getCriteriaBuilder().createCriteriaDelete(this.persistentClass);
    }

    /**
     * @return {@link CriteriaUpdate <T>}
     */
    @Override
    public CriteriaUpdate<T> createCriteriaUpdate() {
        return this.getCriteriaBuilder().createCriteriaUpdate(this.persistentClass);
    }

    /**
     * @return {@link CriteriaBuilder}
     */
    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        return this.entityManager.getCriteriaBuilder();
    }

    /**
     * @param theEntityManager
     */
    @PersistenceContext
    @Override
    public void setEm(EntityManager theEntityManager) {
        Preconditions.checkNotNull(theEntityManager);
        this.entityManager = theEntityManager;
    }

    /**
     * @return {@link SessionFactory}
     */
    @Override
    public final SessionFactory getSessionFactory() {
        return getSession().getSessionFactory();
    }

    /**
     * @return {@link Cache}
     */
    @Override
    public Cache getCache() {
        return getSessionFactory().getCache();
    }

    /**
     * @return {@link Session}
     */
    protected final Session getSession() {
        return (Session) this.entityManager.getDelegate();
    }

    /**
     * @return {@link Criteria}
     */
    protected final Criteria createCriteria() {
        return this.getSession().createCriteria(persistentClass);
    }
}
