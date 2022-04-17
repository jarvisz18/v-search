package cn.ixan.jmh.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/4/17 12:00
 * @description 小汽车
 * @version 1.0
 */
public class Car implements Serializable {
	private String sign;
	private BigDecimal price;

	private List<Dog> myDog;

	public Car() {
	}

	public Car(String sign, BigDecimal price, List<Dog> myDog) {
		this.sign = sign;
		this.price = price;
		this.myDog = myDog;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<Dog> getMyDog() {
		return myDog;
	}

	public void setMyDog(List<Dog> myDog) {
		this.myDog = myDog;
	}
}
