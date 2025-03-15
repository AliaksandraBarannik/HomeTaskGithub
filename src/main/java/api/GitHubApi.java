package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.Collections;

import static api.EndPoints.*;

public class GitHubApi {
    private final String username;
    private final String repoName;
    private final String accessToken;

    public GitHubApi(String username, String repoName, String accessToken) {
        this.username = username;
        this.repoName = repoName;
        this.accessToken = accessToken;
    }

    public String getLatestCommitSha() {
        Response response = sendGetRequest(MAIN_BRANCH_HEAD);
        return response.jsonPath().getString("object.sha");
    }

    public void commitFile(String filePath, String content) {
        String latestCommitSha = getLatestCommitSha();
        String blobSha = createBlob(content);
        String treeSha = createTree(filePath, blobSha);
        String commitSha = createCommit(treeSha, latestCommitSha);
        updateBranch(commitSha);
    }

    private Response sendGetRequest(String endpoint) {
        return RestAssured.given()
                .auth().oauth2(accessToken)
                .get(GITHUB_API_URL + username + "/" + repoName + endpoint);
    }

    private Response sendPostRequest(String endpoint, JSONObject body) {
        return RestAssured.given()
                .auth().oauth2(accessToken)
                .header("Content-Type", "application/json")
                .body(body.toString())
                .post(GITHUB_API_URL + username + "/" + repoName + endpoint);
    }

    private String createBlob(String content) {
        JSONObject blobObject = new JSONObject()
                .put("content", content)
                .put("encoding", "utf-8");

        Response response = sendPostRequest(BLOBS, blobObject);
        return extractSha(response, "blob creation");
    }

    private String createTree(String filePath, String blobSha) {
        JSONObject treeObject = new JSONObject()
                .put("tree", Collections.singletonList(new JSONObject()
                        .put("path", filePath)
                        .put("mode", "100644")
                        .put("type", "blob")
                        .put("sha", blobSha)));

        Response response = sendPostRequest(TREES, treeObject);
        return extractSha(response, "tree creation");
    }

    private String createCommit(String treeSha, String latestCommitSha) {
        JSONObject commitObject = new JSONObject()
                .put("message", "Automated commit via GitHub API")
                .put("parents", Collections.singletonList(latestCommitSha))
                .put("tree", treeSha);

        Response response = sendPostRequest(COMMITS, commitObject);
        return extractSha(response, "commit creation");
    }

    private void updateBranch(String commitSha) {
        JSONObject updateReferenceObject = new JSONObject()
                .put("sha", commitSha)
                .put("force", true);

        Response response = sendPostRequest(MAIN_BRANCH_HEAD, updateReferenceObject);
        System.out.println("Branch updated: " + response.asString());
    }

    private String extractSha(Response response, String action) {
        String sha = response.jsonPath().getString("sha");
        if (sha == null) {
            throw new RuntimeException("Failed " + action + ": " + response.asString());
        }
        return sha;
    }
}