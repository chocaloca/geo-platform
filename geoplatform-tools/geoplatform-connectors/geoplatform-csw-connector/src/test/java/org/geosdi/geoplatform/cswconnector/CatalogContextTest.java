/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.cswconnector;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import junit.framework.TestCase;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.geosdi.connector.jaxb.GPConnectorJAXBContext;
import org.geosdi.geoplatform.cswconnector.jaxb.CSWConnectorJAXBContext;
import org.geosdi.geoplatform.xml.csw.v202.CapabilitiesType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class CatalogContextTest extends TestCase {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private final static String CSW_HOST = "150.146.160.152";
    private final static String CSW_PATH = "/geonetwork/srv/en/csw";
    //
    private GPConnectorJAXBContext cswContext = CSWConnectorJAXBContext.getInstance();

    public void testJAXBContext() throws JAXBException {
        assertNotNull(cswContext);

        Unmarshaller m = cswContext.createUnmarshaller();

        try {
            HttpClient client = new DefaultHttpClient();

            List<NameValuePair> qparams = new ArrayList<NameValuePair>();
            qparams.add(new BasicNameValuePair("SERVICE", "CSW"));
            qparams.add(new BasicNameValuePair("REQUEST", "GetCapabilities"));

            URI uri = URIUtils.createURI("http", CSW_HOST, -1, CSW_PATH,
                    URLEncodedUtils.format(qparams, "UTF-8"), null);

            HttpGet get = new HttpGet(uri);

            HttpResponse response = client.execute(get);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream content = entity.getContent();

                CapabilitiesType cap = ((JAXBElement<CapabilitiesType>) m.unmarshal(
                                        content)).getValue();

                logger.info(
                        "CSW GET_CAPABILITIES @@@@@@@@@@@@@@@@@@@@@@@ " + cap);

                EntityUtils.consume(entity);
            }

        } catch (URISyntaxException ex) {
            logger.error(
                    "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ " + ex.getMessage());
        } catch (ClientProtocolException ex) {
            logger.error(
                    "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ " + ex.getMessage());
        } catch (IOException ex) {
            logger.error(
                    "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ " + ex.getMessage());
        }
    }
}
