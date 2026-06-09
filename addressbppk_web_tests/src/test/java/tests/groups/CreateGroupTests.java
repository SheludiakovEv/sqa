package tests.groups;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.GroupDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

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

        var json = Files.readString(Paths.get("groups.json"));

        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(json, new TypeReference<List<GroupDate>>(){});
        result.addAll(value);
        return result;
    }

    public static Stream<GroupDate> randomGroup() {
        Supplier<GroupDate> randomGroup = ()-> new GroupDate()
                .withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString(10))
                .withFooter(CommonFunctions.randomString(10));
        return Stream.generate(randomGroup).limit(10);
    }

    @ParameterizedTest
    @MethodSource("randomGroup")
    public void canCreateGroups(GroupDate group) {
        var oldGroups = app.jdbc().getGroupList();
        app.groups().createGroup(group);
        var newGroups = app.jdbc().getGroupList();

        var extraGroups = newGroups.stream().filter(g -> ! oldGroups.contains(g)).toList();
        var newId = extraGroups.get(0).id();

        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(newId));

        Assertions.assertEquals(Set.copyOf(newGroups), Set.copyOf(expectedList));
    }

    public static List<GroupDate> negativeGroupProvider() {
        var result = new ArrayList<GroupDate>(List.of(
                new GroupDate("", "group1'", "h111", "f1232")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroups(GroupDate group) {
        var oldGroups = app.jdbc().getGroupList();

        app.groups().createGroup(group);

        var newGroups = app.jdbc().getGroupList();
        Assertions.assertEquals(newGroups, oldGroups);
    }
}