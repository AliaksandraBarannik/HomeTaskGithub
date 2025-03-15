package actions;

import org.openqa.selenium.WebDriver;
import pages.DashboardPage;

public class DashboardActions extends BaseActions {
    private DashboardPage dashboardPage;

    public DashboardActions(WebDriver driver) {
        super(driver);
        this.dashboardPage = new DashboardPage(driver);
    }

    public void clickNewRepositoryButton() {
        dashboardPage.newRepositoryButton().click();
    }

    public void openRepository(String repositoryName) {
        dashboardPage.repositorySearch().clear();
        dashboardPage.repositorySearch().sendKeys(repositoryName);
        dashboardPage.repositoryLink(repositoryName).click();
    }
}
