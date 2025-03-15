package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NewRepositoryPage extends BasePage {
    private final String REPOSITORY_NAME = "//input[contains(@aria-describedby,'RepoNameInput')]";
    private final String ADD_README_FILE_CHECKBOX = "//react-app//input[@type='checkbox']";
    private final String SUBMIT_BUTTON = "//form//button[@type='submit']";
    private final String PUBLIC_RADIO_BUTTON = "//input[@value='public']";
    private final String PRIVATE_RADIO_BUTTON = "//input[@value='private']";
    private final String INPUT_VALIDATION = "//span[contains(@data-validation-status,'success')]";

    public NewRepositoryPage(WebDriver driver) {
        super(driver);
    }

    public WebElement repositoryName() {
        return driver.findElement(By.xpath(REPOSITORY_NAME));
    }

    public WebElement inputValidation() {
        waiter.waitForVisibility(By.xpath(INPUT_VALIDATION));
        return driver.findElement(By.xpath(INPUT_VALIDATION));
    }

    public WebElement addReadmeFileCheckbox() {
        return driver.findElement(By.xpath(ADD_README_FILE_CHECKBOX));
    }

    public WebElement submitButton() {
        return driver.findElement(By.xpath(SUBMIT_BUTTON));
    }

    public WebElement publicRadioButton() {
        return driver.findElement(By.xpath(PUBLIC_RADIO_BUTTON));
    }

    public WebElement privateRadioButton() {
        return driver.findElement(By.xpath(PRIVATE_RADIO_BUTTON));
    }
}
