/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2016 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.server.request.v110;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.AbstractTransactionRequest;
import org.geosdi.geoplatform.connector.server.request.ITransactionOperationStrategy;
import org.geosdi.geoplatform.connector.server.request.v110.transaction.GPTransactionMediator;
import org.geosdi.geoplatform.connector.server.request.v110.transaction.stax.FeatureStreamWriter;
import org.geosdi.geoplatform.gui.shared.wfs.TransactionOperation;
import org.geosdi.geoplatform.xml.wfs.v110.TransactionResponseType;
import org.geosdi.geoplatform.xml.wfs.v110.TransactionType;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class WFSTransactionRequestV110 extends AbstractTransactionRequest<TransactionResponseType> {

    public WFSTransactionRequestV110(GPServerConnector server) {
        super(server);
    }

    @Override
    protected HttpEntity preparePostEntity() throws Exception {
        return (operation == TransactionOperation.INSERT) ? preparePostEntityWithStax() : super.preparePostEntity();
    }

    @Override
    protected Object createRequest() throws Exception {
        if (operation == null) {
            throw new IllegalArgumentException("Transaction Operation " + "must not be null.");
        }

        ITransactionOperationStrategy operationStrategy = GPTransactionMediator.getStrategy(operation);
        Object elementType = operationStrategy.getOperation(this);

        TransactionType request = new TransactionType();
        request.setInsertOrUpdateOrDelete(Arrays.asList(elementType));

        return request;
    }

    @Override
    public String showRequestAsString() throws Exception {
        return (operation == TransactionOperation.INSERT) ? showRequestWithStax() : super.showRequestAsString();
    }

    /**
     * @return String
     * @throws Exception
     */
    protected final String showRequestWithStax() throws Exception {
        StringWriter stringWriter = new StringWriter();

        FeatureStreamWriter streamWriter = new FeatureStreamWriter();
        streamWriter.write(this, stringWriter);

        return stringWriter.toString();
    }

    /**
     * @return HttpEntity
     * @throws Exception
     */
    protected final HttpEntity preparePostEntityWithStax() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        FeatureStreamWriter streamWriter = new FeatureStreamWriter();
        streamWriter.write(this, outputStream);

        String request = outputStream.toString("UTF-8");
        logger.debug("\n*** Request TRANSACTION INSERT ***\n{}\n\n", request);

        return new StringEntity(request, ContentType.APPLICATION_XML);
    }

}
