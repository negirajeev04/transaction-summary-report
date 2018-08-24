package org.rajnegi.transactionsummary.transaction_summary_report.beans;

public enum FieldType {

	RECORD_CODE (0, 3),
	CLIENT_TYPE (3, 4),
	CLIENT_NUMBER (7, 4),
	ACCOUNT_NUMBER (11,4),
	SUB_ACCOUNT_NUMBER (15,4),
	OPP_PARTY_CODE (19,6),
	PRODUCT_GROUP_CODE (25,2),
	EXCHANGE_CODE (27,4),
	SYMBOL (31,6),
	EXPIRATION_DATE (37,8),
	CURRENCY_CODE (45,3),
	MOVEMENT_CODE (48,2),
	BUY_SELL_CODE (50,1),
	QUANTITY_LONG_SIGN (51,1),
	QUANTITY_LONG (52,10),
	QUANTITY_SHORT_SIGN (62,1),
	QUANTITY_SHORT (63,10),
	EXCH_BROKER_FEE (73,12),
	EXCH_BROKER_D_C (85,1),
	EXCH_BROKER_CURR_CODE (86,3),
	CLEARING_FEE (89,12),
	CLEARING_FEE_D_C (101,1),
	CLEARING_FEE_CURR_CODE (102,3),
	COMMISSION (105,12),
	COMMISSION_D_C (117,1),
	COMMISSION_CURR_CODE (118,3),
	TRANSACTION_DATE (121,8),
	FUTURE_REFERENCE (129,6),
	TICKET_NUMBER (135,6),
	EXTERNAL_NUMBER (141,6),
	TRANSACTION_PRICE (147,15),
	TRADER_INITS (162,6),
	OPP_TRADER_ID (168,7),
	OPEN_CLOSE_CODE (175,1)
	;
	
	private final int startIndex;
	private final int offset;
	
	FieldType(int start, int offset){
		this.startIndex = start;
		this.offset = offset;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public int getOffset() {
		return offset;
	}
	
	public int getEndIndex() {
		return startIndex+offset;
	}
	
}
