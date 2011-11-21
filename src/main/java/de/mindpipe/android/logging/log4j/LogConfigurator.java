/* 
   Copyright 2011 Rolf Kulemann, Pascal Bockhorn

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

import java.io.IOException;

import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

/**
 * Configures the Log4j logging framework. See <a href=
 * "http://logging.apache.org/log4j/1.2/apidocs/org/apache/log4j/PatternLayout.html"
 * >Patterns</a> for pattern layout.
 *
 * @author Rolf Kulemann
 */
public class LogConfigurator {
	private Level level = Level.DEBUG;
	private String filePattern = "%d - [%p::%c::%C] - %m%n";
	private String logCatPattern = "%m%n";
	private String fileName = "android-log4j.log";
	private int maxBackupSize = 5;
	private long maxFileSize = 512 * 1024;
	private boolean immediateFlush = false;
	private boolean useLogCatAppender = true;
	private boolean useFileAppender = true;

	public LogConfigurator() {
	}

	public LogConfigurator(final String fileName) {
		setFileName(fileName);
	}

	public LogConfigurator(final String fileName, final Level level) {
		this(fileName);
		setLevel(level);
	}

	public LogConfigurator(final String fileName, final Level level,
			final String pattern) {
		this(fileName);
		setLevel(level);
		setFilePattern(pattern);
	}

	public LogConfigurator(final String fileName, final int maxBackupSize,
			final long maxFileSize, final String pattern, final Level level) {
		this(fileName, level, pattern);
		setMaxBackupSize(maxBackupSize);
		setMaxFileSize(maxFileSize);
	}

	public void configure() {
		final Logger root = Logger.getRootLogger();
		
		if(isUseFileAppender()) {
			configureFileAppender();
		}
		
		if(isUseLogCatAppender()) {
			configureLogCatAppender();
		}
		
		root.setLevel(getLevel());
	}

	private void configureFileAppender() {
		final Logger root = Logger.getRootLogger();
		final RollingFileAppender rollingFileAppender;
		final Layout fileLayout = new PatternLayout(getFilePattern());

		try {
			rollingFileAppender = new RollingFileAppender(fileLayout, getFileName());
		} catch (final IOException e) {
			throw new RuntimeException("Exception configuring log system", e);
		}

		rollingFileAppender.setMaxBackupIndex(getMaxBackupSize());
		rollingFileAppender.setMaximumFileSize(getMaxFileSize());
		rollingFileAppender.setImmediateFlush(isImmediateFlush());

		root.addAppender(rollingFileAppender);
		root.setLevel(getLevel());
	}
	
	private void configureLogCatAppender() {
		final Logger root = Logger.getRootLogger();
		final Layout logCatLayout = new PatternLayout(getLogCatPattern());
		final LogCatAppender logCatAppender = new LogCatAppender(logCatLayout);

		root.addAppender(logCatAppender);
	}
	
	public Level getLevel() {
		return level;
	}

	public void setLevel(final Level level) {
		this.level = level;
	}

	public String getFilePattern() {
		return filePattern;
	}

	public void setFilePattern(final String filePattern) {
		this.filePattern = filePattern;
	}

	public String getLogCatPattern() {
		return logCatPattern;
	}

	public void setLogCatPattern(final String logCatPattern) {
		this.logCatPattern = logCatPattern;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}

	public int getMaxBackupSize() {
		return maxBackupSize;
	}

	public void setMaxBackupSize(final int maxBackupSize) {
		this.maxBackupSize = maxBackupSize;
	}

	public long getMaxFileSize() {
		return maxFileSize;
	}

	public void setMaxFileSize(final long maxFileSize) {
		this.maxFileSize = maxFileSize;
	}

	public boolean isImmediateFlush() {
		return immediateFlush;
	}

	public void setImmediateFlush(final boolean immediateFlush) {
		this.immediateFlush = immediateFlush;
	}

	/**
	 * @return the useFileAppender
	 */
	public boolean isUseFileAppender() {
		return useFileAppender;
	}

	/**
	 * @param useFileAppender the useFileAppender to set
	 */
	public void setUseFileAppender(final boolean useFileAppender) {
		this.useFileAppender = useFileAppender;
	}

	/**
	 * @return the useLogCatAppender
	 */
	public boolean isUseLogCatAppender() {
		return useLogCatAppender;
	}

	/**
	 * @param useLogCatAppender the useLogCatAppender to set
	 */
	public void setUseLogCatAppender(final boolean useLogCatAppender) {
		this.useLogCatAppender = useLogCatAppender;
	}
}
