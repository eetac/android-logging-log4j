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

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Rolf Kulemann
 *
 */
public class LogConfiguratorTest {
	private LogConfigurator logConfigurator;
	
	@Before
	public void setUp() {
		logConfigurator = new LogConfigurator();
	}
	
	@Test
	public void testConfigure() {
		final Logger log = Logger.getLogger(LogConfiguratorTest.class);
		final String message = "This message should be seen in log file and logcat";
		final String messageNot = "This message should NOT be seen in log file and logcat";
		
		// deactivate LogCatAppender, since otherwise we get exception while logging in a non Android environment
		logConfigurator.setUseLogCatAppender(false);
		logConfigurator.setImmediateFlush(true);
		logConfigurator.setLevel("de.mindpipe", Level.TRACE);
		logConfigurator.configure();
		
		log.info(message);
		
		assertLogFileExists();
		assertLogContains(message);
		
		logConfigurator.setLevel("de.mindpipe", Level.ERROR);
		log.info(messageNot);
		assertLogNotContains(messageNot);
	}

	private void assertLogFileExists() {
		assertTrue(new File(logConfigurator.getFileName()).exists());
	}
	
	private void assertLogContains(final String string) {
		final String logFileContents = logFileToString();
		assertTrue(logFileContents.contains(string));
	}
	
	private void assertLogNotContains(final String string) {
		final String logFileContents = logFileToString();
		assertFalse(logFileContents.contains(string));
	}
	
	private String logFileToString() {
		final String logFileContents;
		
		try {
			logFileContents = FileUtils.readFileToString(new File(logConfigurator.getFileName()));
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
		
		return logFileContents;
	}
}
