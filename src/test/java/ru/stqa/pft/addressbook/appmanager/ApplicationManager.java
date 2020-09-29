package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    WebDriver driver;

    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        if(browser.equals(BrowserType.CHROME)){
            System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
            ChromeOptions chromeOptions = new ChromeOptions();
            driver = new ChromeDriver(chromeOptions);
        } else if(browser.equals(BrowserType.FIREFOX)){
            driver = new FirefoxDriver();
        } else if(browser.equals(BrowserType.IE)){
            driver = new InternetExplorerDriver();
        }

        //driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get("http://localhost:8080/addressbook/");
        driver.manage().window().maximize();
        groupHelper = new GroupHelper(driver);
        navigationHelper = new NavigationHelper(driver);
        sessionHelper = new SessionHelper(driver);
        contactHelper = new ContactHelper(driver);
        sessionHelper.login("admin", "secret");
    }

    public void stop() {
        driver.quit();
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }
}
