package enums;

import lombok.Getter;

@Getter
public enum RepositoryTabs {
    CODE("Code"),
    ISSUES("Issues"),
    PULL_REQUESTS("Pull requests");

    private String value;

    RepositoryTabs(String value) {
        this.value = value;
    }
}
