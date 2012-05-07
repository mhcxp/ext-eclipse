/*******************************************************************************
 * Copyright(C) 2011 Arzan Corporation. All rights reserved.
 *
 * Contributors:
 *     Arzan Corporation - initial API and implementation
 *******************************************************************************/
package galaxy.derby.core.test;


/**
 * @author dzh 
 * @date Oct 31, 2011 3:17:02 PM
 */
public class Test_DerbyManager {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test_embedded();
	}

	private static void test_embedded() {
		String path = "";
//		String path = Platform.getInstanceLocation().getURL().getPath();
//		path = Platform.getLocation().makeAbsolute().toString();
//		path = Platform.get
		System.out.println(path);
	}

}
