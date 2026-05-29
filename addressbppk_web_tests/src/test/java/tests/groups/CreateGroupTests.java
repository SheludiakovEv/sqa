package tests.groups;

import common.CommonFunctions;
import model.GroupDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CreateGroupTests extends TestBase {

    public static List<GroupDate> groupProvider() throws IOException {
        var result = new ArrayList<GroupDate>();
        for (var name : List.of("", "group_name")) {
            for (var header : List.of("", "group_header")) {
                for (var footer : List.of("", "group_name")) {
                    result.add(new GroupDate()
                            .withName(name)
                            .withHeader(header)
                            .withFooter(footer));
                }
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("groups.json"), new TypeReference<List<GroupDate>>(){});
        result.addAll(value);
        return result;
    }

    @ParameterizedTest
    @MethodSource("groupProvider")
    public void canCreateMultipleGroups(GroupDate group) {
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        //Сравниваем два числа, ид групп
        Comparator<GroupDate> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);

        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(newGroups.get(newGroups.size() - 1).id()).withHeader("").withFooter(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);
    }

    public static List<GroupDate> negativeGroupProvider() {
        var result = new ArrayList<GroupDate>(List.of(
                new GroupDate("", "group1'", "h111", "f1232")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroups(GroupDate group) {
        var oldGroups = app.groups().getList();

        app.groups().createGroup(group);

        var newGroups = app.groups().getList();
        Assertions.assertEquals(newGroups, oldGroups);
    }
}