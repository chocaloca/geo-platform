/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2020 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.7 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.08.25 alle 11:12:35 PM CEST 
//


package org.geosdi.geoplatform.xml.sld.v100;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice minOccurs="0">
 *           &lt;element ref="{http://www.opengis.net/sld}GraphicFill"/>
 *           &lt;element ref="{http://www.opengis.net/sld}GraphicStroke"/>
 *         &lt;/choice>
 *         &lt;element ref="{http://www.opengis.net/sld}CssParameter" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "graphicFill",
    "graphicStroke",
    "cssParameter"
})
@XmlRootElement(name = "Stroke")
public class StrokeElement
    implements ToString
{

    @XmlElement(name = "GraphicFill")
    protected GraphicFillElement graphicFill;
    @XmlElement(name = "GraphicStroke")
    protected GraphicStrokeElement graphicStroke;
    @XmlElement(name = "CssParameter")
    protected List<CssParameterElement> cssParameter;

    /**
     * Recupera il valore della proprietà graphicFill.
     * 
     * @return
     *     possible object is
     *     {@link GraphicFillElement }
     *     
     */
    public GraphicFillElement getGraphicFill() {
        return graphicFill;
    }

    /**
     * Imposta il valore della proprietà graphicFill.
     * 
     * @param value
     *     allowed object is
     *     {@link GraphicFillElement }
     *     
     */
    public void setGraphicFill(GraphicFillElement value) {
        this.graphicFill = value;
    }

    public boolean isSetGraphicFill() {
        return (this.graphicFill!= null);
    }

    /**
     * Recupera il valore della proprietà graphicStroke.
     * 
     * @return
     *     possible object is
     *     {@link GraphicStrokeElement }
     *     
     */
    public GraphicStrokeElement getGraphicStroke() {
        return graphicStroke;
    }

    /**
     * Imposta il valore della proprietà graphicStroke.
     * 
     * @param value
     *     allowed object is
     *     {@link GraphicStrokeElement }
     *     
     */
    public void setGraphicStroke(GraphicStrokeElement value) {
        this.graphicStroke = value;
    }

    public boolean isSetGraphicStroke() {
        return (this.graphicStroke!= null);
    }

    /**
     * Gets the value of the cssParameter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cssParameter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCssParameter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CssParameterElement }
     * 
     * 
     */
    public List<CssParameterElement> getCssParameter() {
        if (cssParameter == null) {
            cssParameter = new ArrayList<CssParameterElement>();
        }
        return this.cssParameter;
    }

    public boolean isSetCssParameter() {
        return ((this.cssParameter!= null)&&(!this.cssParameter.isEmpty()));
    }

    public void unsetCssParameter() {
        this.cssParameter = null;
    }

    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        {
            GraphicFillElement theGraphicFill;
            theGraphicFill = this.getGraphicFill();
            strategy.appendField(locator, this, "graphicFill", buffer, theGraphicFill);
        }
        {
            GraphicStrokeElement theGraphicStroke;
            theGraphicStroke = this.getGraphicStroke();
            strategy.appendField(locator, this, "graphicStroke", buffer, theGraphicStroke);
        }
        {
            List<CssParameterElement> theCssParameter;
            theCssParameter = (this.isSetCssParameter()?this.getCssParameter():null);
            strategy.appendField(locator, this, "cssParameter", buffer, theCssParameter);
        }
        return buffer;
    }

    public void setCssParameter(List<CssParameterElement> value) {
        this.cssParameter = null;
        List<CssParameterElement> draftl = this.getCssParameter();
        draftl.addAll(value);
    }

}
