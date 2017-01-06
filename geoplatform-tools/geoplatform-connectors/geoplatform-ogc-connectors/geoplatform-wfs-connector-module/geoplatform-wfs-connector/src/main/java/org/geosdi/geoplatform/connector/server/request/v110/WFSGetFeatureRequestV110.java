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
package org.geosdi.geoplatform.connector.server.request.v110;

import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.AbstractGetFeatureRequest;
import org.geosdi.geoplatform.connector.server.request.v110.query.responsibility.ILogicOperatorHandler;
import org.geosdi.geoplatform.gui.shared.bean.BBox;
import org.geosdi.geoplatform.xml.filter.v110.BBOXType;
import org.geosdi.geoplatform.xml.filter.v110.FilterType;
import org.geosdi.geoplatform.xml.filter.v110.GmlObjectIdType;
import org.geosdi.geoplatform.xml.filter.v110.PropertyNameType;
import org.geosdi.geoplatform.xml.gml.v311.DirectPositionType;
import org.geosdi.geoplatform.xml.gml.v311.EnvelopeType;
import org.geosdi.geoplatform.xml.wfs.v110.FeatureCollectionType;
import org.geosdi.geoplatform.xml.wfs.v110.GetFeatureType;
import org.geosdi.geoplatform.xml.wfs.v110.QueryType;
import org.geosdi.geoplatform.xml.wfs.v110.ResultTypeType;

import javax.xml.bind.JAXBElement;
import java.util.Arrays;

/**
 * @author Giuseppe La Scaleia - <giuseppe.lascaleia@geosdi.org>
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class WFSGetFeatureRequestV110 extends AbstractGetFeatureRequest<FeatureCollectionType> {

    private static final org.geosdi.geoplatform.xml.filter.v110.ObjectFactory filterFactory = new org.geosdi.geoplatform.xml.filter.v110.ObjectFactory();
    private static final org.geosdi.geoplatform.xml.gml.v311.ObjectFactory gmlFactory = new org.geosdi.geoplatform.xml.gml.v311.ObjectFactory();

    public WFSGetFeatureRequestV110(GPServerConnector server) {
        super(server);
    }

    @Override
    protected Object createRequest() throws Exception {
        if (typeName == null) {
            throw new IllegalArgumentException("typeName must not be null.");
        }

        GetFeatureType request = new GetFeatureType();

        QueryType query = new QueryType();
        query.setTypeName(Arrays.asList(typeName));
        request.getQuery().add(query);

        if (super.isSetFeatureIDs()) {
            FilterType filter = new FilterType();

            for (String featureID : featureIDs) {
                // Add featureID to filter
                GmlObjectIdType obj = new GmlObjectIdType();
                obj.setId(featureID);

                JAXBElement<GmlObjectIdType> gmlObjectId = filterFactory.createGmlObjectId(obj);
                filter.getId().add(gmlObjectId);
            }

            query.setFilter(filter);
        }

        if (super.isSetPropertyNames()) {
            for (String propertyName : propertyNames) {
                query.getPropertyNameOrXlinkPropertyNameOrFunction().add(propertyName);
            }
        }

        if (srs != null) {
            query.setSrsName(srs);
        }

        if (bBox != null) {
            JAXBElement<BBOXType> areaOperator = this.createAreaOperator(bBox);

            FilterType filter = query.getFilter();
            if (filter == null) {
                filter = new FilterType();
                query.setFilter(filter);
            }
            filter.setSpatialOps(areaOperator);
        }

        if (super.isSetQueryDTO()) {
            FilterType filterType = query.getFilter();
            if (filterType == null) {
                filterType = new FilterType();
                query.setFilter(filterType);
            }
            ILogicOperatorHandler.WFSQueryRestrictionsBuilder.builder()
                    .withFilterType(filterType)
                    .withQueryDTO(queryDTO)
                    .build();
        }

        request.setResultType(resultType != null ? ResultTypeType.fromValue(resultType) : ResultTypeType.RESULTS);

        request.setOutputFormat(outputFormat != null ? outputFormat : "text/xml; subtype=gml/3.1.1");

        if (maxFeatures != null) {
            request.setMaxFeatures(maxFeatures);
        }

        return request;
    }

    private JAXBElement<BBOXType> createAreaOperator(BBox bBox) {
        logger.debug("\n+++ {} +++", bBox);

        BBOXType bBoxType = new BBOXType();

        PropertyNameType propertyNameType = new PropertyNameType();
        propertyNameType.setContent(Arrays.<Object>asList(NAME_GEOMETRY));
        bBoxType.setPropertyName(propertyNameType);

        EnvelopeType envelope = this.createEnvelope(bBox);
        if (srs != null) {
            envelope.setSrsName(srs);
        }
        bBoxType.setEnvelope(gmlFactory.createEnvelope(envelope));

        return filterFactory.createBBOX(bBoxType);
    }

    private EnvelopeType createEnvelope(BBox bBox) {
        EnvelopeType envelope = new EnvelopeType();

        DirectPositionType lower = new DirectPositionType();
        lower.setValue(Arrays.asList(bBox.getMinX(), bBox.getMinY()));
        envelope.setLowerCorner(lower);

        DirectPositionType upper = new DirectPositionType();
        upper.setValue(Arrays.asList(bBox.getMaxX(), bBox.getMaxY()));
        envelope.setUpperCorner(upper);

        return envelope;
    }

}
