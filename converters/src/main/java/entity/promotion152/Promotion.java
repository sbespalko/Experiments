//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.11.27 at 09:59:54 AM MSK 
//


package entity.promotion152;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>Java class for promotion152 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="promotion152">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GlobalData" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ID" type="{}T_ExternalID" minOccurs="0"/>
 *                   &lt;element name="Description" type="{}T_Description" minOccurs="0"/>
 *                   &lt;element name="Origin" type="{}T_Code2" minOccurs="0"/>
 *                   &lt;element name="EffectiveDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                   &lt;element name="ExpiryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                   &lt;element name="OperatorDisplayName" type="{}T_Name" minOccurs="0"/>
 *                   &lt;element name="CustomerDisplayName" type="{}T_Name" minOccurs="0"/>
 *                   &lt;element name="ReceiptPrinterName" type="{}T_Name" minOccurs="0"/>
 *                   &lt;element name="PromotionType" type="{}T_Name" minOccurs="0"/>
 *                   &lt;element name="ExcludedTenders" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="TenderTypeCode" type="{}T_Code4" maxOccurs="unbounded"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Condition" type="{}T_Condition" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ProcessMode" use="required" type="{}T_ProcessMode" />
 *       &lt;attribute name="NumberOfConditions" type="{}T_Count" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Promotion", propOrder = {
    "info",
    "globalData",
    "condition"
})
public class Promotion {

    @XmlElement(name = "Info") protected TInfo info;
    @XmlElement(name = "PromotionData") protected Promotion.GlobalData globalData;
    @XmlElement(name = "Rebate") protected List<TCondition> condition;
    @XmlAttribute(name = "ProcessMode", required = true) protected TProcessMode processMode;
    @XmlAttribute(name = "NumberOfConditions") protected BigInteger numberOfConditions;

    public TInfo getInfo() {
        return info;
    }

    /**
     * Gets the value of the globalData property.
     *
     * @return
     *     possible object is
     *     {@link Promotion.GlobalData }
     *
     */
    public Promotion.GlobalData getGlobalData() {
        return globalData;
    }

    /**
     * Sets the value of the globalData property.
     *
     * @param value
     *     allowed object is
     *     {@link Promotion.GlobalData }
     *
     */
    public void setGlobalData(Promotion.GlobalData value) {
        this.globalData = value;
    }

    /**
     * Gets the value of the condition property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the condition property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCondition().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TCondition }
     *
     *
     */
    public List<TCondition> getCondition() {
        if (condition == null) {
            condition = new ArrayList<TCondition>();
        }
        return this.condition;
    }

    /**
     * Gets the value of the processMode property.
     *
     * @return
     *     possible object is
     *     {@link TProcessMode }
     *
     */
    public TProcessMode getProcessMode() {
        return processMode;
    }

    /**
     * Sets the value of the processMode property.
     *
     * @param value
     *     allowed object is
     *     {@link TProcessMode }
     *
     */
    public void setProcessMode(TProcessMode value) {
        this.processMode = value;
    }

    /**
     * Gets the value of the numberOfConditions property.
     *
     * @return
     *     possible object is
     *     {@link BigInteger }
     *
     */
    public BigInteger getNumberOfConditions() {
        return numberOfConditions;
    }

