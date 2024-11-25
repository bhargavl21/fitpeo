import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class FitPeoTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        // Setup WebDriver
        //System.setProperty("webdriver.chrome.driver", "path/to/chromedriver"); // Update with actual path
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testFitPeoSliderAndCPT() throws InterruptedException {
        // Step 1: Navigate to the FitPeo Homepage
        driver.get("https://www.fitpeo.com");

        // Step 2: Navigate to the Revenue Calculator Page
        WebElement revenueCalculatorLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/revenue-calculator']")));
        revenueCalculatorLink.click();

        // Step 3: Scroll Down to the Slider section
        WebElement sliderSection = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h4[normalize-space()='Medicare Eligible Patients']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", sliderSection);

        // Step 4: Adjust the Slider to 820
        WebElement rangeBar = driver.findElement(By.xpath("//span[@class='MuiSlider-thumb MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary MuiSlider-thumb MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary css-1sfugkh']//input"));
        int currentValue = Integer.parseInt(rangeBar.getAttribute("aria-valuenow"));
        Actions actions = new Actions(driver);
        int targetValue = 820;
        
        // Adjust the slider using arrow keys
        int value = targetValue - 200; // Adjust this as per your needs
        for (int i = 1; i <= value; i++) {
            rangeBar.sendKeys(Keys.ARROW_RIGHT);
            currentValue = Integer.parseInt(rangeBar.getAttribute("aria-valuenow"));
            //System.out.println("Current Value: " + currentValue);

            // Break if the desired value is reached
            if (currentValue == targetValue) {
                System.out.println("Reached target value: " + targetValue);
                break;
            }
        }

        // Verify if the slider value is 820
        WebElement sliderValueField = driver.findElement(By.xpath("//input[@type='number']"));
        Assert.assertEquals("820", sliderValueField.getAttribute("value"));

        // Step 5: Update the Text Field to 560
        sliderValueField.clear();
        sliderValueField.click();
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].value='560';", sliderValueField);
        jsExecutor.executeScript("arguments[0].dispatchEvent(new Event('input'));", sliderValueField);
        jsExecutor.executeScript("arguments[0].dispatchEvent(new Event('change'));", sliderValueField);
        jsExecutor.executeScript("arguments[0].dispatchEvent(new Event('blur'));", sliderValueField);

        // Wait for UI to update
        String updatedValue = sliderValueField.getAttribute("value");
        System.out.println("Updated slider value: " + updatedValue);
        Assert.assertEquals("560", updatedValue);

        // Step 6: Validate Slider Value is now 560
        Thread.sleep(1000); // Optional, adjust timing if necessary

        // Step 7: Scroll down and select CPT Codes
        WebElement cptSection = driver.findElement(By.xpath("//div[@class='MuiBox-root css-1p19z09']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", cptSection);

        // Select specific CPT checkboxes
        String[] cptCodes = {"CPT-99091", "CPT-99453", "CPT-99454", "CPT-99474"};
        for (String code : cptCodes) {
            WebElement checkboxInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//p[contains(text(), '" + code + "')]/following-sibling::label//input")
            ));

            // Ensure checkbox is not selected, then click
            if (!checkboxInput.isSelected()) {
                checkboxInput.click();
                System.out.println("Checkbox for " + code + " selected.");
            } else {
                System.out.println("Checkbox for " + code + " was already selected.");
            }

            // Short delay to avoid interacting too quickly
            Thread.sleep(500);
        }

        // Step 8: Validate Total Recurring Reimbursement
        WebElement totalReimbursement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='MuiBox-root css-rfiegf']//p[contains(text(), 'Total Recurring Reimbursement ')])[1]/p")));
        Assert.assertEquals("$110700", totalReimbursement.getText());

        System.out.println("All test cases passed successfully.");
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
