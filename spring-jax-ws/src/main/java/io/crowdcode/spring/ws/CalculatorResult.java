package io.crowdcode.spring.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="CalculatorResultType")
@XmlAccessorType(XmlAccessType.FIELD)
public class CalculatorResult {

    public CalculatorResult(Double value) {
        this.value = value;
    }

    @XmlElement(name="result")
    private Double value;

    public Double getValue() {
        return value;
    }
    
}
