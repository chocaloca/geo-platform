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
package org.geosdi.geoplatform.connector.server.request;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.geosdi.geoplatform.connector.server.GPServerConnector;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;

/**
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public abstract class GPPostConnectorRequest<T> extends PostConnectorRequest<T> {
    /**
     * @param server
     */
    public GPPostConnectorRequest(GPServerConnector server) {
        super(server);
    }

    @Override
    public T getResponse() throws Exception {
        T response = null;

        HttpPost httpPost = super.getPostMethod();
        CloseableHttpResponse httpResponse = super.securityConnector
                .secure(this, httpPost);

        HttpEntity responseEntity = httpResponse.getEntity();
        if (responseEntity != null) {
            InputStream is = responseEntity.getContent();

            Unmarshaller unmarshaller = getUnmarshaller();
            Object content = null;
            try {
                content = unmarshaller.unmarshal(is);
            } catch (JAXBException ex) {
                logger.error("######################JAXBException : ", ex);
            }
            if ((content == null) || !(content instanceof JAXBElement)) { // ExceptionReport
                logger.error("#############CONTENT : {} #############\n", content);
                throw new IllegalStateException("Connector Server Error: "
                        + "incorrect responce");
            }

            JAXBElement<T> elementType = (JAXBElement<T>) content;
            response = elementType.getValue();

            EntityUtils.consume(responseEntity);
        } else {
            throw new IllegalStateException("Connector Server Error: "
                    + "Connection problem");
        }

        return response;
    }
}
