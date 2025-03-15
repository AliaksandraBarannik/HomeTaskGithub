package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import static lombok.AccessLevel.PUBLIC;

@Builder(toBuilder = true)
@AllArgsConstructor
@Getter(PUBLIC)
@ToString
public class Repository {
    private String name;
    @Builder.Default
    private boolean isPrivate=false;
    @Builder.Default
    private boolean isAddReadmeFile=false;
}
