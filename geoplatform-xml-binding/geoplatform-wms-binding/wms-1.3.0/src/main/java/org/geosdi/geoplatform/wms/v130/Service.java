//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2019.01.25 alle 11:53:13 AM CET 
//


package org.geosdi.geoplatform.wms.v130;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Name"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="WMS"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}Title"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}Abstract" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}KeywordList" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}OnlineResource"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}ContactInformation" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}Fees" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}AccessConstraints" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}LayerLimit" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}MaxWidth" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}MaxHeight" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "name",
    "title",
    "_abstract",
    "keywordList",
    "onlineResource",
    "contactInformation",
    "fees",
    "accessConstraints",
    "layerLimit",
    "maxWidth",
    "maxHeight"
})
@XmlRootElement(name = "Service")
public class Service implements ToString2
{

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Title", required = true)
    protected String title;
    @XmlElement(name = "Abstract")
    protected String _abstract;
    @XmlElement(name = "KeywordList")
    protected KeywordList keywordList;
    @XmlElement(name = "OnlineResource", required = true)
    protected OnlineResource onlineResource;
    @XmlElement(name = "ContactInformation")
    protected ContactInformation contactInformation;
    @XmlElement(name = "Fees")
    protected String fees;
    @XmlElement(name = "AccessConstraints")
    protected String accessConstraints;
    @XmlElement(name = "LayerLimit")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger layerLimit;
    @XmlElement(name = "MaxWidth")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger maxWidth;
    @XmlElement(name = "MaxHeight")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger maxHeight;

    /**
     * Recupera il valore della proprietà name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il valore della proprietà name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Recupera il valore della proprietà title.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Imposta il valore della proprietà title.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Recupera il valore della proprietà abstract.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbstract() {
        return _abstract;
    }

    /**
     * Imposta il valore della proprietà abstract.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbstract(String value) {
        this._abstract = value;
    }

    /**
     * Recupera il valore della proprietà keywordList.
     * 
     * @return
     *     possible object is
     *     {@link KeywordList }
     *     
     */
    public KeywordList getKeywordList() {
        return keywordList;
    }

    /**
     * Imposta il valore della proprietà keywordList.
     * 
     * @param value
     *     allowed object is
     *     {@link KeywordList }
     *     
     */
    public void setKeywordList(KeywordList value) {
        this.keywordList = value;
    }

    /**
     * Recupera il valore della proprietà onlineResource.
     * 
     * @return
     *     possible object is
     *     {@link OnlineResource }
     *     
     */
    public OnlineResource getOnlineResource() {
        return onlineResource;
    }

    /**
     * Imposta il valore della proprietà onlineResource.
     * 
     * @param value
     *     allowed object is
     *     {@link OnlineResource }
     *     
     */
    public void setOnlineResource(OnlineResource value) {
        this.onlineResource = value;
    }

    /**
     * Recupera il valore della proprietà contactInformation.
     * 
     * @return
     *     possible object is
     *     {@link ContactInformation }
     *     
     */
    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    /**
     * Imposta il valore della proprietà contactInformation.
     * 
     * @param value
     *     allowed object is
     *     {@link ContactInformation }
     *     
     */
    public void setContactInformation(ContactInformation value) {
        this.contactInformation = value;
    }

    /**
     * Recupera il valore della proprietà fees.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFees() {
        return fees;
    }

    /**
     * Imposta il valore della proprietà fees.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFees(String value) {
        this.fees = value;
    }

    /**
     * Recupera il valore della proprietà accessConstraints.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessConstraints() {
        return accessConstraints;
    }

    /**
     * Imposta il valore della proprietà accessConstraints.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessConstraints(String value) {
        this.accessConstraints = value;
    }

    /**
     * Recupera il valore della proprietà layerLimit.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getLayerLimit() {
        return layerLimit;
    }

    /**
     * Imposta il valore della proprietà layerLimit.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setLayerLimit(BigInteger value) {
        this.layerLimit = value;
    }

    /**
     * Recupera il valore della proprietà maxWidth.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxWidth() {
        return maxWidth;
    }

    /**
     * Imposta il valore della proprietà maxWidth.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxWidth(BigInteger value) {
        this.maxWidth = value;
    }

    /**
     * Recupera il valore della proprietà maxHeight.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxHeight() {
        return maxHeight;
    }

    /**
     * Imposta il valore della proprietà maxHeight.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxHeight(BigInteger value) {
        this.maxHeight = value;
    }

    public String toString() {
        final ToStringStrategy2 strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy2 strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy2 strategy) {
        {
            String theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName, (this.name!= null));
        }
        {
            String theTitle;
            theTitle = this.getTitle();
            strategy.appendField(locator, this, "title", buffer, theTitle, (this.title!= null));
        }
        {
            String theAbstract;
            theAbstract = this.getAbstract();
            strategy.appendField(locator, this, "_abstract", buffer, theAbstract, (this._abstract!= null));
        }
        {
            KeywordList theKeywordList;
            theKeywordList = this.getKeywordList();
            strategy.appendField(locator, this, "keywordList", buffer, theKeywordList, (this.keywordList!= null));
        }
        {
            OnlineResource theOnlineResource;
            theOnlineResource = this.getOnlineResource();
            strategy.appendField(locator, this, "onlineResource", buffer, theOnlineResource, (this.onlineResource!= null));
        }
        {
            ContactInformation theContactInformation;
            theContactInformation = this.getContactInformation();
            strategy.appendField(locator, this, "contactInformation", buffer, theContactInformation, (this.contactInformation!= null));
        }
        {
            String theFees;
            theFees = this.getFees();
            strategy.appendField(locator, this, "fees", buffer, theFees, (this.fees!= null));
        }
        {
            String theAccessConstraints;
            theAccessConstraints = this.getAccessConstraints();
            strategy.appendField(locator, this, "accessConstraints", buffer, theAccessConstraints, (this.accessConstraints!= null));
        }
        {
            BigInteger theLayerLimit;
            theLayerLimit = this.getLayerLimit();
            strategy.appendField(locator, this, "layerLimit", buffer, theLayerLimit, (this.layerLimit!= null));
        }
        {
            BigInteger theMaxWidth;
            theMaxWidth = this.getMaxWidth();
            strategy.appendField(locator, this, "maxWidth", buffer, theMaxWidth, (this.maxWidth!= null));
        }
        {
            BigInteger theMaxHeight;
            theMaxHeight = this.getMaxHeight();
            strategy.appendField(locator, this, "maxHeight", buffer, theMaxHeight, (this.maxHeight!= null));
        }
        return buffer;
    }

}
