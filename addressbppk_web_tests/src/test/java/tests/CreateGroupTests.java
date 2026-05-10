package tests;

import model.GroupDate;
import org.junit.jupiter.api.Test;

public class CreateGroupTests extends TestBase {

    @Test
    public void createGroup() {
        app.groups().createGroup(new GroupDate("name", "header", "footer"));
    }

    @Test
    public void createGroupWithEmptyName() {
        app.groups().createGroup(new GroupDate());
    }

    @Test
    public void createGroupWithNameOnly() {
        app.groups().createGroup(new GroupDate().withName("sone name"));
    }
}