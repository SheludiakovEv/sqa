package tests.groups;

import model.ContactDate;
import model.GroupDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class AddGroupInContact extends TestBase {

    @Test
    public void CanAddGroupOldContact() {
        // Убедились что есть группа
        if (app.hmb().getGroupCount() == 0) {
            app.hmb().createGroup(new GroupDate("", "New_group_name", "New_group_header", "New_group_footer"));
        }

        // Убедились что есть контакт
        if (app.hmb().getContactCount() == 0) {
            app.contacts().createContact(new ContactDate("", "New_contact_firstName", "New_contact_middleName", "New_contact_lastName","", "", "", "", "","", "", "", ""));
        }

        // Загружаем списки из БД для поиска подходящей пары
        List<GroupDate> groups = app.hmb().getGroupList();
        List<ContactDate> contacts = app.hmb().getContactList();

        GroupDate selectedGroup = null;
        ContactDate selectedContact = null;

        // Поиск пары
        for (GroupDate group : groups) {
            List<ContactDate> contactsInGroup = app.hmb().getContactsInGroup(group);

            for (ContactDate contact : contacts) {
                if (!contactsInGroup.contains(contact)) {
                    selectedGroup = group;
                    selectedContact = contact;
                    break;
                }
            }
            if (selectedGroup != null) {
                break;
            }
        }

        // Создаем контакт для добавлния в группу
        if (selectedGroup == null) {
            selectedGroup = groups.get(0);


            ContactDate newContact = new ContactDate("", "Unique_firstName", "Unique_middleName", "Unique_lastName","", "", "", "", "","", "", "", "");
            app.contacts().createContact(newContact);

            // Перечитываем список контактов из БД, чтобы получить созданный контакт с его новым ID
            List<ContactDate> updatedContacts = app.hmb().getContactList();
            selectedContact = updatedContacts.stream()
                    .filter(c -> "Unique_firstName".equals(c.firstName()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Новый контакт не найден в БД после создания через UI"));
        }

        // Получаем список контактов в группе ДО добавления
        var oldContactsInGroup = app.hmb().getContactsInGroup(selectedGroup);

        // Добавление группы в контакт
        app.contacts().AddGroupToContact(selectedContact, selectedGroup);

        // Получаем список контактов в группе ПОСЛЕ добавления
        var newContactsInGroup = app.hmb().getContactsInGroup(selectedGroup);

        Comparator<ContactDate> compareById = (o1, o2) ->
                Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));

        newContactsInGroup.sort(compareById);

        var expectedList = new ArrayList<>(oldContactsInGroup);
        expectedList.add(selectedContact);
        expectedList.sort(compareById);

        Assertions.assertEquals(expectedList, newContactsInGroup);
    }
}