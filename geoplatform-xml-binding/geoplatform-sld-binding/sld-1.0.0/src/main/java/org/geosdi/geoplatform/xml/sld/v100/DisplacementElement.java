//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.7 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.08.25 alle 11:12:35 PM CEST 
//


package org.geosdi.geoplatform.xml.sld.v100;

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
 *         &lt;element ref="{http://www.opengis.net/sld}DisplacementX"/>
 *         &lt;element ref="{http://www.opengis.net/sld}DisplacementY"/>
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
    "displacementX",
    "displacementY"
})
@XmlRootElement(name = "Displacement")
public class DisplacementElement
    implements ToString
{

    @XmlElement(name = "DisplacementX", required = true)
    protected ParameterValueType displacementX;
    @XmlElement(name = "DisplacementY", required = true)
    protected ParameterValueType displacementY;

    /**
     * Recupera il valore della proprietà displacementX.
     * 
     * @return
     *     possible object is
     *     {@link ParameterValueType }
     *     
     */
    public ParameterValueType getDisplacementX() {
        return displacementX;
    }

    /**
     * Imposta il valore della proprietà displacementX.
     * 
     * @param value
     *     allowed object is
     *     {@link ParameterValueType }
     *     
     */
    public void setDisplacementX(ParameterValueType value) {
        this.displacementX = value;
    }

    public boolean isSetDisplacementX() {
        return (this.displacementX!= null);
    }

    /**
     * Recupera il valore della proprietà displacementY.
     * 
     * @return
     *     possible object is
     *     {@link ParameterValueType }
     *     
     */
    public ParameterValueType getDisplacementY() {
        return displacementY;
    }

    /**
     * Imposta il valore della proprietà displacementY.
     * 
     * @param value
     *     allowed object is
     *     {@link ParameterValueType }
     *     
     */
    public void setDisplacementY(ParameterValueType value) {
        this.displacementY = value;
    }

    public boolean isSetDisplacementY() {
        return (this.displacementY!= null);
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
            ParameterValueType theDisplacementX;
            theDisplacementX = this.getDisplacementX();
            strategy.appendField(locator, this, "displacementX", buffer, theDisplacementX);
        }
        {
            ParameterValueType theDisplacementY;
            theDisplacementY = this.getDisplacementY();
            strategy.appendField(locator, this, "displacementY", buffer, theDisplacementY);
        }
        return buffer;
    }

}