package ru.merann.practicaltask.client.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CliAppRunner.class)
public class ClientRestApplicationTests {

//    @Autowired
//    private Shell shell;

    @Test
    public void runHelpTest() {
//        Assert.assertThat(shell.evaluate(() -> "help"), Matchers.notNullValue());
    }

}
