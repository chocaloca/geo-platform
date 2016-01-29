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
package org.geosdi.geoplatform.gml.api.parser.jts.geometry.point;

import com.google.common.base.Preconditions;
import javax.xml.bind.JAXBElement;
import org.geosdi.geoplatform.gml.api.DirectPosition;
import org.geosdi.geoplatform.gml.api.Point;
import org.geosdi.geoplatform.gml.api.PointProperty;
import org.geosdi.geoplatform.gml.api.jaxb.AbstractGMLObjectFactory;
import org.geosdi.geoplatform.gml.api.jaxb.GMLObjectFactory;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.geosdi.geoplatform.gml.api.parser.jts.AbstractJTSParser;
import org.geosdi.geoplatform.gml.api.parser.jts.AbstractJTSSRSParser;
import org.geosdi.geoplatform.gml.api.parser.jts.DefaultJTSSRSParser;
import org.geosdi.geoplatform.gml.api.parser.jts.coordinate.CoordinateParser;
import org.geosdi.geoplatform.gml.api.parser.jts.coordinate.JTSCoordinateParser;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSPointParser extends AbstractJTSParser<Point, PointProperty, com.vividsolutions.jts.geom.Point> {

    private CoordinateParser coordinateParser;

    public JTSPointParser(AbstractGMLObjectFactory theGmlObjectFactory,
            AbstractJTSSRSParser theSrsParser,
            CoordinateParser theCoordinateParser) {
        super(theGmlObjectFactory, theSrsParser);
        this.coordinateParser = theCoordinateParser;
    }

    @Override
    protected Point canParseGeometry(
            com.vividsolutions.jts.geom.Point jtsGeometry)
            throws ParserException {

        Point point = gmlObjectFactory.createPointType();

        if (!jtsGeometry.isEmpty()) {
            DirectPosition directPosition = coordinateParser.parseCoordinate(
                    jtsGeometry.getCoordinate());
            point.setPos(directPosition);
        }

        return point;
    }

    @Override
    public PointProperty parseProperty(
            com.vividsolutions.jts.geom.Point jtsGeometry)
            throws ParserException {
        Preconditions.checkNotNull(jtsGeometry, "The JTS Point must "
                + "not be null.");

        PointProperty pointProperty = gmlObjectFactory.createPointPropertyType();
        pointProperty.setPoint(super.parseGeometry(jtsGeometry));

        return pointProperty;
    }

    @Override
    public JAXBElement<? extends Point> buildJAXBElement(
            com.vividsolutions.jts.geom.Point geometry) throws ParserException {
        return gmlObjectFactory.createPoint(super.parseGeometry(geometry));
    }
}
