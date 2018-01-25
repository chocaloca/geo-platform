/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2018 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-b10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.04.17 at 10:27:36 PM CEST 
//
package org.geosdi.geoplatform.xml.gml.v311;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.gml.api.DirectPosition;
import org.geosdi.geoplatform.gml.api.Point;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

/**
 * A Point is defined by a single coordinate tuple.
 *
 * <p>Java class for PointType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained
 * within this class.
 *
 * <pre>
 * &lt;complexType name="PointType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractGeometricPrimitiveType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element ref="{http://www.opengis.net/gml}pos"/>
 *           &lt;element ref="{http://www.opengis.net/gml}coordinates"/>
 *           &lt;element ref="{http://www.opengis.net/gml}coord"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlRootElement(name = "Point")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PointType", propOrder = {
    "pos",
    "coordinates",
    "coord"
})
public class PointType extends AbstractGeometricPrimitiveType
        implements ToString, Point {

    protected DirectPositionType pos;
    protected CoordinatesType coordinates;
    protected CoordType coord;

    /**
     * Gets the value of the pos property.
     *
     * @return possible object is {@link DirectPositionType }
     *
     */
    @Override
    public DirectPositionType getPos() {
        return pos;
    }

    /**
     * Sets the value of the pos property.
     *
     * @param value allowed object is {@link DirectPositionType }
     *
     */
    @Override
    public void setPos(DirectPosition value) {
        this.pos = (DirectPositionType) value;
    }

    @Override
    public boolean isSetPos() {
        return (this.pos != null);
    }

    /**
     * Deprecated with GML version 3.1.0 for coordinates with ordinate values
     * that are numbers. Use "pos" instead. The "coordinates" element shall only
     * be used for coordinates with ordinates that require a string
     * representation, e.g. DMS representations.
     *
     * @return possible object is {@link CoordinatesType }
     *
     */
    @Override
    public CoordinatesType getCoordinates() {
        return coordinates;
    }

    /**
     * Sets the value of the coordinates property.
     *
     * @param value allowed object is {@link CoordinatesType }
     *
     */
    public void setCoordinates(CoordinatesType value) {
        this.coordinates = value;
    }

    @Override
    public boolean isSetCoordinates() {
        return (this.coordinates != null);
    }

    /**
     * Deprecated with GML version 3.0. Use "pos" instead. The "coord" element
     * is included for backwards compatibility with GML 2.
     *
     * @return possible object is {@link CoordType }
     *
     */
    @Override
    public CoordType getCoord() {
        return coord;
    }

    /**
     * Sets the value of the coord property.
     *
     * @param value allowed object is {@link CoordType }
     *
     */
    public void setCoord(CoordType value) {
        this.coord = value;
    }

    @Override
    public boolean isSetCoord() {
        return (this.coord != null);
    }

    @Override
    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    @Override
    public StringBuilder append(ObjectLocator locator,
            StringBuilder buffer,
            ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    @Override
    public StringBuilder appendFields(ObjectLocator locator,
            StringBuilder buffer,
            ToStringStrategy strategy) {
        super.appendFields(locator, buffer, strategy);
        {
            DirectPositionType thePos;
            thePos = this.getPos();
            strategy.appendField(locator, this, "pos", buffer, thePos);
        }
        {
            CoordinatesType theCoordinates;
            theCoordinates = this.getCoordinates();
            strategy.appendField(locator, this, "coordinates", buffer,
                    theCoordinates);
        }
        {
            CoordType theCoord;
            theCoord = this.getCoord();
            strategy.appendField(locator, this, "coord", buffer, theCoord);
        }
        return buffer;
    }
}
