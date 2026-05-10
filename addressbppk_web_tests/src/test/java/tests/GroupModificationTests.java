package tests;

import model.GroupDate;
import org.junit.jupiter.api.Test;

public class GroupModificationTests  extends TestBase {
    @Test
    void canModifyGroup(){
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupDate("ff", "ff", "ff"));
        }
        app.groups().modifyGroup(new GroupDate().withName("modify name"));
    }
}
