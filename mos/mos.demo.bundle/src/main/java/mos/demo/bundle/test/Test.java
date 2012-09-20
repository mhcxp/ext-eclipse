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
package mos.demo.bundle.test;

import java.io.File;

/**
 * @author dzh
 * @version 1.0 Aug 27, 2012 11:17:55 AM
 */
public class Test {

	public static void main(String[] args) {
		File file = new File("${user.temp}", "test1.png");
		file = new File("/home/dzh/temp", "test1.png");
		if (file.exists())
			System.out.println("File existed!");
	}

}
