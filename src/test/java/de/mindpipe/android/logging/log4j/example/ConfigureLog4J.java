/* 
   Copyright 2011 Rolf Kulemann

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/  
package de.mindpipe.android.logging.log4j.example;

import org.apache.log4j.Level;

import android.os.Environment;
import de.mindpipe.android.logging.log4j.LogConfigurator;

/**
 * Example how to to configure Log4J in Android.
 * Simply place a class like this in your Android applications classpath.
 * @author Rolf Kulemann
 */
public class ConfigureLog4J {
	public static void configure() {
		final LogConfigurator logConfigurator = new LogConfigurator();
		
		logConfigurator.setFileName(Environment.getExternalStorageDirectory() + "myapp.log");
		// Set the root log level
		logConfigurator.setRootLevel(Level.DEBUG);
		// Set log level of a specific logger
		logConfigurator.setLevel("org.apache", Level.ERROR);
		logConfigurator.configure();
	}
}