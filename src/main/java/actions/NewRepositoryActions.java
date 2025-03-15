package actions;

import models.Repository;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import pages.NewRepositoryPage;

public class NewRepositoryActions extends BaseActions{
    private NewRepositoryPage newRepositoryPage;

    public NewRepositoryActions(WebDriver driver) {
        super(driver);
        this.newRepositoryPage = new NewRepositoryPage(driver);
    }

    public void createNewRepository(Repository repository) {
        newRepositoryPage.repositoryName().sendKeys(repository.getName());
        if(repository.isPrivate()){
            newRepositoryPage.privateRadioButton().click();
        } else {
            newRepositoryPage.publicRadioButton().click();
        }
        if(repository.isAddReadmeFile()){
            newRepositoryPage.addReadmeFileCheckbox().click();
        }

        newRepositoryPage.submitButton().click();
    }
}
