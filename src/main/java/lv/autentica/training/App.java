package lv.autentica.training;

import lv.autentica.training.console.ConsoleApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@ComponentScan
@EnableAutoConfiguration
public class App {

    @Inject
    ConsoleApplication consoleGame;

    private final Logger log = LoggerFactory.getLogger(App.class);

    @PostConstruct
    public void initApplication() {
        consoleGame.start();
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(App.class);
        app.run(args);
    }
}
