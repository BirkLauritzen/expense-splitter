package itm.oss.splitter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Reports {

    public SimpleMap totalsByCategory(ArrayList<Expense> xs) {
        // TODO (Issue 6): sum amounts per category
        throw new UnsupportedOperationException("totalsByCategory() not implemented yet");
    }

    public SimplePersonSummaryMap perPerson(ArrayList<Expense> xs) {
        SimplePersonSummaryMap map = new SimplePersonSummaryMap();

        // xs is a list of Expence objects
        // String date, String payer, BigDecimal amount, String currency,
        // ArrayList<String> participants, String category, String notes
        for (Expense x : xs) {
            int count = x.getParticipants().size();
            if (count == 0) continue;

            // amount paid divided by people in the bill
            BigDecimal owedPerPerson = x.getAmount()
                    .divide(new BigDecimal(count), 2, RoundingMode.HALF_UP);

            for (String name : x.getParticipants()) {

                // create new payment map for each participant
                if (map.get(name) == null) {
                    map.put(name, new PersonSummary(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO ));
                }
                PersonSummary ps = map.get(name);

                // add full amount to payer
                if (name.equals(x.getPayer())) {
                    ps.addPaid(x.getAmount());
                }

                ps.addOwed(owedPerPerson);
            }
        }

        return map;
    }

}
