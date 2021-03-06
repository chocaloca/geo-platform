/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2020 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.wms;

import org.geosdi.geoplatform.services.request.GPWMSGetFeatureInfoElement;
import org.geosdi.geoplatform.services.request.GPWMSGetFeatureInfoRequest;
import org.geosdi.geoplatform.services.request.WMSGetFeatureInfoBoundingBox;
import org.geosdi.geoplatform.services.request.WMSGetFeatureInfoPoint;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Arrays.asList;
import static org.geosdi.geoplatform.services.builder.WMSGetFeatureInfoResponseBuilder.wmsGetFeatureInfoResponseBuilder;
import static org.geosdi.geoplatform.services.request.WMSGetFeatureInfoResponseFormat.FEATURE_STORE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WMSGetFeatureInfoResponseBuilderTest {

    private static final Logger logger = LoggerFactory.getLogger(WMSGetFeatureInfoResponseBuilderTest.class);

    @Test
    public void loadWMSGetFeatureInfoResponseBuilderTest() throws Exception {
        GPWMSGetFeatureInfoRequest request = new GPWMSGetFeatureInfoRequest();
        request.setCrs("EPSG:4326");
        request.setWidth("101");
        request.setHeight("101");
        request.setBoundingBox(new WMSGetFeatureInfoBoundingBox() {
            {
                super.setMinx(14.403741359710693);
                super.setMiny(41.891523599624634);
                super.setMaxx(14.681146144866943);
                super.setMaxy(42.168928384780884);
            }
        });
        request.setPoint(new WMSGetFeatureInfoPoint() {
            {
                super.setX(50);
                super.setY(50);
            }
        });
        request.setFormat(FEATURE_STORE);
        GPWMSGetFeatureInfoElement featureInfoElement = new GPWMSGetFeatureInfoElement();
        featureInfoElement.setLayers(asList("PNSRS:ABR_4_5_comuni_aff_COM"));
        featureInfoElement.setWmsServerURL("https://servizi.protezionecivile.it/geoserver/PNSRS/wms");
        request.setWmsFeatureInfoElements(asList(featureInfoElement));
        logger.info("#####################{}\n", wmsGetFeatureInfoResponseBuilder()
                .withRequest(request)
                .build());
    }
}