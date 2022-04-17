package cn.ixan.jmh.domain;

import java.io.Serializable;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/4/17 12:02
 * @description dog
 * @version 1.0
 */
public class Dog implements Serializable {
	private String name;
	private Boolean alive;
	private Boolean loyal;

	public Dog() {
	}

	public Dog(String name, Boolean alive, Boolean loyal) {
		this.name = name;
		this.alive = alive;
		this.loyal = loyal;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getAlive() {
		return alive;
	}

	public void setAlive(Boolean alive) {
		this.alive = alive;
	}

	public Boolean getLoyal() {
		return loyal;
	}

	public void setLoyal(Boolean loyal) {
		this.loyal = loyal;
	}
}
