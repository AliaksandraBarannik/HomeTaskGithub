package actions;

import org.openqa.selenium.WebDriver;
import pages.RepositoryNavigationPanel;
import pages.RepositoryPage;

public class RepositoryActions extends BaseActions {
    private RepositoryPage repositoryPage;
    private RepositoryNavigationPanel navigationPanel;

    public RepositoryActions(WebDriver driver) {
        super(driver);
        this.repositoryPage = new RepositoryPage(driver);
        this.navigationPanel = new RepositoryNavigationPanel(driver);
    }

    public String getRepositoryName() {
        return repositoryPage.repositoryName().getText();
    }

    public String getTheLatestCommit() {
        return repositoryPage.commitLink().getText();
    }

    public void goToTab(String tabName) {
        navigationPanel.tab(tabName).click();
    }

    public int getTabCount(String tabName) {
        return Integer.parseInt(navigationPanel.tabCount(tabName).getText());
    }
}
