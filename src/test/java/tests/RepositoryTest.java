package tests;

import actions.DashboardActions;
import actions.NewRepositoryActions;
import actions.RepositoryActions;
import api.GitHubApi;
import models.Repository;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RepositoryTest extends BaseTest{
    private RepositoryActions repositoryActions;
    private NewRepositoryActions newRepositoryActions;
    private DashboardActions dashboardActions;
    private GitHubApi gitHubApi;

    @BeforeMethod(alwaysRun = true)
    public void setupMethod() {
        gitHubApi = new GitHubApi(envConfig.getUserName(), testConfig.getRepositoryName(), envConfig.getAccessToken());
        repositoryActions = new RepositoryActions(getDriver());
        newRepositoryActions = new NewRepositoryActions(getDriver());
        dashboardActions = new DashboardActions(getDriver());
    }

    @Test(groups = {"sanity"})
    public void checkRepositoryCreationTest() {
        Repository repository = Repository.builder().name(dataUtils.generateRandomString()).build();
        dashboardActions.clickNewRepositoryButton();
        newRepositoryActions.createNewRepository(repository);
        String actualRepositoryName = repositoryActions.getRepositoryName();
        Assert.assertEquals(actualRepositoryName, repository.getName(), "Repository name is not as expected");
    }

    @Test(groups = {"sanity"})
    public void commitChangesToRepositoryTest() {
        String latestCommitSha = gitHubApi.getLatestCommitSha();
        gitHubApi.commitFile(testConfig.getFileToCommitPath(), "Test commit");
        dashboardActions.openRepository(testConfig.getRepositoryName());
        String actualLatestCommit = repositoryActions.getTheLatestCommit();
        Assert.assertFalse(actualLatestCommit.contains(latestCommitSha.substring(0, 5)), "New commit wasn't commited");
    }
}
