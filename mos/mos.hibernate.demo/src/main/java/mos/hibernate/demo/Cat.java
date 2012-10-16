package mos.hibernate.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * @author caiyu
 * @date 2012-10-11 ÏÂÎç2:41:21
 */
@Entity
@Table(name = "CAT")
public class Cat {
	@Id
	@TableGenerator(name = "CAT", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CAT")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="color")
	private String color;

	public Cat() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("ID:");
		sb.append(id);
		sb.append("\n");
		sb.append("Name:");
		sb.append(name);
		sb.append("\n");
		sb.append("Color:");
		sb.append(color);
		sb.append("\n");
		return sb.toString();
	}
}
