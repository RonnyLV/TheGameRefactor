package lv.autentica.training.config.prompter;

import org.codehaus.plexus.components.interactivity.AbstractInputHandler;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.Disposable;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.Initializable;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.InitializationException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class InputHandlerImpl
        extends AbstractInputHandler
        implements Initializable, Disposable {

    private BufferedReader consoleReader;

    public String readLine()
            throws IOException {
        return consoleReader.readLine();
    }

    public String readPassword()
            throws IOException {
        return consoleReader.readLine();
    }

    @PostConstruct
    public void initialize()
            throws InitializationException {
        consoleReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void dispose() {
        try {
            consoleReader.close();
        } catch (IOException e) {
            getLogger().error("Error closing input stream must be ignored", e);
        }
    }
}
