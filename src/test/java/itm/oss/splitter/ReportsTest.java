package itm.oss.splitter;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class ReportsTest {

// simple csv file test


// complex csv file test


    // Issue 1 integration testing
    @Test
    void perPerson_usesCsvLoadedByExpenseStore() throws IOException, URISyntaxException {

        // Load the CSV from test/resources (classpath)
        var resourceUrl = getClass().getClassLoader().getResource("Expenses_Simple.csv");
        assertNotNull(resourceUrl, "Resource Expenses_Simple.csv not found on classpath");

        Path path = Path.of(resourceUrl.toURI());

        ExpenseStore store = new ExpenseStore();
        ArrayList<Expense> xs = store.load(path.toString());

        // sanity check
        assertFalse(xs.isEmpty(), "Expenses_Simple.csv should not be empty");

        Reports reports = new Reports();
        SimplePersonSummaryMap map = reports.perPerson(xs);

        // Sanity on loader
        assertEquals(3, xs.size(), "Should load three expense rows (header skipped)");
        assertEquals("Alice", xs.get(0).getPayer(), "the payer should be alice");
        assertEquals(new BigDecimal("60.00"), xs.get(0).getAmount(), "the amount should be 60");
        assertEquals(3, xs.get(0).getParticipants().size(), "There should be three participants");
    }
}


