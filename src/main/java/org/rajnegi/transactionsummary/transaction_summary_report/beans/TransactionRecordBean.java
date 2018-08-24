package org.rajnegi.transactionsummary.transaction_summary_report.beans;

/**
 * POJO bean to hold a single flat record
 * @author RajeevNegi
 *
 */
public class TransactionRecordBean {
	
	private String recordCode;
	private String clientType;
	private String clientNumber;
	private String accountNumber;
	private String subAccountNumber;
	private String oppPartyCode;
	private String productGroupCode;
	private String exchangeCode;
	private String symbol;
	private String expirationDate;
	private String currencyCode;
	private String movementCode;
	private char buySellCode;
	private char qntyLongSign;
	private Long quantityLong;
	private char qntyShortSign;
	private Long quantityShort;
	private Double exchBrokerFee;
	private char exchBrokerFeeDorC;
	private String exchBrokerFeeCurrCode;
	private Double clearingFee;
	private char clearingFeeDorC;
	private String clearingFeeCurrCode;
	private Double commission;
	private char commissionDorC;
	private String commissionCurrCode;
	private String transactionDate;
	private Long futureReference;
	private String ticketNumber;
	private Long externalNumber;
	private Double transactionPrice;
	private String traderInitials;
	private String oppTraderId;
	private char openCloseCode;
	
	private TransactionRecordBean() {}
	
	public String getRecordCode() {
		return recordCode;
	}

