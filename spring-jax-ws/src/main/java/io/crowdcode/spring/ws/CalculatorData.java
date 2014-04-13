package io.crowdcode.spring.ws;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="CalculatorDataType")
@XmlAccessorType(XmlAccessType.FIELD)
public class CalculatorData {
    
    public enum OperatorType{ADD, DIVIDE, MULTIPLY, SUBSTRACT}
    
    private OperatorType operator;

    @XmlElement(name="value")
    private List<Double> values;

    public List<Double> getValues() {
        return values;
    }

    public void setValues(List<Double> values) {
        this.values = values;
    }

    public OperatorType getOperator() {
        return operator;
    }

    public void setOperator(OperatorType operator) {
        this.operator = operator;
    }
}
