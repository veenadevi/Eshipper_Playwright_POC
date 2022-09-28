package com.eshipper.pageFactory;

import com.google.gson.JsonObject;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.util.Properties;

public class PlaywrightFactory {

	Playwright playwright;
	Browser browser;
	BrowserContext browserContext;
	Page page;
	Properties prop;

	private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
	private static ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
	private static ThreadLocal<Page> tlPage = new ThreadLocal<>();
	private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();

	public static Playwright getPlaywright() {
		return tlPlaywright.get();
	}

	public static Browser getBrowser() {
		return tlBrowser.get();
	}

	public static BrowserContext getBrowserContext() {
		return tlBrowserContext.get();
	}

	public static Page getPage() {
		return tlPage.get();
	}

	public Page initBrowser(Properties prop) throws UnsupportedEncodingException {

		String browserName = prop.getProperty("browser").trim();
		System.out.println("browser name is : " + browserName);

		String serverName = prop.getProperty("server").trim();
		System.out.println("Test case run on : " + serverName);
		tlPlaywright.set(Playwright.create());

		String CLOUD_USERNAME="";
		String CLOUD_PASSWORD="";
		if(serverName.equalsIgnoreCase("local")){


		switch (browserName.toLowerCase()) {
		case "chromium":
			//browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			tlBrowser.set(getPlaywright().chromium().launch(new LaunchOptions().setHeadless(false)));
			break;
		case "firefox":
			//browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
			tlBrowser.set(getPlaywright().firefox().launch(new LaunchOptions().setHeadless(false)));
			break;
		case "safari":
			//browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
			tlBrowser.set(getPlaywright().webkit().launch(new LaunchOptions().setHeadless(false)));
			break;
		case "chrome":
			//browser = playwright.chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false));
			tlBrowser.set(getPlaywright().chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false)));
			break;

		default:
			System.out.println("please pass the right browser name......");
			break;
		}
		}
		 else if (serverName.equalsIgnoreCase("lambdatest")) {
			JsonObject capabilities = new JsonObject();
			JsonObject ltOptions = new JsonObject();
			String user = CLOUD_USERNAME;
			String accessKey = CLOUD_PASSWORD;
			capabilities.addProperty("browsername", "Chrome"); // Browsers allowed: `Chrome`, `MicrosoftEdge`, `pw-chromium`, `pw-firefox` and `pw-webkit`
			capabilities.addProperty("browserVersion", "latest");
			ltOptions.addProperty("platform", "Windows 10");
			ltOptions.addProperty("name", "Playwright Test");
			ltOptions.addProperty("build", "Playwrite Testing in Java");
			ltOptions.addProperty("user", user);
			ltOptions.addProperty("accessKey", accessKey);
			capabilities.add("LT:Options", ltOptions);

			//BrowserType chromium = getPlaywright().chromium();
			String caps = URLEncoder.encode(capabilities.toString(), "utf-8");
			String cdpUrl = "wss://cdp.lambdatest.com/playwright?capabilities=" + capabilities;
			Browser browser = getPlaywright().chromium().connect(cdpUrl);
			tlBrowser.set(browser);
		}
		//Record Video
		tlBrowserContext.set(getBrowser().newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get("myvideos"))));
		//Set Browser size
		 tlBrowserContext.set(getBrowser().newContext(new Browser.NewContextOptions().setViewportSize(1496,1080)));
		 tlPage.set(getBrowserContext().newPage());
		getPage().navigate(prop.getProperty("url").trim());
		return getPage();

	}

	/**
	 * this method is used to initialize the properties from config file
	 */
	public Properties init_prop() {

		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			prop = new Properties();
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

	}
	
	/**
	 * take screenshot
	 * 
	 */
	
	public static String takeScreenshot() {
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";

		getPage().screenshot(new Page.ScreenshotOptions()
				  .setPath(Paths.get(path))
				  .setFullPage(true));
		return path;
	}

}
