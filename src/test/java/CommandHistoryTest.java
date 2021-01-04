import judgments.CommandHistory;

import java.util.Optional;

import  org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandHistoryTest {
    CommandHistory commandHistory = new CommandHistory();
    String first = "element1";
    String second = "element2";

    @AfterEach
    void resetHistory() {
        commandHistory = new CommandHistory();
    }

    @BeforeEach
    void initHistory() {
        // GIVEN
        // WHEN
        commandHistory.addCommand(first);
        commandHistory.addCommand(second);
    }

    @Test
    public void oneElementTest() {
        // THEN
        Assert.assertEquals(Optional.of(second), commandHistory.getPrevious());
    }

    @Test
    public void samePositionTest() {
        // THEN
        Assert.assertEquals(Optional.of(second), commandHistory.getPrevious());
        Assert.assertEquals(Optional.of(first), commandHistory.getPrevious());
        Assert.assertEquals(Optional.of(second), commandHistory.getNext());
    }

    @Test
    public void historyBeginning() {
        // THEN
        Assert.assertEquals(Optional.empty(), commandHistory.getNext());
        Assert.assertEquals(Optional.empty(), commandHistory.getNext());
        Assert.assertEquals(Optional.of(second), commandHistory.getPrevious());
        Assert.assertEquals(Optional.of(""), commandHistory.getNext());
        Assert.assertEquals(Optional.empty(), commandHistory.getNext());
    }

    @Test
    public void historyEnd() {
        // THEN
        Assert.assertEquals(Optional.of(second), commandHistory.getPrevious());
        Assert.assertEquals(Optional.of(first), commandHistory.getPrevious());
        Assert.assertEquals(Optional.empty(), commandHistory.getPrevious());
        Assert.assertEquals(Optional.empty(), commandHistory.getPrevious());
        Assert.assertEquals(Optional.of(second), commandHistory.getNext());
    }
}
