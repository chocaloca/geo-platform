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
// Generated on: 2015.06.17 at 04:41:23 PM CEST 
//


package org.geosdi.geoplatform.xml.iso19139.v20060504.gmd;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gco.AbstractObjectType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gco.CharacterStringPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.src.SVServiceIdentificationType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * Basic information about data
 * 
 * <p>Java class for AbstractMD_Identification_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AbstractMD_Identification_Type">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.isotc211.org/2005/gco}AbstractObject_Type">
 *       &lt;sequence>
 *         &lt;element name="citation" type="{http://www.isotc211.org/2005/gmd}CI_Citation_PropertyType"/>
 *         &lt;element name="abstract" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType"/>
 *         &lt;element name="purpose" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         &lt;element name="credit" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.isotc211.org/2005/gmd}MD_ProgressCode_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="pointOfContact" type="{http://www.isotc211.org/2005/gmd}CI_ResponsibleParty_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="resourceMaintenance" type="{http://www.isotc211.org/2005/gmd}MD_MaintenanceInformation_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="graphicOverview" type="{http://www.isotc211.org/2005/gmd}MD_BrowseGraphic_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="resourceFormat" type="{http://www.isotc211.org/2005/gmd}MD_Format_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="descriptiveKeywords" type="{http://www.isotc211.org/2005/gmd}MD_Keywords_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="resourceSpecificUsage" type="{http://www.isotc211.org/2005/gmd}MD_Usage_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="resourceConstraints" type="{http://www.isotc211.org/2005/gmd}MD_Constraints_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="aggregationInfo" type="{http://www.isotc211.org/2005/gmd}MD_AggregateInformation_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractMD_Identification_Type", propOrder = {
    "citation",
    "_abstract",
    "purpose",
    "credit",
    "status",
    "pointOfContact",
    "resourceMaintenance",
    "graphicOverview",
    "resourceFormat",
    "descriptiveKeywords",
    "resourceSpecificUsage",
    "resourceConstraints",
    "aggregationInfo"
})
@XmlSeeAlso({
    MDDataIdentificationType.class,
    MDServiceIdentificationType.class,
    SVServiceIdentificationType.class
})
public abstract class AbstractMDIdentificationType
    extends AbstractObjectType
    implements ToString
{

    @XmlElement(required = true)
    protected CICitationPropertyType citation;
    @XmlElement(name = "abstract", required = true)
    protected CharacterStringPropertyType _abstract;
    protected CharacterStringPropertyType purpose;
    protected List<CharacterStringPropertyType> credit;
    protected List<MDProgressCodePropertyType> status;
    protected List<CIResponsiblePartyPropertyType> pointOfContact;
    protected List<MDMaintenanceInformationPropertyType> resourceMaintenance;
    protected List<MDBrowseGraphicPropertyType> graphicOverview;
    protected List<MDFormatPropertyType> resourceFormat;
    protected List<MDKeywordsPropertyType> descriptiveKeywords;
    protected List<MDUsagePropertyType> resourceSpecificUsage;
    protected List<MDConstraintsPropertyType> resourceConstraints;
    protected List<MDAggregateInformationPropertyType> aggregationInfo;

    /**
     * Gets the value of the citation property.
     * 
     * @return
     *     possible object is
     *     {@link CICitationPropertyType }
     *     
     */
    public CICitationPropertyType getCitation() {
        return citation;
    }

    /**
     * Sets the value of the citation property.
     * 
     * @param value
     *     allowed object is
     *     {@link CICitationPropertyType }
     *     
     */
    public void setCitation(CICitationPropertyType value) {
        this.citation = value;
    }

    public boolean isSetCitation() {
        return (this.citation!= null);
    }

    /**
     * Gets the value of the abstract property.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getAbstract() {
        return _abstract;
    }

    /**
     * Sets the value of the abstract property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setAbstract(CharacterStringPropertyType value) {
        this._abstract = value;
    }

    public boolean isSetAbstract() {
        return (this._abstract!= null);
    }

    /**
     * Gets the value of the purpose property.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getPurpose() {
        return purpose;
    }

    /**
     * Sets the value of the purpose property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setPurpose(CharacterStringPropertyType value) {
        this.purpose = value;
    }

    public boolean isSetPurpose() {
        return (this.purpose!= null);
    }

    /**
     * Gets the value of the credit property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the credit property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCredit().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CharacterStringPropertyType }
     * 
     * 
     */
    public List<CharacterStringPropertyType> getCredit() {
        if (credit == null) {
            credit = new ArrayList<CharacterStringPropertyType>();
        }
        return this.credit;
    }

    public boolean isSetCredit() {
        return ((this.credit!= null)&&(!this.credit.isEmpty()));
    }

    public void unsetCredit() {
        this.credit = null;
    }

    /**
     * Gets the value of the status property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the status property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStatus().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDProgressCodePropertyType }
     * 
     * 
     */
    public List<MDProgressCodePropertyType> getStatus() {
        if (status == null) {
            status = new ArrayList<MDProgressCodePropertyType>();
        }
        return this.status;
    }

    public boolean isSetStatus() {
        return ((this.status!= null)&&(!this.status.isEmpty()));
    }

    public void unsetStatus() {
        this.status = null;
    }

    /**
     * Gets the value of the pointOfContact property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pointOfContact property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPointOfContact().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CIResponsiblePartyPropertyType }
     * 
     * 
     */
    public List<CIResponsiblePartyPropertyType> getPointOfContact() {
        if (pointOfContact == null) {
            pointOfContact = new ArrayList<CIResponsiblePartyPropertyType>();
        }
        return this.pointOfContact;
    }

    public boolean isSetPointOfContact() {
        return ((this.pointOfContact!= null)&&(!this.pointOfContact.isEmpty()));
    }

    public void unsetPointOfContact() {
        this.pointOfContact = null;
    }

    /**
     * Gets the value of the resourceMaintenance property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resourceMaintenance property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResourceMaintenance().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDMaintenanceInformationPropertyType }
     * 
     * 
     */
    public List<MDMaintenanceInformationPropertyType> getResourceMaintenance() {
        if (resourceMaintenance == null) {
            resourceMaintenance = new ArrayList<MDMaintenanceInformationPropertyType>();
        }
        return this.resourceMaintenance;
    }

    public boolean isSetResourceMaintenance() {
        return ((this.resourceMaintenance!= null)&&(!this.resourceMaintenance.isEmpty()));
    }

    public void unsetResourceMaintenance() {
        this.resourceMaintenance = null;
    }

    /**
     * Gets the value of the graphicOverview property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the graphicOverview property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGraphicOverview().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDBrowseGraphicPropertyType }
     * 
     * 
     */
    public List<MDBrowseGraphicPropertyType> getGraphicOverview() {
        if (graphicOverview == null) {
            graphicOverview = new ArrayList<MDBrowseGraphicPropertyType>();
        }
        return this.graphicOverview;
    }

    public boolean isSetGraphicOverview() {
        return ((this.graphicOverview!= null)&&(!this.graphicOverview.isEmpty()));
    }

    public void unsetGraphicOverview() {
        this.graphicOverview = null;
    }

    /**
     * Gets the value of the resourceFormat property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resourceFormat property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResourceFormat().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDFormatPropertyType }
     * 
     * 
     */
    public List<MDFormatPropertyType> getResourceFormat() {
        if (resourceFormat == null) {
            resourceFormat = new ArrayList<MDFormatPropertyType>();
        }
        return this.resourceFormat;
    }

    public boolean isSetResourceFormat() {
        return ((this.resourceFormat!= null)&&(!this.resourceFormat.isEmpty()));
    }

    public void unsetResourceFormat() {
        this.resourceFormat = null;
    }

    /**
     * Gets the value of the descriptiveKeywords property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the descriptiveKeywords property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDescriptiveKeywords().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDKeywordsPropertyType }
     * 
     * 
     */
    public List<MDKeywordsPropertyType> getDescriptiveKeywords() {
        if (descriptiveKeywords == null) {
            descriptiveKeywords = new ArrayList<MDKeywordsPropertyType>();
        }
        return this.descriptiveKeywords;
    }

    public boolean isSetDescriptiveKeywords() {
        return ((this.descriptiveKeywords!= null)&&(!this.descriptiveKeywords.isEmpty()));
    }

    public void unsetDescriptiveKeywords() {
        this.descriptiveKeywords = null;
    }

    /**
     * Gets the value of the resourceSpecificUsage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resourceSpecificUsage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResourceSpecificUsage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDUsagePropertyType }
     * 
     * 
     */
    public List<MDUsagePropertyType> getResourceSpecificUsage() {
        if (resourceSpecificUsage == null) {
            resourceSpecificUsage = new ArrayList<MDUsagePropertyType>();
        }
        return this.resourceSpecificUsage;
    }

    public boolean isSetResourceSpecificUsage() {
        return ((this.resourceSpecificUsage!= null)&&(!this.resourceSpecificUsage.isEmpty()));
    }

    public void unsetResourceSpecificUsage() {
        this.resourceSpecificUsage = null;
    }

    /**
     * Gets the value of the resourceConstraints property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resourceConstraints property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResourceConstraints().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDConstraintsPropertyType }
     * 
     * 
     */
    public List<MDConstraintsPropertyType> getResourceConstraints() {
        if (resourceConstraints == null) {
            resourceConstraints = new ArrayList<MDConstraintsPropertyType>();
        }
        return this.resourceConstraints;
    }

    public boolean isSetResourceConstraints() {
        return ((this.resourceConstraints!= null)&&(!this.resourceConstraints.isEmpty()));
    }

    public void unsetResourceConstraints() {
        this.resourceConstraints = null;
    }

    /**
     * Gets the value of the aggregationInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aggregationInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAggregationInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDAggregateInformationPropertyType }
     * 
     * 
     */
    public List<MDAggregateInformationPropertyType> getAggregationInfo() {
        if (aggregationInfo == null) {
            aggregationInfo = new ArrayList<MDAggregateInformationPropertyType>();
        }
        return this.aggregationInfo;
    }

    public boolean isSetAggregationInfo() {
        return ((this.aggregationInfo!= null)&&(!this.aggregationInfo.isEmpty()));
    }

    public void unsetAggregationInfo() {
        this.aggregationInfo = null;
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
        super.appendFields(locator, buffer, strategy);
        {
            CICitationPropertyType theCitation;
            theCitation = this.getCitation();
            strategy.appendField(locator, this, "citation", buffer, theCitation);
        }
        {
            CharacterStringPropertyType theAbstract;
            theAbstract = this.getAbstract();
            strategy.appendField(locator, this, "_abstract", buffer, theAbstract);
        }
        {
            CharacterStringPropertyType thePurpose;
            thePurpose = this.getPurpose();
            strategy.appendField(locator, this, "purpose", buffer, thePurpose);
        }
        {
            List<CharacterStringPropertyType> theCredit;
            theCredit = (this.isSetCredit()?this.getCredit():null);
            strategy.appendField(locator, this, "credit", buffer, theCredit);
        }
        {
            List<MDProgressCodePropertyType> theStatus;
            theStatus = (this.isSetStatus()?this.getStatus():null);
            strategy.appendField(locator, this, "status", buffer, theStatus);
        }
        {
            List<CIResponsiblePartyPropertyType> thePointOfContact;
            thePointOfContact = (this.isSetPointOfContact()?this.getPointOfContact():null);
            strategy.appendField(locator, this, "pointOfContact", buffer, thePointOfContact);
        }
        {
            List<MDMaintenanceInformationPropertyType> theResourceMaintenance;
            theResourceMaintenance = (this.isSetResourceMaintenance()?this.getResourceMaintenance():null);
            strategy.appendField(locator, this, "resourceMaintenance", buffer, theResourceMaintenance);
        }
        {
            List<MDBrowseGraphicPropertyType> theGraphicOverview;
            theGraphicOverview = (this.isSetGraphicOverview()?this.getGraphicOverview():null);
            strategy.appendField(locator, this, "graphicOverview", buffer, theGraphicOverview);
        }
        {
            List<MDFormatPropertyType> theResourceFormat;
            theResourceFormat = (this.isSetResourceFormat()?this.getResourceFormat():null);
            strategy.appendField(locator, this, "resourceFormat", buffer, theResourceFormat);
        }
        {
            List<MDKeywordsPropertyType> theDescriptiveKeywords;
            theDescriptiveKeywords = (this.isSetDescriptiveKeywords()?this.getDescriptiveKeywords():null);
            strategy.appendField(locator, this, "descriptiveKeywords", buffer, theDescriptiveKeywords);
        }
        {
            List<MDUsagePropertyType> theResourceSpecificUsage;
            theResourceSpecificUsage = (this.isSetResourceSpecificUsage()?this.getResourceSpecificUsage():null);
            strategy.appendField(locator, this, "resourceSpecificUsage", buffer, theResourceSpecificUsage);
        }
        {
            List<MDConstraintsPropertyType> theResourceConstraints;
            theResourceConstraints = (this.isSetResourceConstraints()?this.getResourceConstraints():null);
            strategy.appendField(locator, this, "resourceConstraints", buffer, theResourceConstraints);
        }
        {
            List<MDAggregateInformationPropertyType> theAggregationInfo;
            theAggregationInfo = (this.isSetAggregationInfo()?this.getAggregationInfo():null);
            strategy.appendField(locator, this, "aggregationInfo", buffer, theAggregationInfo);
        }
        return buffer;
    }

    public void setCredit(List<CharacterStringPropertyType> value) {
        this.credit = null;
        List<CharacterStringPropertyType> draftl = this.getCredit();
        draftl.addAll(value);
    }

    public void setStatus(List<MDProgressCodePropertyType> value) {
        this.status = null;
        List<MDProgressCodePropertyType> draftl = this.getStatus();
        draftl.addAll(value);
    }

    public void setPointOfContact(List<CIResponsiblePartyPropertyType> value) {
        this.pointOfContact = null;
        List<CIResponsiblePartyPropertyType> draftl = this.getPointOfContact();
        draftl.addAll(value);
    }

    public void setResourceMaintenance(List<MDMaintenanceInformationPropertyType> value) {
        this.resourceMaintenance = null;
        List<MDMaintenanceInformationPropertyType> draftl = this.getResourceMaintenance();
        draftl.addAll(value);
    }

    public void setGraphicOverview(List<MDBrowseGraphicPropertyType> value) {
        this.graphicOverview = null;
        List<MDBrowseGraphicPropertyType> draftl = this.getGraphicOverview();
        draftl.addAll(value);
    }

    public void setResourceFormat(List<MDFormatPropertyType> value) {
        this.resourceFormat = null;
        List<MDFormatPropertyType> draftl = this.getResourceFormat();
        draftl.addAll(value);
    }

    public void setDescriptiveKeywords(List<MDKeywordsPropertyType> value) {
        this.descriptiveKeywords = null;
        List<MDKeywordsPropertyType> draftl = this.getDescriptiveKeywords();
        draftl.addAll(value);
    }

    public void setResourceSpecificUsage(List<MDUsagePropertyType> value) {
        this.resourceSpecificUsage = null;
        List<MDUsagePropertyType> draftl = this.getResourceSpecificUsage();
        draftl.addAll(value);
    }

    public void setResourceConstraints(List<MDConstraintsPropertyType> value) {
        this.resourceConstraints = null;
        List<MDConstraintsPropertyType> draftl = this.getResourceConstraints();
        draftl.addAll(value);
    }

    public void setAggregationInfo(List<MDAggregateInformationPropertyType> value) {
        this.aggregationInfo = null;
        List<MDAggregateInformationPropertyType> draftl = this.getAggregationInfo();
        draftl.addAll(value);
    }

}
