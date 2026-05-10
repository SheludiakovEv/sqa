package tests.groups;

import model.GroupDate;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupDate("ff", "ff", "ff"));
        }
        app.groups().removeGroup();
    }
}