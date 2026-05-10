package tests.groups;

import model.GroupDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;

import java.util.ArrayList;
import java.util.List;

public class CreateGroupTests extends TestBase {

    public static List<GroupDate> groupProvider() {
        var result = new ArrayList<GroupDate>();
        for (var name : List.of("", "group_name")) {
            for (var header : List.of("", "group_header")) {
                for (var footer : List.of("", "group_name")) {
                    result.add(new GroupDate(name, header, footer));
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new GroupDate(randomString(i), randomString(i), randomString(i)));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("groupProvider")
    public void canCreateMultipleGroups(GroupDate group) {
        int groupCount = app.groups().getCount();

        app.groups().createGroup(group);

        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);
    }

    public static List<GroupDate> negativeGroupProvider() {
        var result = new ArrayList<GroupDate>(List.of(
                new GroupDate("group1'", "h111", "f1232")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroups(GroupDate group) {
        int groupCount = app.groups().getCount();

        app.groups().createGroup(group);

        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount, newGroupCount);
    }
}