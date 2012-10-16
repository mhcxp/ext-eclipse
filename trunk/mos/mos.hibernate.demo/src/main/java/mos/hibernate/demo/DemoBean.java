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
 * @date 2012-9-25 ÉÏÎç10:48:04
 */
@Entity
@Table(name = "DEMO")
public class DemoBean {
	@Id
	@TableGenerator(name = "DEMO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "DEMO")
	private String id;

	@Column(name = "name")
	private String name;

	public DemoBean() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
