package ru.stqa.montis.manager;

import org.openqa.selenium.By;

public class SessionHelper extends HelperBase{

    public SessionHelper(ApplicationManager manager) {
        super(manager);
    }

    public void login(String user, String password) {
        type(By.name("username"), user);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }

    public boolean isLoggetIn() {
        return isElementPresent(By.cssSelector("span.user-info"));
    }
}
