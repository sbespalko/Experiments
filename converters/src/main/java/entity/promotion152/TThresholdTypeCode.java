//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.11.27 at 09:59:54 AM MSK 
//


package entity.promotion152;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for T_ThresholdTypeCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="T_ThresholdTypeCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;length value="4"/>
 *     &lt;enumeration value="QUT "/>
 *     &lt;enumeration value="QUTI"/>
 *     &lt;enumeration value="AMT "/>
 *     &lt;enumeration value="AMTI"/>
 *     &lt;enumeration value="AMTS"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "T_ThresholdTypeCode")
@XmlEnum
public enum TThresholdTypeCode {

    @XmlEnumValue("QUT ")
    QUT("QUT "),
    QUTI("QUTI"),
    @XmlEnumValue("AMT ")
    AMT("AMT "),
    AMTI("AMTI"),
    AMTS("AMTS");
    private final String value;

    TThresholdTypeCode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TThresholdTypeCode fromValue(String v) {
        for (TThresholdTypeCode c: TThresholdTypeCode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
