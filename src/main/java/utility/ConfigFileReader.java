package utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
	private Properties properties;
	private final String propertyFilePath = "configs//Configuration.properties";

	public ConfigFileReader() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}
	}
	
	public String getReportConfigPath() {
		String reportConfigPath = properties.getProperty("reportConfigPath");
		if (reportConfigPath != null)
			return reportConfigPath;
		else
			throw new RuntimeException(
					"Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");
	}
	
	public String getDriverPath() {
		String driverPath = properties.getProperty("driverPath");
		if (driverPath != null)
			return driverPath;
		else
			throw new RuntimeException("driverPath not specified in the Configuration.properties file.");
	}
	
	public String getUrl() {
		String url = properties.getProperty("Url");
		if (url != null)
			return url;
		else
			throw new RuntimeException("Url not specified in the Configuration.properties file.");
	}
	
	public String getPlatformName() {
		String platformName = properties.getProperty("PLATFORM_NAME");
		if (platformName != null)
			return platformName;
		else
			throw new RuntimeException("Platform Name not specified in the Configuration.properties file.");
	}
	
	public String getPlatformVersion() {
		String platformVersion = properties.getProperty("PLATFORM_VERSION");
		if (platformVersion != null)
			return platformVersion;
		else
			throw new RuntimeException("Platform Version not specified in the Configuration.properties file.");
	}
	public String getDeviceName() {
		String deviceName = properties.getProperty("DEVICE_NAME");
		if (deviceName != null)
			return deviceName;
		else
			throw new RuntimeException("Device Name not specified in the Configuration.properties file.");
	}
	
	public String getApkName() {
		String deviceName = properties.getProperty("APK_FILE_NAME");
		if (deviceName != null)
			return deviceName;
		else
			throw new RuntimeException("APK file Name not specified in the Configuration.properties file.");
	}
	
	public String getAppPackage() {
		String deviceName = properties.getProperty("APP_PACKAGE");
		if (deviceName != null)
			return deviceName;
		else
			throw new RuntimeException("App Package Name not specified in the Configuration.properties file.");
	}
	
	public String getAppActivity() {
		String deviceName = properties.getProperty("APP_ACTIVITY");
		if (deviceName != null)
			return deviceName;
		else
			throw new RuntimeException("App Activity Name not specified in the Configuration.properties file.");
	}
	
	public String getTestDataSheetName() {
		String deviceName = properties.getProperty("TESTDATA_SHEET_NAME");
		if (deviceName != null)
			return deviceName;
		else
		throw new RuntimeException("Test Data Sheet Name not specified in the Configuration.properties file.");
	}
	
	public String getDbDataSheetName() {
		String deviceName = properties.getProperty("DBDATA_SHEET_NAME");
		if (deviceName != null)
			return deviceName;
		else
		throw new RuntimeException("Db Data/Query Sheet Name not specified in the Configuration.properties file.");
	}

	public String getWebContextName() {
		String deviceName = properties.getProperty("WEB_CONTEXT_NAME");
		if (deviceName != null)
			return deviceName;
		else
			throw new RuntimeException("Context Name not specified in the Configuration.properties file.");
	}
	
	public String getNativeContextName() {
		String deviceName = properties.getProperty("NATIVE_CONTEXT_NAME");
		if (deviceName != null)
			return deviceName;
		else
			throw new RuntimeException("Context Name not specified in the Configuration.properties file.");
	}
	
	public String getDBUsername() {
		String deviceName = properties.getProperty("DB_USERNAME");
		if (deviceName != null)
			return deviceName;
		else
			throw new RuntimeException("Databae Username Name not specified in the Configuration.properties file.");
	}
	
	public String getDBPassword() {
		String deviceName = properties.getProperty("DB_PASSWORD");
		if (deviceName != null)
			return deviceName;
		else
			throw new RuntimeException("Databae Password not specified in the Configuration.properties file.");
	}
	
	public String getDBConnectionURL() {
		String deviceName = properties.getProperty("DB_CONNECTION_URL");
		if (deviceName != null)
			return deviceName;
		else
			throw new RuntimeException("Database Connection URL not specified in the Configuration.properties file.");
	}
	
	public String getEmailUsername() {
		String deviceName = properties.getProperty("EMAIL_USERNAME");
		if (deviceName != null)
			return deviceName;
		else
			throw new RuntimeException("Email username not specified in the Configuration.properties file.");
	}
	
	public String getEmailPassword() {
		String deviceName = properties.getProperty("EMAIL_PASSWORD");
		if (deviceName != null)
			return deviceName;
		else
			throw new RuntimeException("Email password not specified in the Configuration.properties file.");
	}
	
	

}
