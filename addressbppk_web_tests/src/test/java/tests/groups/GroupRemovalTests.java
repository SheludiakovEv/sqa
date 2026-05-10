package tests.groups;

import dev.failsafe.internal.util.Assert;
import model.GroupDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupDate("ff", "ff", "ff"));
        }
        int groupCount = app.groups().getCount();
        app.groups().removeGroup();
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount - 1, newGroupCount);
    }

    @Test
    void canRemoveAllGroupAtOnce() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupDate("ff", "ff", "ff"));
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0, app.groups().getCount());
    }
}