package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.Collections;

public class GitHubApi {

    public String getLatestCommitSha(String username, String repoName, String accessToken) {
        Response response = RestAssured.given()
                .auth().oauth2(accessToken)
                .get("https://api.github.com/repos/" + username + "/" + repoName + "/git/refs/heads/main");

        return response.jsonPath().getString("object.sha");
    }

    public void commitFile(String username, String repoName, String accessToken, String filePath, String latestCommitSha) {
        String commitMessage = "Automated commit via GitHub API";
        String fileContent = "This is a new line in the file";

        JSONObject blobObject = new JSONObject();
        blobObject.put("content", fileContent);
        blobObject.put("encoding", "utf-8");

        Response blobResponse = RestAssured.given()
                .auth().oauth2(accessToken)
                .header("Content-Type", "application/json")
                .body(blobObject.toString())
                .post("https://api.github.com/repos/" + username + "/" + repoName + "/git/blobs");

        String blobSha = blobResponse.jsonPath().getString("sha");

        if (blobSha == null) {
            throw new RuntimeException("Failed to create blob: " + blobResponse.asString());
        }

        JSONObject treeObject = new JSONObject();
        treeObject.put("tree", Collections.singletonList(new JSONObject()
                .put("path", filePath)
                .put("mode", "100644")
                .put("type", "blob")
                .put("sha", blobSha)));

        Response treeResponse = RestAssured.given()
                .auth().oauth2(accessToken)
                .header("Content-Type", "application/json")
                .body(treeObject.toString())
                .post("https://api.github.com/repos/" + username + "/" + repoName + "/git/trees");

        String treeSha = treeResponse.jsonPath().getString("sha");

        if (treeSha == null) {
            throw new RuntimeException("Failed to create tree: " + treeResponse.asString());
        }

        JSONObject commitObject = new JSONObject();
        commitObject.put("message", commitMessage);
        commitObject.put("parents", Collections.singletonList(latestCommitSha));
        commitObject.put("tree", treeSha);

        Response commitResponse = RestAssured.given()
                .auth().oauth2(accessToken)
                .header("Content-Type", "application/json")
                .body(commitObject.toString())
                .post("https://api.github.com/repos/" + username + "/" + repoName + "/git/commits");

        String commitSha = commitResponse.jsonPath().getString("sha");

        if (commitSha == null) {
            throw new RuntimeException("Failed to create commit: " + commitResponse.asString());
        }

        JSONObject updateReferenceObject = new JSONObject();
        updateReferenceObject.put("sha", commitSha);
        updateReferenceObject.put("force", true);

        Response updateRefResponse = RestAssured.given()
                .auth().oauth2(accessToken)
                .header("Content-Type", "application/json")
                .body(updateReferenceObject.toString())
                .post("https://api.github.com/repos/" + username + "/" + repoName + "/git/refs/heads/main");

        System.out.println(updateRefResponse.asString());
    }
}
