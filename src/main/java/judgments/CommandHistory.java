package judgments;

import java.util.Optional;
import java.util.Vector;

public class CommandHistory {
    private final Vector<String> history;
    private int pos;

    public CommandHistory() {
        this.history = new Vector<>();
        this.pos = 0;
    }

    public void addCommand(String command) {
        if (!command.equals("")) {
            this.history.add(command);
        }
    }

    public Optional<String> getNext() {
        if(pos >= 2) {
            pos--;
            return Optional.of(getCurrentPositionText());
        } else if (pos == 1){
            pos--;
            return Optional.of("");
        } else {
            return Optional.empty();
        }
    }

    public Optional<String> getPrevious() {
        if(pos <= history.size() - 1){
            pos++;
            return Optional.of(getCurrentPositionText());
        } else {
            return Optional.empty();
        }
    }

    private String getCurrentPositionText() {
        return history.get(history.size() - pos);
    }
}
