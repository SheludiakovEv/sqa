package tests.groups;

import model.GroupDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Random;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        if (app.hmb().getGroupCount() == 0) {
            app.hmb().createGroup(new GroupDate("", "ff", "ff", "ff"));
        }
        var oldGroups = app.hmb().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.groups().removeGroup(oldGroups.get(index));
        var newGroups = app.hmb().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Assertions.assertEquals(newGroups.size(), oldGroups.size()-1);
    }

    @Test
    void canRemoveAllGroupAtOnce() {
        if (app.hmb().getGroupCount() == 0) {
            app.hmb().createGroup(new GroupDate("", "ff", "ff", "ff"));
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0, app.hmb().getGroupCount());
    }
}