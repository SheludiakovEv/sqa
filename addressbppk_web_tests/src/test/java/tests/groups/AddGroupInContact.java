package tests.groups;

import model.ContactDate;
import model.GroupDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class AddGroupInContact extends TestBase {


    @Test
    public void CanAddGroupOldContact() {

        //Проверяем наличие контактов. если нет, то создаём
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactDate("", "New_contact_firstName", "New_contact_middleName", "New_contact_lastName","", "", "", "", "","", "", "", ""));
        }


        // Проверяем наличие групп. создаём если нет
        if (app.hmb().getGroupCount() == 0) {
            app.hmb().createGroup(new GroupDate("", "New_group_name", "New_group_header", "New_group_footer"));
        }


        //Выбор случайного контакта
        var contactList = app.hmb().getContactList();
        var rnd = new Random();
        var indexContact = rnd.nextInt(contactList.size());
        var selectedContact = contactList.get(indexContact);

        //Выбор случайной группы
        var groupList = app.hmb().getGroupList();
        var indexGroup = rnd.nextInt(groupList.size());
        var selectedGroup = groupList.get(indexGroup);


        //Получаем список контактов в группе ДО добавления
        var oldContactsInGroup = app.hmb().getContactsInGroup(selectedGroup);

        //Добавление группы в контакт
        app.contacts().AddGroupToContact(selectedContact, selectedGroup);


        var newContactsInGroup = app.hmb().getContactsInGroup(selectedGroup);

        Comparator<ContactDate> compareById = (o1, o2) ->
                Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        newContactsInGroup.sort(compareById);

        var expectedList = new ArrayList<>(oldContactsInGroup);
        expectedList.add(selectedContact);
        expectedList.sort(compareById);


        Assertions.assertEquals(newContactsInGroup, expectedList);
    }
}
