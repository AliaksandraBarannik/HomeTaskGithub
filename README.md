## Environment Details
- Java Version: 22
- TestNG: Ensure TestNG is installed.
- Lombok: Ensure Lombok extension is installed.

## Local Run
Test run is parallelized by classes
1.Use testNG configuration by suite:
1.1.Select a suite (for example: sanitySuite.xml)
1.2.Use the following command in VM options field:
```-Ddriver.type=chrome -DheadlessMode=false -Dsecret=B9CE98F04918610A1F114CA398C22B62```

## Environment data:
Environment data is located in the test/resources/env.properties file

## Test data:
Test data is located in the test/resources/test.properties file