	public String getClientType() {
		return clientType;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getSubAccountNumber() {
		return subAccountNumber;
	}

	public String getOppPartyCode() {
		return oppPartyCode;
	}

	public String getProductGroupCode() {
		return productGroupCode;
	}

	public String getExchangeCode() {
		return exchangeCode;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public String getCurrencyMode() {
		return currencyCode;
	}

	public String getMovementCode() {
		return movementCode;
	}

	public char getBuySellCode() {
		return buySellCode;
	}

	public char getQntyLongSign() {
		return qntyLongSign;
	}

	public Long getQuantityLong() {
		return quantityLong;
	}

	public char getQntyShortSign() {
		return qntyShortSign;
	}

	public Long getQuantityShort() {
		return quantityShort;
	}

	public Double getExchBrokerFee() {
		return exchBrokerFee;
	}

	public char getExchBrokerFeeDorC() {
		return exchBrokerFeeDorC;
	}

	public String getExchBrokerFeeCurrCode() {
		return exchBrokerFeeCurrCode;
	}

	public Double getClearingFee() {
		return clearingFee;
	}

	public char getClearingFeeDorC() {
		return clearingFeeDorC;
	}

	public String getClearingFeeCurrCode() {
		return clearingFeeCurrCode;
	}

	public Double getCommission() {
		return commission;
	}

	public char getCommissionDorC() {
		return commissionDorC;
	}

	public String getCommissionCurrCode() {
		return commissionCurrCode;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public Long getFutureReference() {
		return futureReference;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public Long getExternalNumber() {
		return externalNumber;
	}

	public Double getTransactionPrice() {
		return transactionPrice;
	}

	public String getTraderInitials() {
		return traderInitials;
	}

	public String getOppTraderId() {
		return oppTraderId;
	}

	public char getOpenCloseCode() {
		return openCloseCode;
	}

	@Override
	public String toString() {
		return String.format(
				"TransactionRecordBean [recordCode=%s, clientType=%s, clientNumber=%s, accountNumber=%s, subAccountNumber=%s, oppPartyCode=%s, productGroupCode=%s, exchangeCode=%s, symbol=%s, expirationDate=%s, currencyMode=%s, movementCode=%s, buySellCode=%s, qntyLongSign=%s, quantityLong=%s, qntyShortSign=%s, quantityShort=%s, exchBrokerFee=%s, exchBrokerFeeDorC=%s, exchBrokerFeeCurrCode=%s, clearingFee=%s, clearingFeeDorC=%s, clearingFeeCurrCode=%s, commission=%s, commissionDorC=%s, commissionCurrCode=%s, transactionDate=%s, futureReference=%s, ticketNumber=%s, externalNumber=%s, transactionPrice=%s, traderInitials=%s, oppTraderId=%s, openCloseCode=%s]",
				recordCode, clientType, clientNumber, accountNumber, subAccountNumber, oppPartyCode, productGroupCode,
				exchangeCode, symbol, expirationDate, currencyCode, movementCode, buySellCode, qntyLongSign,
				quantityLong, qntyShortSign, quantityShort, exchBrokerFee, exchBrokerFeeDorC, exchBrokerFeeCurrCode,
				clearingFee, clearingFeeDorC, clearingFeeCurrCode, commission, commissionDorC, commissionCurrCode,
				transactionDate, futureReference, ticketNumber, externalNumber, transactionPrice, traderInitials,
				oppTraderId, openCloseCode);
	}

	public static class TransactionRecordBuilder{
		
		private String recordCode;
		private String clientType;
		private String clientNumber;
		private String accountNumber;
		private String subAccountNumber;
		private String oppPartyCode;
		private String productGroupCode;
		private String exchangeCode;
		private String symbol;
		private String expirationDate;
		private String currencyCode;
		private String movementCode;
		private char buySellCode;
		private char qntyLongSign;
		private Long quantityLong;
		private char qntyShortSign;
		private Long quantityShort;
		private Double exchBrokerFee;
		private char exchBrokerFeeDorC;
		private String exchBrokerFeeCurrCode;
		private Double clearingFee;
		private char clearingFeeDorC;
		private String clearingFeeCurrCode;
		private Double commission;
		private char commissionDorC;
		private String commissionCurrCode;
		private String transactionDate;
		private Long futureReference;
		private String ticketNumber;
		private Long externalNumber;
		private Double transactionPrice;
		private String traderInitials;
		private String oppTraderId;
		private char openCloseCode;
		
		public TransactionRecordBuilder(String recordCode) {
			this.recordCode = recordCode;
		}

		public TransactionRecordBuilder clientType(String clientType) {
			this.clientType = clientType;
			return this;
		}

		public TransactionRecordBuilder clientNumber(String clientNumber) {
			this.clientNumber = clientNumber;
			return this;
		}

		public TransactionRecordBuilder accountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
			return this;
		}

		public TransactionRecordBuilder subAccountNumber(String subAccountNumber) {
			this.subAccountNumber = subAccountNumber;
			return this;
		}

		public TransactionRecordBuilder oppPartyCode(String oppPartyCode) {
			this.oppPartyCode = oppPartyCode;
			return this;
		}

		public TransactionRecordBuilder productGroupCode(String productGroupCode) {
			this.productGroupCode = productGroupCode;
			return this;
		}

		public TransactionRecordBuilder exchangeCode(String exchangeCode) {
			this.exchangeCode = exchangeCode;
			return this;
		}

		public TransactionRecordBuilder symbol(String symbol) {
			this.symbol = symbol;
			return this;
		}

		public TransactionRecordBuilder expirationDate(String expirationDate) {
			this.expirationDate = expirationDate;
			return this;
		}

		public TransactionRecordBuilder currencyCode(String currencyCode) {
			this.currencyCode = currencyCode;
			return this;
		}

		public TransactionRecordBuilder movementCode(String movementCode) {
			this.movementCode = movementCode;
			return this;
		}

		public TransactionRecordBuilder buySellCode(char buySellCode) {
			this.buySellCode = buySellCode;
			return this;
		}

		public TransactionRecordBuilder qntyLongSign(char qntyLongSign) {
			this.qntyLongSign = qntyLongSign;
			return this;
		}

		public TransactionRecordBuilder quantityLong(Long quantityLong) {
			this.quantityLong = quantityLong;
			return this;
		}

		public TransactionRecordBuilder qntyShortSign(char qntyShortSign) {
			this.qntyShortSign = qntyShortSign;
			return this;
		}

		public TransactionRecordBuilder quantityShort(Long quantityShort) {
			this.quantityShort = quantityShort;
			return this;
		}

		public TransactionRecordBuilder exchBrokerFee(Double exchBrokerFee) {
			this.exchBrokerFee = exchBrokerFee;
			return this;
		}

		public TransactionRecordBuilder exchBrokerFeeDorC(char exchBrokerFeeDorC) {
			this.exchBrokerFeeDorC = exchBrokerFeeDorC;
			return this;
		}

		public TransactionRecordBuilder exchBrokerFeeCurrCode(String exchBrokerFeeCurrCode) {
			this.exchBrokerFeeCurrCode = exchBrokerFeeCurrCode;
			return this;
		}

		public TransactionRecordBuilder clearingFee(Double clearingFee) {
			this.clearingFee = clearingFee;
			return this;
		}

		public TransactionRecordBuilder clearingFeeDorC(char clearingFeeDorC) {
			this.clearingFeeDorC = clearingFeeDorC;
			return this;
		}

		public TransactionRecordBuilder clearingFeeCurrCode(String clearingFeeCurrCode) {
			this.clearingFeeCurrCode = clearingFeeCurrCode;
			return this;
		}

		public TransactionRecordBuilder commission(Double commission) {
			this.commission = commission;
			return this;
		}

		public TransactionRecordBuilder commissionDorC(char commissionDorC) {
			this.commissionDorC = commissionDorC;
			return this;
		}

		public TransactionRecordBuilder commissionCurrCode(String commissionCurrCode) {
			this.commissionCurrCode = commissionCurrCode;
			return this;
		}

		public TransactionRecordBuilder transactionDate(String transactionDate) {
			this.transactionDate = transactionDate;
			return this;
		}

		public TransactionRecordBuilder futureReference(Long futureReference) {
			this.futureReference = futureReference;
			return this;
		}

		public TransactionRecordBuilder ticketNumber(String ticketNumber) {
			this.ticketNumber = ticketNumber;
			return this;
		}

		public TransactionRecordBuilder externalNumber(Long externalNumber) {
			this.externalNumber = externalNumber;
			return this;
		}

		public TransactionRecordBuilder transactionPrice(Double transactionPrice) {
			this.transactionPrice = transactionPrice;
			return this;
		}

		public TransactionRecordBuilder traderInitials(String traderInitials) {
			this.traderInitials = traderInitials;
			return this;
		}

		public TransactionRecordBuilder oppTraderId(String oppTraderId) {
			this.oppTraderId = oppTraderId;
			return this;
		}

		public TransactionRecordBuilder openCloseCode(char openCloseCode) {
			this.openCloseCode = openCloseCode;
			return this;
		}
		
		public TransactionRecordBean build() {
			
			TransactionRecordBean recordBean = new TransactionRecordBean();
			recordBean.recordCode = this.recordCode;
			recordBean.accountNumber = this.accountNumber;
			recordBean.buySellCode = this.buySellCode;
			recordBean.clearingFee = this.clearingFee;
			recordBean.clearingFeeCurrCode = this.clearingFeeCurrCode;
			recordBean.clearingFeeDorC = this.clearingFeeDorC;
			recordBean.clientNumber = this.clientNumber;
			recordBean.clientType = this.clientType;
			recordBean.commission = this.commission;
			recordBean.commissionCurrCode = this.commissionCurrCode;
			recordBean.commissionDorC = this.commissionDorC;
			recordBean.currencyCode = this.currencyCode;
			recordBean.exchangeCode = this.exchangeCode;
			recordBean.exchBrokerFee = this.exchBrokerFee;
			recordBean.exchBrokerFeeCurrCode = this.exchBrokerFeeCurrCode;
			recordBean.exchBrokerFeeDorC = this.exchBrokerFeeDorC;
			recordBean.expirationDate = this.expirationDate;
			recordBean.externalNumber = this.externalNumber;
			recordBean.futureReference = this.futureReference;
			recordBean.movementCode = this.movementCode;
			recordBean.openCloseCode = this.openCloseCode;
			recordBean.oppPartyCode = this.oppPartyCode;
			recordBean.oppTraderId = this.oppTraderId;
			recordBean.productGroupCode = this.productGroupCode;
			recordBean.qntyLongSign = this.qntyLongSign;
			recordBean.qntyShortSign = this.qntyShortSign;
			recordBean.quantityLong = this.quantityLong;
			recordBean.quantityShort = this.quantityShort;
			recordBean.subAccountNumber = this.subAccountNumber;
			recordBean.symbol = this.symbol;
			recordBean.ticketNumber = this.ticketNumber;
			recordBean.traderInitials = this.traderInitials;
			recordBean.transactionDate = this.transactionDate;
			recordBean.transactionPrice = this.transactionPrice;
			
			return recordBean;
		}
		
	}

}
