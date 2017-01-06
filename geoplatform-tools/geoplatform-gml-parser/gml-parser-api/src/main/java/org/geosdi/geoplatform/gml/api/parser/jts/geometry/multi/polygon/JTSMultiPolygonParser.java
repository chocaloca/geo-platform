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
package org.geosdi.geoplatform.gml.api.parser.jts.geometry.multi.polygon;

import com.google.common.base.Preconditions;
import com.vividsolutions.jts.geom.Polygon;
import javax.xml.bind.JAXBElement;
import org.geosdi.geoplatform.gml.api.MultiPolygon;
import org.geosdi.geoplatform.gml.api.MultiPolygonProperty;
import org.geosdi.geoplatform.gml.api.jaxb.AbstractGMLObjectFactory;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.geosdi.geoplatform.gml.api.parser.jts.AbstractJTSParser;
import org.geosdi.geoplatform.gml.api.parser.jts.AbstractJTSSRSParser;
import org.geosdi.geoplatform.gml.api.parser.jts.geometry.polygon.JTSPolygonParser;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSMultiPolygonParser extends AbstractJTSParser<MultiPolygon, MultiPolygonProperty, com.vividsolutions.jts.geom.MultiPolygon> {

    private JTSPolygonParser polygonParser;

    public JTSMultiPolygonParser(AbstractGMLObjectFactory theGmlObjectFactory,
            AbstractJTSSRSParser theSrsParser,
            JTSPolygonParser thePolygonParser) {

        super(theGmlObjectFactory, theSrsParser);
        this.polygonParser = thePolygonParser;
    }

    @Override
    protected MultiPolygon canParseGeometry(
            com.vividsolutions.jts.geom.MultiPolygon jtsGeometry)
            throws ParserException {

        MultiPolygon multiPolygon = gmlObjectFactory.createMultiPolygonType();

        for (int i = 0; i < jtsGeometry.getNumGeometries(); i++) {
            Polygon polygon = (Polygon) jtsGeometry.getGeometryN(i);
            multiPolygon.addPolygonMember(polygonParser.parseProperty(polygon));
        }

        return multiPolygon;
    }

    @Override
    public MultiPolygonProperty parseProperty(
            com.vividsolutions.jts.geom.MultiPolygon jtsGeometry)
            throws ParserException {

        Preconditions.checkNotNull(jtsGeometry, "The MultiPolygon JTS "
                + "Geometry must not be null.");

        MultiPolygonProperty multiPolygonProperty = gmlObjectFactory.createMultiPolygonPropertyType();
        multiPolygonProperty.setMultiPolygon(super.parseGeometry(jtsGeometry));

        return multiPolygonProperty;
    }

    @Override
    public JAXBElement<? extends MultiPolygon> buildJAXBElement(
            com.vividsolutions.jts.geom.MultiPolygon geometry) throws ParserException {
        return gmlObjectFactory.createMultiPolygon(super.parseGeometry(geometry));
    }
}
