package actions;

import models.Repository;
import org.openqa.selenium.WebDriver;
import pages.NewRepositoryPage;

public class NewRepositoryActions extends BaseActions {
    private NewRepositoryPage newRepositoryPage;

    public NewRepositoryActions(WebDriver driver) {
        super(driver);
        this.newRepositoryPage = new NewRepositoryPage(driver);
    }

    public void createNewRepository(Repository repository) {
        newRepositoryPage.repositoryName().sendKeys(repository.getName());
        newRepositoryPage.inputValidation().click();
        setPrivacy(repository.isPrivate());
        if (repository.isAddReadmeFile()) {
            newRepositoryPage.addReadmeFileCheckbox().click();
        }
        newRepositoryPage.submitButton().click();
    }

    public void setPrivacy(boolean isPrivate) {
        if (isPrivate) {
            newRepositoryPage.privateRadioButton().click();
        } else {
            newRepositoryPage.publicRadioButton().click();
        }
    }
}
