package galaxy.sqlanlysis.core.model.value;

import galaxy.sqlanlysis.core.dialect.Dialect;

/**
 * ��ֵ���ͣ������Ǹ��ּ򵥵���������
 * 
 * @author caiyu
 * @date 2012-12-3
 */
public class SimpleValue extends ValueModel {
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String render(Dialect dialect) {
		// TODO Auto-generated method stub
		return content;
	}
}
