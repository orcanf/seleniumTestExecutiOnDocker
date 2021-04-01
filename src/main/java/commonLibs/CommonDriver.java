package commonLibs;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CommonDriver {

	private WebDriver driver;
	private int pageloadTimeout;
	private int elementDetectionTimeout;

	private String currentWorkingDriectory;

	public CommonDriver(String browserType) throws Exception {

		pageloadTimeout = 60;
		elementDetectionTimeout = 10;

		currentWorkingDriectory = System.getProperty("user.dir");

		if (browserType.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", currentWorkingDriectory + "/drivers/chromedriver");

			driver = new ChromeDriver();
		} 

		else if (browserType.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", currentWorkingDriectory + "/drivers/GeckoDriver.exe");

			driver = new EdgeDriver();
		}else if (browserType.equalsIgnoreCase("chrome-remote")){
			
			ChromeOptions chromeOptions = new ChromeOptions();
			URL remoteUrl = new URL("http://172.18.0.1:4444/wd/hub");
					
			driver = new RemoteWebDriver(remoteUrl, chromeOptions);
					
		}else if (browserType.equalsIgnoreCase("firefox-remote")){
			
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			URL remoteUrl = new URL("http://172.18.0.1:4444/wd/hub");
					
			driver = new RemoteWebDriver(remoteUrl, firefoxOptions);
					
		}

		else {
			throw new Exception("Invalid Browser Type");
		}

		driver.manage().window().maximize();

		driver.manage().deleteAllCookies();

	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setPageloadTimeout(int pageloadTimeout) {
		this.pageloadTimeout = pageloadTimeout;
	}

	public void setElementDetectionTimeout(int elementDetectionTimeout) {
		this.elementDetectionTimeout = elementDetectionTimeout;
	}

	public void navigateToFirstUrl(String url) throws Exception {

		driver.manage().timeouts().pageLoadTimeout(pageloadTimeout, TimeUnit.SECONDS);

		driver.manage().timeouts().implicitlyWait(elementDetectionTimeout, TimeUnit.SECONDS);

		url = url.trim();

		driver.get(url);

	}

	public String getTitle() throws Exception {
		String title = driver.getTitle();
		return title;
	}

	public String getCurrentUrl() throws Exception {

		return driver.getCurrentUrl();
	}

	public String getPageSource() throws Exception {

		return driver.getPageSource();
	}

	public void navigateToUrl(String url) throws Exception {

		url = url.trim();
		driver.navigate().to(url);

	}

	public void navigateForward() throws Exception {
		driver.navigate().forward();

	}

	public void navigateBackward() throws Exception {
		driver.navigate().back();

	}

	public void refresh() throws Exception {
		driver.navigate().refresh();

	}

	public void closeBrowser() throws Exception {

		if (driver != null) {
			driver.close();

		}
	}

	public void closeAllBrowsers() throws Exception {
		if (driver != null) {
			driver.quit();
		}

	}

}
