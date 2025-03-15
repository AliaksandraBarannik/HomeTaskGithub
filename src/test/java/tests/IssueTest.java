package tests;

import actions.*;
import enums.RepositoryTabs;
import models.Issue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class IssueTest extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(IssueTest.class);
    private CreateIssueActions createIssueActions;
    private RepositoryActions repositoryActions;
    private DashboardActions dashboardActions;
    private IssueActions issueActions;

    @BeforeMethod(alwaysRun = true)
    public void setupMethod() {
        createIssueActions = new CreateIssueActions(getDriver());
        repositoryActions = new RepositoryActions(getDriver());
        dashboardActions = new DashboardActions(getDriver());
        issueActions = new IssueActions(getDriver());
    }

    @Test(groups = {"sanity"})
    public void createIssueTest() {
        log.info("Go to 'Issues' tab for " + testConfig.getRepositoryName() + " repository");
        dashboardActions.openRepository(testConfig.getRepositoryName());
        String tabName = RepositoryTabs.ISSUES.getValue();
        repositoryActions.goToTab(tabName);
        int currentIssueCount = repositoryActions.getTabCount(tabName);

        log.info("Create new issue");
        Issue issue = Issue.builder().title(dataUtils.generateRandomString()).build();
        issueActions.openNewIssuePage();
        createIssueActions.createIssue(issue);

        log.info("Check that issue was created");
        repositoryActions.refreshPage();
        int newIssueCount = repositoryActions.getTabCount(tabName);
        Assert.assertEquals(newIssueCount, currentIssueCount + 1, "Issue was not created");
    }
}
