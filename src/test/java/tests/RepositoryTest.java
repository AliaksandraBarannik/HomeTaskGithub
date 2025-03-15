package tests;

import actions.DashboardActions;
import actions.NewRepositoryActions;
import actions.RepositoryActions;
import api.GitHubApi;
import models.Repository;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RepositoryTest extends BaseTest{
    private RepositoryActions repositoryActions;
    private NewRepositoryActions newRepositoryActions;
    private DashboardActions dashboardActions;
    private GitHubApi gitHubApi;

    @BeforeClass
    public void setupRepositoryTest() {
        repositoryActions = new RepositoryActions(driver);
        newRepositoryActions = new NewRepositoryActions(driver);
        dashboardActions = new DashboardActions(driver);
        gitHubApi = new GitHubApi();
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
        String latestCommitSha = gitHubApi.getLatestCommitSha(envConfig.getUserName(), testConfig.getRepositoryName(), envConfig.getAccessToken());
        gitHubApi.commitFile(envConfig.getUserName(), testConfig.getRepositoryName(), envConfig.getAccessToken(), testConfig.getFileToCommitPath(), latestCommitSha);
        dashboardActions.openRepository(testConfig.getRepositoryName());
        String actualLatestCommit = repositoryActions.getTheLatestCommit();
        Assert.assertEquals(actualLatestCommit, latestCommitSha, "Latest commit is not as expected");
    }
}
