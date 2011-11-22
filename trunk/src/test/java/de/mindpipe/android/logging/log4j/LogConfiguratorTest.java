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
package de.mindpipe.android.logging.log4j;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * @author Rolf Kulemann
 *
 */
public class LogConfiguratorTest {

	@Test
	public void testConfigure() {
		final LogConfigurator logConfigurator = new LogConfigurator();
		final Logger log = Logger.getLogger(LogConfiguratorTest.class);
		
		logConfigurator.setUseLogCatAppender(false);
		logConfigurator.setImmediateFlush(true);
		logConfigurator.configure();
		
		logConfigurator.setLevel("de.mindpipe", Level.TRACE);
		//logConfigurator.setFileName(Environment.getExternalStorageDirectory() + "myapp.log");
		log.info("This message should be seen in log file an logcat");
	}

}
