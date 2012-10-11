package mos.hibernate.manager.config.impl;

/**
 * hibernate数据库配置单
 * 
 * @author caiyu
 * @date 2012-9-26 上午10:31:12
 */

public class DatabaseConfig extends AbstractConfig {
	protected int type = database;

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof DatabaseConfig) {
			if (this.properties.equals(((MappingConfig) obj).properties))
				return true;
		}
		return false;
	}
}
