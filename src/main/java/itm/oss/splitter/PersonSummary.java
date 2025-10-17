package itm.oss.splitter;

import java.math.BigDecimal;

public class PersonSummary {
  private BigDecimal paidTotal;
  private BigDecimal owedTotal;
  private BigDecimal net;

  public PersonSummary(BigDecimal paidTotal, BigDecimal owedTotal, BigDecimal net) {
    this.paidTotal = paidTotal;
    this.owedTotal = owedTotal;
    this.net = net;
  }

  public BigDecimal getPaidTotal() { return paidTotal; }
  public BigDecimal getOwedTotal() { return owedTotal; }
  public BigDecimal getNet() { return net; }
}
