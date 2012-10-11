package mos.hibernate.manager.config.impl;

/**
 * hibernate”≥…‰≈‰÷√µ•
 * 
 * @author caiyu
 * @date 2012-9-26 …œŒÁ10:31:21
 */

public class MappingConfig extends AbstractConfig {
	protected int type = mapping;

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof MappingConfig) {
			if (this.properties.equals(((MappingConfig) obj).properties))
				return true;
		}
		return false;
	}
}
