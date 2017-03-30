package com.salmon.test.framework.helpers;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

public class WebDriverHelper extends EventFiringWebDriver {
    private static final Logger LOG = LoggerFactory.getLogger(WebDriverHelper.class);
    private static final Dimension BROWSER_WINDOW_SIZE = new Dimension(1280, 1024);
    private static RemoteWebDriver REAL_DRIVER = null;
    private static final Thread CLOSE_THREAD = new Thread(() -> REAL_DRIVER.quit());
    private static String BROWSER;
    private static String PLATFORM;
    private static String DRIVER_PATH;
    private static String DRIVER_ROOT_DIR;
    private static String FILE_SEPARATOR;
    private static String SELENIUM_HOST;
    private static String SELENIUM_PORT;
    private static String SELENIUM_REMOTE_URL;

    static {
        LoadProperties.loadRunConfigProps("/environment.properties");
        SELENIUM_HOST = System.getProperty("driverhost");
        SELENIUM_PORT = System.getProperty("driverport");
        FILE_SEPARATOR = System.getProperty("file.separator");
        PLATFORM = LoadProperties.getProps().getProperty("platform");
        BROWSER = LoadProperties.getProps().getProperty("browser");
        DRIVER_ROOT_DIR = LoadProperties.getProps().getProperty(
                "driver.root.dir");
        if (!DRIVER_ROOT_DIR.equals("DEFAULT_PATH")) {
            System.setProperty("webdriver.chrome.driver", getDriverPath());
            System.setProperty("webdriver.ie.driver", getDriverPath());
            System.setProperty("phantomjs.binary.path", getDriverPath());
        }
        try {
            if (BROWSER.equalsIgnoreCase("chrome")) {
                REAL_DRIVER = (RemoteWebDriver) startChromeDriver();
            } else if (BROWSER.equalsIgnoreCase("firefox")) {
                startFireFoxDriver();
            } else if (BROWSER.equalsIgnoreCase("iexplore")) {
                startIEDriver();
            } else if (BROWSER.equalsIgnoreCase("phantomjs")) {
                startPhantomJsDriver();
            } else {
                throw new IllegalArgumentException("Browser " + BROWSER + " or Platform "
                        + PLATFORM + " type not supported");
            }
        } catch (IllegalStateException e) {
            LOG.error("FIX path for driver.root.dir in pom.xml " + DRIVER_ROOT_DIR
                    + " Browser parameter " + BROWSER + " Platform parameter " + PLATFORM
                    + " type not supported");
        }
        REAL_DRIVER.manage().window().setSize(BROWSER_WINDOW_SIZE);
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    public WebDriverHelper() {
        super(REAL_DRIVER);
    }

    public static String getDriverPath() {
        if (BROWSER.equals("chrome") && PLATFORM.contains("win")) {
            DRIVER_PATH = DRIVER_ROOT_DIR + FILE_SEPARATOR + "chromedriver"
                    + FILE_SEPARATOR + PLATFORM + FILE_SEPARATOR
                    + "chromedriver.exe";
        } else if (BROWSER.equals("chrome") && PLATFORM.contains("linux")) {
            DRIVER_PATH = DRIVER_ROOT_DIR + FILE_SEPARATOR + "chromedriver"
                    + FILE_SEPARATOR + PLATFORM + FILE_SEPARATOR
                    + "chromedriver";
        } else if (BROWSER.equals("iexplore") && PLATFORM.contains("win")) {
            DRIVER_PATH = DRIVER_ROOT_DIR + FILE_SEPARATOR + "iedriver"
                    + FILE_SEPARATOR + PLATFORM + FILE_SEPARATOR
                    + "IEDriverServer.exe";
        } else if (BROWSER.equals("phantomjs") && PLATFORM.contains("linux")) {
            DRIVER_PATH = DRIVER_ROOT_DIR + FILE_SEPARATOR + "phantomjs"
                    + FILE_SEPARATOR + PLATFORM + FILE_SEPARATOR
                    + "phantomjs";
        } else if (BROWSER.equals("phantomjs") && PLATFORM.contains("win")) {
            DRIVER_PATH = DRIVER_ROOT_DIR + FILE_SEPARATOR + "phantomjs"
                    + FILE_SEPARATOR + PLATFORM + FILE_SEPARATOR
                    + "phantomjs.exe";
        }
        return DRIVER_PATH;
    }

    private static void startIEDriver() {
        DesiredCapabilities capabilities = getInternetExploreDesiredCapabilities();
        if (SELENIUM_HOST == null)
            REAL_DRIVER = new InternetExplorerDriver(capabilities);
        else {
            try {
                REAL_DRIVER = getRemoteWebDriver(capabilities);
            } catch (MalformedURLException e) {
                LOG.error(SELENIUM_REMOTE_URL + " Error " + e.getMessage());
            }
        }
    }

    private static void startFireFoxDriver() {
        DesiredCapabilities capabilities = getFireFoxDesiredCapabilities();
        if (SELENIUM_HOST == null) {
            ProfilesIni profilesIni = new ProfilesIni();
            FirefoxProfile profile = profilesIni.getProfile("SeleniumProfile");
            //fallback if there is no custom profile setup.
            if (profile == null) {
                LOG.warn("Custom firefox profile is not setup.");
                profile = profilesIni.getProfile("default");
            }
            profile.setPreference("browser.cache.memory.enable", false);
            REAL_DRIVER = new FirefoxDriver(profile);
        } else {
            try {
                LOG.info("Running on Selenium Server " + SELENIUM_HOST + " PORT " + SELENIUM_PORT);
                capabilities.setPlatform(Platform.WINDOWS);
                capabilities.setBrowserName("firefox");
                REAL_DRIVER = getRemoteWebDriver(capabilities);
            } catch (MalformedURLException e) {
                LOG.error(SELENIUM_REMOTE_URL + " Error " + e.getMessage());
            }/*finally {
                if(REAL_DRIVER != null){
                    REAL_DRIVER.quit();
                }
            }*/
        }
    }

    private static void startPhantomJsDriver() {
        DesiredCapabilities capabilities = getPhantomJsCapabilities();
        if (SELENIUM_HOST == null)
            REAL_DRIVER = new PhantomJSDriver(capabilities);
        else {
            try {
                REAL_DRIVER = getRemoteWebDriver(capabilities);
            } catch (MalformedURLException e) {
                LOG.error(SELENIUM_REMOTE_URL + " Error " + e.getMessage());
            }
        }
    }

    private static WebDriver startChromeDriver() {
        DesiredCapabilities capabilities = getChromeDesiredCapabilities();
        if (SELENIUM_HOST == null)
            REAL_DRIVER = new ChromeDriver(
                    ChromeDriverService.createDefaultService(), capabilities);
        else {
            try {
                REAL_DRIVER = getRemoteWebDriver(capabilities);
            } catch (MalformedURLException e) {
                LOG.error(SELENIUM_REMOTE_URL + " Error " + e.getMessage());
            }
        }
        REAL_DRIVER.manage().window().setSize(BROWSER_WINDOW_SIZE);
        return REAL_DRIVER;
    }

    private static RemoteWebDriver getRemoteWebDriver(DesiredCapabilities capabilities) throws MalformedURLException {
        SELENIUM_REMOTE_URL = "http://" + SELENIUM_HOST + ":" + SELENIUM_PORT + "/wd/hub";
        LOG.info(SELENIUM_REMOTE_URL + " Checking Selenium Remote URL");
        return new RemoteWebDriver(new URL(SELENIUM_REMOTE_URL), (capabilities));
    }

    private static DesiredCapabilities getChromeDesiredCapabilities() {
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.DRIVER, Level.OFF);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-b2c-security");
        chromeOptions.addArguments("--test-type");
        capabilities.setCapability("chrome.verbose", false);
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return capabilities;
    }

    private static DesiredCapabilities getFireFoxDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        return capabilities;
    }

    private static DesiredCapabilities getInternetExploreDesiredCapabilities() {
        System.setProperty("webdriver.ie.driver", getDriverPath());
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.DRIVER, Level.OFF);
        DesiredCapabilities capabilities = DesiredCapabilities
                .internetExplorer();
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
        capabilities
                .setCapability(
                        InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                        true);
        capabilities.setVersion("11");
        return capabilities;
    }

    private static DesiredCapabilities getPhantomJsCapabilities() {
        System.setProperty("phantomjs.binary.path", getDriverPath());
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.DRIVER, Level.OFF);
        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
        capabilities
                .setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, getDriverPath());
        return capabilities;
    }

    private static DesiredCapabilities getAppiumDesiredCapabilities() {
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "../../../apps/ApiDemos/bin");
        File app = new File(appDir, "ApiDemos-debug.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("platformVersion", "4.4");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.example.android.apis");
        capabilities.setCapability("appActivity", ".ApiDemos");
        return capabilities;
    }

    public static WebDriver getWebDriver() {
        return REAL_DRIVER;
    }

    @Override
    public void close() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException(
                    "You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
        }
        super.close();
    }
}
