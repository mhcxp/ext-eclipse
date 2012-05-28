/**
 * Copyright 2012 Ext-Eclipse Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eclipse.example.policy.buddy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author dzh
 * @version 1.0 May 28, 2012 4:42:44 PM
 */
public class TestBuddy {

	public static void testBuddy() {
		BufferedReader in = null;
		try {
			// test finding object
			Class<?> one = Class
					.forName("eclipse.example.policy.buddy.register1.RegisterOne");
			one.newInstance(); // print "RegisterOne"

			// test getting exported resource
			in = new BufferedReader(new InputStreamReader(
					one.getResourceAsStream("/test.txt")));
			System.out.println(in.readLine()); // print "Ext-Eclipse Project"

			// test classloader
			ClassLoader cl = one.getClassLoader();
			System.out.println(cl.toString()); // print
												// "org.eclipse.osgi.internal.baseadaptor.DefaultClassLoader"
			while ((cl = cl.getParent()) != null) {
				System.out.println(cl.toString());
			}
			// own classloader
			System.out.println(TestBuddy.class.getClassLoader().toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// test internal invisible resource
		try {
			Class<?> inter = Class
					.forName("eclipse.example.policy.buddy.register1.internal.InternalResource");
			inter.newInstance();
		} catch (ClassNotFoundException e) {
			System.out.println("Not found internal resource");
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
