package tests.groups;

import model.GroupDate;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class GroupModificationTests  extends TestBase {
    @Test
    void canModifyGroup(){
        if (app.groups().getCount()==0) {
            app.groups().createGroup(new GroupDate("ff", "ff", "ff"));
        }
        app.groups().modifyGroup(new GroupDate().withName("modify name"));
    }
}