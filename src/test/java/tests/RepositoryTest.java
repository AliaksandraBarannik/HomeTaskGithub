package tests;

import actions.DashboardActions;
import actions.NewRepositoryActions;
import actions.RepositoryActions;
import api.GitHubApi;
import models.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RepositoryTest extends BaseTest{
    private static final Logger log = LoggerFactory.getLogger(RepositoryTest.class);
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
        log.info("Create new repository");
        Repository repository = Repository.builder().name(dataUtils.generateRandomString()).build();
        dashboardActions.clickNewRepositoryButton();
        newRepositoryActions.createNewRepository(repository);

        log.info("Verify if the repository was created");
        String actualRepositoryName = repositoryActions.getRepositoryName();
        Assert.assertEquals(actualRepositoryName, repository.getName(), "Repository name is not as expected");
    }

    @Test(groups = {"sanity"})
    public void commitChangesToRepositoryTest() {
        log.info("Commit changes to the repository");
        String latestCommitSha = gitHubApi.getLatestCommitSha();
        gitHubApi.commitFile(testConfig.getFileToCommitPath(), "Test commit");
        dashboardActions.openRepository(testConfig.getRepositoryName());

        log.info("Verify if the new commit was commited");
        String actualLatestCommit = repositoryActions.getTheLatestCommit();
        Assert.assertFalse(actualLatestCommit.contains(latestCommitSha.substring(0, 5)), "New commit wasn't commited");
    }
}
