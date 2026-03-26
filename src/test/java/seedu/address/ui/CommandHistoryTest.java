package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class CommandHistoryTest {

    @Test
    public void previous_emptyHistory_returnsEmpty() {
        CommandHistory history = new CommandHistory();
        assertFalse(history.previous().isPresent());
        assertEquals(0, history.size());
    }

    @Test
    public void next_emptyHistory_returnsEmpty() {
        CommandHistory history = new CommandHistory();
        assertFalse(history.next().isPresent());
        assertEquals(0, history.size());
    }

    @Test
    public void addAndNavigate_cyclesThroughCommands() {
        CommandHistory history = new CommandHistory();

        history.add("first");
        history.add("second");

        assertEquals(2, history.size());

        // At end: previous yields "second", then "first".
        assertEquals("second", history.previous().orElseThrow());
        assertEquals("first", history.previous().orElseThrow());
        assertFalse(history.previous().isPresent());

        // Navigate forward: "second", then blank.
        assertEquals("second", history.next().orElseThrow());
        assertFalse(history.next().isPresent());

        // At end again: previous yields "second".
        assertEquals("second", history.previous().orElseThrow());
    }

    @Test
    public void next_oneCommand_returnsEmptyAtEnd() {
        CommandHistory history = new CommandHistory();
        history.add("only");

        assertEquals("only", history.previous().orElseThrow());
        assertFalse(history.next().isPresent());
        assertFalse(history.next().isPresent());
        assertEquals("only", history.previous().orElseThrow());
    }
}

