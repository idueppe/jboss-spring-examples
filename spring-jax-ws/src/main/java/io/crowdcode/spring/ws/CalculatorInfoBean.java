package io.crowdcode.spring.ws;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

@Component
public class CalculatorInfoBean implements CalculatorInfo, BeanNameAware {

	private String name;

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setBeanName(String name) {
		this.name = name;
	}

}
