/**
* @author Cliff Ng
* @version 1.0, 5/11/2019
*/

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;

public class TierOnePrepareSortByBest {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void timonPurchasesFlight() {

        //Log in
        driver.get("http://localhost:8080/");
        driver.manage().window().setSize(new Dimension(1536, 824));
        driver.findElement(By.linkText("Log In")).click();
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("arden");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("1443");
        driver.findElement(By.cssSelector("input:nth-child(5)")).click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Logged In

        //Search for flight to book
        driver.findElement(By.cssSelector("form:nth-child(1)")).click();
        driver.findElement(By.id("departure")).clear();
        driver.findElement(By.id("departure")).sendKeys("Paris - Charles De Gaulle, France");
        driver.findElement(By.cssSelector(".container:nth-child(2) > .row:nth-child(1)")).click();
        driver.findElement(By.id("arrival")).clear();
        driver.findElement(By.id("arrival")).sendKeys("Madrid, Spain");
        driver.findElement(By.cssSelector(".container:nth-child(3)")).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Filled out arrival, departure, date.

        //Go to Sliders preference page.
        driver.findElement(By.cssSelector("input:nth-child(1)")).click();



        //Slide all to 0
        WebElement priceSlider = driver.findElement(By.name("priceWeight"));
        WebElement durationSlider = driver.findElement(By.name("durationWeight"));
        WebElement ratingSlider = driver.findElement(By.name("ratingWeight"));
        WebElement noStopOversSlider = driver.findElement(By.name("noStopOversWeight"));

        for (int i = 1; i <= 4 ; i++) {
            priceSlider.sendKeys(Keys.ARROW_LEFT);
            durationSlider.sendKeys(Keys.ARROW_LEFT);
            ratingSlider.sendKeys(Keys.ARROW_LEFT);
            noStopOversSlider.sendKeys(Keys.ARROW_LEFT);
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Finished sliding

        //Go to show flights page
        driver.findElement(By.name("submit")).click();


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //All flights shown

        //Click on View a flight
        driver.findElement(By.cssSelector(".listingContainer:nth-child(5) .blue")).click();

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Viewing a flight

        //Click on Book a flight
        driver.findElement(By.cssSelector("form:nth-child(2) > input:nth-child(3)")).click();

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Booking a flight

        //Fill out payment details
        driver.findElement(By.name("nameOnCard")).click();
        driver.findElement(By.name("nameOnCard")).sendKeys("timon");
        driver.findElement(By.name("cardNumber")).click();
        driver.findElement(By.name("cardNumber")).sendKeys("12345");
        driver.findElement(By.name("cvv")).click();
        driver.findElement(By.name("cvv")).sendKeys("123");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Finish filling out payment details

        //Click on pay for ticket
        driver.findElement(By.cssSelector(".col-xs-12 > input")).click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Message shows paid for ticket

        //go to search again
        driver.findElement(By.cssSelector("input:nth-child(1)")).click();

        //logout
        driver.findElement(By.linkText("Log Out")).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //back at search page, but with flight bought.


    }


    @Test
    public void timonReviewsFlight() {
        //Log in
        driver.get("http://localhost:8080/");
        driver.manage().window().setSize(new Dimension(1536, 824));
        driver.findElement(By.linkText("Log In")).click();
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("arden");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("1443");
        driver.findElement(By.cssSelector("input:nth-child(5)")).click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Logged In

        //go to account
        driver.findElement(By.linkText("Account")).click();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Showing all bookings in account

        //click on review button of newly booked flight ticket
        //(In Account, flight tickets bought are ranked by newest bought, not by date. hence toppest is most recent)
        driver.findElement(By.cssSelector("button:nth-child(3)")).click();

        //Review flight
        WebElement serviceSlider = driver.findElement(By.name("serviceRating"));
        WebElement cateringSlider = driver.findElement(By.name("cateringRating"));
        WebElement comfortSlider = driver.findElement(By.name("comfortRating"));
        WebElement entertainmentSlider = driver.findElement(By.name("entertainmentRating"));
        WebElement cleanlinessSlider = driver.findElement(By.name("cleanlinessRating"));
        WebElement punctualitySlider = driver.findElement(By.name("punctualityRating"));
        WebElement recommendationSlider = driver.findElement(By.name("recommendationRating"));

        for (int i = 1; i <= 4 ; i++) {
            serviceSlider.sendKeys(Keys.ARROW_RIGHT);
            cateringSlider.sendKeys(Keys.ARROW_RIGHT);
            comfortSlider.sendKeys(Keys.ARROW_RIGHT);
            entertainmentSlider.sendKeys(Keys.ARROW_RIGHT);
            cleanlinessSlider.sendKeys(Keys.ARROW_RIGHT);
            punctualitySlider.sendKeys(Keys.ARROW_RIGHT);
            recommendationSlider.sendKeys(Keys.ARROW_RIGHT);
        }

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Finish reviewing flights

        //submit review
        driver.findElement(By.name("submitButtonValue")).click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //message shows review successful

        //go back to user account
        driver.findElement(By.cssSelector("input:nth-child(1)")).click();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Showing flight booking with review

        //logout
        driver.findElement(By.linkText("Log Out")).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //back at search page, but with flight reviewed.
    }
}
