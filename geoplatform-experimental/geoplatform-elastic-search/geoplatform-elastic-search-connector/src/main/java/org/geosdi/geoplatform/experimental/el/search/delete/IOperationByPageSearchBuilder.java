/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2018 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, operation this exception statement from your version.
 */
package org.geosdi.geoplatform.experimental.el.search.delete;

import com.google.common.base.Preconditions;
import org.geosdi.geoplatform.experimental.el.dao.GPPageableElasticSearchDAO;

import static org.geosdi.geoplatform.experimental.el.search.delete.OperationByPage.OperationByPageSearch;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IOperationByPageSearchBuilder {

    /**
     * @param thePage
     * @param <P>
     * @return {@link IOperationByPageSearchBuilder}
     */
    <P extends GPPageableElasticSearchDAO.PageAsync> IOperationByPageSearchBuilder withPage(P thePage);

    /**
     * @return {@link OperationByPage}
     * @throws Exception
     */
    OperationByPage build() throws Exception;

    /**
     *
     */
    class OperationByPageSearchBuilder implements IOperationByPageSearchBuilder {

        private GPPageableElasticSearchDAO.PageAsync page;

        private OperationByPageSearchBuilder() {
        }

        public static IOperationByPageSearchBuilder newInstance() {
            return new OperationByPageSearchBuilder();
        }

        /**
         * @param thePage
         * @return {@link IOperationByPageSearchBuilder}
         */
        @Override
        public <P extends GPPageableElasticSearchDAO.PageAsync> IOperationByPageSearchBuilder withPage(P thePage) {
            this.page = thePage;
            return self();
        }

        /**
         * @return {@link OperationByPage}
         * @throws Exception
         */
        @Override
        public OperationByPage build() throws Exception {
            Preconditions.checkNotNull(this.page, "The Parameter Page must not be null.");
            return new OperationByPageSearch(this.page);
        }

        /**
         * @return {@link IOperationByPageSearchBuilder}
         */
        protected IOperationByPageSearchBuilder self() {
            return this;
        }
    }
}
