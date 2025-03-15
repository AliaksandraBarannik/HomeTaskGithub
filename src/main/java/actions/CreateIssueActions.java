package actions;

import models.Issue;
import org.openqa.selenium.WebDriver;
import pages.CreateIssuePage;

public class CreateIssueActions extends BaseActions {
    private CreateIssuePage createIssuePage;

    public CreateIssueActions(WebDriver driver) {
        super(driver);
        this.createIssuePage = new CreateIssuePage(driver);
    }

    public void createIssue(Issue issue) {
        createIssuePage.titleInput().clear();
        createIssuePage.titleInput().sendKeys(issue.getTitle());
        if (issue.getDescription() != null) {
            createIssuePage.description().clear();
            createIssuePage.description().sendKeys(issue.getDescription());
        }
        createIssuePage.createButton().click();
    }
}
