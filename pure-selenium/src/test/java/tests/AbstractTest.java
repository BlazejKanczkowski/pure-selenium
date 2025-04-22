package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class AbstractTest {

    protected WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(AbstractTest.class);

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (browser.equalsIgnoreCase("firefox")) {
            capabilities.setBrowserName("firefox");
        } else {
            capabilities.setBrowserName("chrome");
        }

        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            File destFile = new File("screenshots/" + result.getName() + "_" + timestamp + ".png");

            try {
                FileUtils.copyFile(srcFile, destFile);
                logger.info("Screenshot saved to: {}", destFile.getAbsolutePath());
            } catch (IOException e) {
                logger.error("Failed to save screenshot", e);
            }
        }

        if (driver != null) {
            driver.quit();
        }
    }
}