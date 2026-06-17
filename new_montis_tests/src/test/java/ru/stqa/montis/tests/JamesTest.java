package ru.stqa.montis.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.montis.common.CommonFunctions;

public class JamesTest extends TestBase {

    @Test
    void canCreateUser(){
        app.jamesCli().addUser(String.format("%s@localhost", CommonFunctions.randomString(8)),
                "password");
    }
}