    /**
     * Sets the value of the numberOfConditions property.
     *
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *
     */
    public void setNumberOfConditions(BigInteger value) {
        this.numberOfConditions = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="ID" type="{}T_ExternalID" minOccurs="0"/>
     *         &lt;element name="Description" type="{}T_Description" minOccurs="0"/>
     *         &lt;element name="Origin" type="{}T_Code2" minOccurs="0"/>
     *         &lt;element name="EffectiveDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *         &lt;element name="ExpiryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *         &lt;element name="OperatorDisplayName" type="{}T_Name" minOccurs="0"/>
     *         &lt;element name="CustomerDisplayName" type="{}T_Name" minOccurs="0"/>
     *         &lt;element name="ReceiptPrinterName" type="{}T_Name" minOccurs="0"/>
     *         &lt;element name="PromotionType" type="{}T_Name" minOccurs="0"/>
     *         &lt;element name="ExcludedTenders" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="TenderTypeCode" type="{}T_Code4" maxOccurs="unbounded"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "id",
        "description",
        "origin",
        "effectiveDate",
        "expiryDate",
        "operatorDisplayName",
        "customerDisplayName",
        "receiptPrinterName",
        "promotionType",
        "excludedTenders"
    })
    public static class GlobalData {

        @XmlElement(name = "ID")
        protected String id;
        @XmlElement(name = "Description")
        protected String description;
        @XmlElement(name = "Origin")
        protected String origin;
        @XmlElement(name = "EffectiveDate")
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar effectiveDate;
        @XmlElement(name = "ExpiryDate")
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar expiryDate;
        @XmlElement(name = "OperatorDisplayName")
        protected String operatorDisplayName;
        @XmlElement(name = "CustomerDisplayName")
        protected String customerDisplayName;
        @XmlElement(name = "ReceiptPrinterName")
        protected String receiptPrinterName;
        @XmlElement(name = "PromotionType")
        protected String promotionType;
        @XmlElement(name = "ExcludedTenders")
        protected Promotion.GlobalData.ExcludedTenders excludedTenders;

        /**
         * Gets the value of the id property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getID() {
            return id;
        }

        /**
         * Sets the value of the id property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setID(String value) {
            this.id = value;
        }

        /**
         * Gets the value of the description property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getDescription() {
            return description;
        }

        /**
         * Sets the value of the description property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setDescription(String value) {
            this.description = value;
        }

        /**
         * Gets the value of the origin property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getOrigin() {
            return origin;
        }

        /**
         * Sets the value of the origin property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setOrigin(String value) {
            this.origin = value;
        }

        /**
         * Gets the value of the effectiveDate property.
         *
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *
         */
        public XMLGregorianCalendar getEffectiveDate() {
            return effectiveDate;
        }

        /**
         * Sets the value of the effectiveDate property.
         *
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *
         */
        public void setEffectiveDate(XMLGregorianCalendar value) {
            this.effectiveDate = value;
        }

        /**
         * Gets the value of the expiryDate property.
         *
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *
         */
        public XMLGregorianCalendar getExpiryDate() {
            return expiryDate;
        }

        /**
         * Sets the value of the expiryDate property.
         *
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *
         */
        public void setExpiryDate(XMLGregorianCalendar value) {
            this.expiryDate = value;
        }

        /**
         * Gets the value of the operatorDisplayName property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getOperatorDisplayName() {
            return operatorDisplayName;
        }

        /**
         * Sets the value of the operatorDisplayName property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setOperatorDisplayName(String value) {
            this.operatorDisplayName = value;
        }

        /**
         * Gets the value of the customerDisplayName property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getCustomerDisplayName() {
            return customerDisplayName;
        }

        /**
         * Sets the value of the customerDisplayName property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setCustomerDisplayName(String value) {
            this.customerDisplayName = value;
        }

        /**
         * Gets the value of the receiptPrinterName property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getReceiptPrinterName() {
            return receiptPrinterName;
        }

        /**
         * Sets the value of the receiptPrinterName property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setReceiptPrinterName(String value) {
            this.receiptPrinterName = value;
        }

        /**
         * Gets the value of the promotionType property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getPromotionType() {
            return promotionType;
        }

        /**
         * Sets the value of the promotionType property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setPromotionType(String value) {
            this.promotionType = value;
        }

        /**
         * Gets the value of the excludedTenders property.
         *
         * @return
         *     possible object is
         *     {@link Promotion.GlobalData.ExcludedTenders }
         *
         */
        public Promotion.GlobalData.ExcludedTenders getExcludedTenders() {
            return excludedTenders;
        }

        /**
         * Sets the value of the excludedTenders property.
         *
         * @param value
         *     allowed object is
         *     {@link Promotion.GlobalData.ExcludedTenders }
         *
         */
        public void setExcludedTenders(Promotion.GlobalData.ExcludedTenders value) {
            this.excludedTenders = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="TenderTypeCode" type="{}T_Code4" maxOccurs="unbounded"/>
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
            "tenderTypeCode"
        })
        public static class ExcludedTenders {

            @XmlElement(name = "TenderTypeCode", required = true)
            protected List<String> tenderTypeCode;

            /**
             * Gets the value of the tenderTypeCode property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the tenderTypeCode property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getTenderTypeCode().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link String }
             * 
             * 
             */
            public List<String> getTenderTypeCode() {
                if (tenderTypeCode == null) {
                    tenderTypeCode = new ArrayList<String>();
                }
                return this.tenderTypeCode;
            }

        }

    }

}
