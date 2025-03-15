package actions;

import models.Issue;
import org.openqa.selenium.WebDriver;
import pages.IssuePage;

public class IssueActions extends BaseActions {
    private IssuePage issuePage;

    public IssueActions(WebDriver driver) {
        super(driver);
        this.issuePage = new IssuePage(driver);
    }

    public void openNewIssuePage() {
        issuePage.newIssueButton().click();
    }
}
