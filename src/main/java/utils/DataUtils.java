package utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
@Slf4j
public class DataUtils {

    public String generateRandomString() {
        return new RandomStringUtils().nextAlphabetic(10);
    }
}
