package galaxy.ide.service.auth.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * ��ȡ SQL �ű���ִ��
 * 
 * @author caiyu
 */
public class SqlFileExecutor {

	/**
	 * ��ȡ SQL �ļ�����ȡ SQL ���
	 * 
	 * @param sqlFile
	 *            SQL �ű��ļ�
	 * @return List<sql> �������� SQL ���� List
	 * @throws Exception
	 */
	private static List<String> loadSql(String sqlFile) throws Exception {
		List<String> sqlList = new ArrayList<String>();

		try {
			InputStream sqlFileIn = new FileInputStream(sqlFile);

			StringBuffer sqlSb = new StringBuffer();
			byte[] buff = new byte[1024];
			int byteRead = 0;
			while ((byteRead = sqlFileIn.read(buff)) != -1) {
				sqlSb.append(new String(buff, 0, byteRead));
			}

			String[] sqlArr = sqlSb.toString()
					.split("(;\\s*\\r\\n)|(;\\s*\\n)");
			for (int i = 0; i < sqlArr.length; i++) {
				String sql = sqlArr[i].replaceAll("--.*", "").trim();
				if (!sql.equals("")) {
					sqlList.add(sql);
					System.out.println(i+"> "+ sql);
				}
			}
			return sqlList;
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}

	/**
	 * ����������ִ�� SQL �ű��ļ�������������������ݿ����ͬ��һ��������
	 * 
	 * @param conn
	 *            �������ݿ�����
	 * @param sqlFile
	 *            SQL �ű��ļ�
	 * @throws Exception
	 */
	public synchronized static int[] execute(Connection conn, String sqlFile)
			throws Exception {
		Statement stmt = null;
		List<String> sqlList = loadSql(sqlFile);
		stmt = conn.createStatement();
		for (String sql : sqlList) {
			stmt.addBatch(sql);
		}
		int[] rows = stmt.executeBatch();
		return rows;
	}
	
	
}