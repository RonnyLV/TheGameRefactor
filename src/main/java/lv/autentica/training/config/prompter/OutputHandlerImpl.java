package lv.autentica.training.config.prompter;

import org.codehaus.plexus.components.interactivity.OutputHandler;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.InitializationException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class OutputHandlerImpl
        implements OutputHandler {
    private PrintWriter consoleWriter;

    @PostConstruct
    public void initialize()
            throws InitializationException {
        consoleWriter = new PrintWriter(System.out);
    }

    public void dispose() {
        consoleWriter.close();
    }

    public void write(String line)
            throws IOException {
        consoleWriter.print(line);
        consoleWriter.flush();
    }

    public void writeLine(String line)
            throws IOException {
        consoleWriter.println(line);
        consoleWriter.flush();
    }
}
