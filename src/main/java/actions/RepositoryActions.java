package actions;

import org.openqa.selenium.WebDriver;
import pages.RepositoryPage;

public class RepositoryActions extends BaseActions {
    private RepositoryPage repositoryPage;

    public RepositoryActions(WebDriver driver) {
        super(driver);
        this.repositoryPage = new RepositoryPage(driver);
    }

    public String getRepositoryName() {
        waiter.waitForVisibility(repositoryPage.repositoryName());
        return repositoryPage.repositoryName().getText();
    }

    public String getTheLatestCommit() {
        return repositoryPage.commitLink().getText();
    }
}
