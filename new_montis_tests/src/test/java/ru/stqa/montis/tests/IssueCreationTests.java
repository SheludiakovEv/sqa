package ru.stqa.montis.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.montis.common.CommonFunctions;
import ru.stqa.montis.model.IssueData;

public class IssueCreationTests extends TestBase{

    @Test
    void canCreateIssue(){
        app.rest().createIssue(new IssueData()
                .withSummary(CommonFunctions.randomString(10))
                .withDescription(CommonFunctions.randomString(50))
                .withHProject(1L));
    }
}
