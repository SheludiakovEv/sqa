package manager;

import org.openqa.selenium.By;

public class LoginHelper extends HelperBase{

    public LoginHelper(ApplicationManager manager){
        super(manager);
    }

    void login(String user, String password) {
        type(By.name("pass"),password);
        click(By.name("user"));
        type(By.name("user"),user);
        click(By.id("LoginForm"));
        click(By.name("pass"));
        click(By.cssSelector("input:nth-child(7)"));
    }
}
