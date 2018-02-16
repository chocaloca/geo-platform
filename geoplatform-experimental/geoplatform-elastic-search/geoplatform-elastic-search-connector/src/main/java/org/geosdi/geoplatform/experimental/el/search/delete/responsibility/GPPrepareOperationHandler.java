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
package org.geosdi.geoplatform.experimental.el.search.delete.responsibility;

import com.google.common.base.Preconditions;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.geosdi.geoplatform.experimental.el.dao.ElasticSearchDAO;
import org.geosdi.geoplatform.experimental.el.search.delete.OperationByPage;
import org.geosdi.geoplatform.experimental.el.search.delete.OperationByPage.IOperationByPageResult;
import org.geosdi.geoplatform.experimental.el.search.delete.OperationByPage.OperationByPageSearchDecorator;
import org.geosdi.geoplatform.experimental.el.search.strategy.IGPStrategyRepository;

import static org.geosdi.geoplatform.experimental.el.search.delete.responsibility.GPElasticSearchOperationHandler.GPElasticSearchDeleteHandlerType.PREPARER_OPERATION_TYPE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
class GPPrepareOperationHandler extends GPAbstractOperationHandler<ElasticSearchDAO> {

    public GPPrepareOperationHandler(IGPStrategyRepository strategyRepository) {
        super(strategyRepository);
        super.setSuccessor(new GPSingleOperationHandler(strategyRepository));
    }

    /**
     * @param page
     * @param searchDAO
     * @return {@link Result}
     * @throws Exception
     */
    @Override
    public <Result extends IOperationByPageResult, Page extends OperationByPage> Result operation(Page page,
                                                                                                  ElasticSearchDAO searchDAO
    ) throws Exception {
        return (canDoOperation(page) ? internalOperation(page, searchDAO) : super.forwardOperation(page, searchDAO));
    }

    /**
     * @param page
     * @param searchDAO
     * @return {@link Result}
     * @throws Exception
     */
    @Override
    protected final <Result extends IOperationByPageResult, Page extends OperationByPage> Result internalOperation(Page page,
                                                                                                                   ElasticSearchDAO searchDAO) throws Exception {
        Preconditions.checkNotNull(page, "Parameter Page must not be null.");
        Preconditions.checkNotNull(searchDAO, "Parameter SearchDAO must not be null.");

        SearchRequestBuilder builder = page.buildPage(searchDAO.client()
                .prepareSearch(searchDAO.getIndexName()).setTypes(searchDAO.getIndexType()));
        Long totalElementsToDelete = builder.execute().get().getHits().getTotalHits();
        return super.forwardOperation(new OperationByPageSearchDecorator(page.getPage(), totalElementsToDelete),
                searchDAO);
    }

    /**
     * @param page
     * @return {@link Boolean}
     */
    @Override
    protected <Page extends OperationByPage> Boolean canDoOperation(Page page) {
        return ((page.getSize() != null) && (page.getSize() == 0));
    }

    /**
     * @return {@link TYPE}
     */
    @Override
    public <TYPE extends IGPElasticSearchDeleteHandlerType> TYPE getOperationType() {
        return (TYPE) PREPARER_OPERATION_TYPE;
    }
}
