package org.rajnegi.transactionsummary.transaction_summary_report.mapper;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.rajnegi.transactionsummary.transaction_summary_report.beans.FieldType;
import org.rajnegi.transactionsummary.transaction_summary_report.beans.TransactionRecordBean;

public class FlatRecordMapper{

	private static final Logger LOGGER = Logger.getLogger(FlatRecordMapper.class);
	
	public FlatRecordMapper() {}

	/**
	 * Given the flat record convert to a TransactionRecordBean POJO reading values using positions.
	 * @author RajeevNegi
	 * @param record
	 * @return
	 */
	public Optional<TransactionRecordBean> mapRecord(String record) {
		
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("Start of mapRecord. record = "+record);
		}
		
		/*
		 *Excluding the trailing fillers, the length of each flat record should be 176 
		 */
		if(StringUtils.isEmpty(record) || record.trim().length() != 176) {
			LOGGER.error("Unable to parse the record - "+record+"; Missing fields!!");
			LOGGER.error("Moving to the next record");
			return Optional.ofNullable(null);
		}
		
		TransactionRecordBean transactionBean = new TransactionRecordBean.TransactionRecordBuilder("315")
												.clientType(getValue(record, FieldType.CLIENT_TYPE))
												.clientNumber(getValue(record, FieldType.CLIENT_NUMBER))
												.accountNumber(getValue(record, FieldType.ACCOUNT_NUMBER))
												.subAccountNumber(getValue(record, FieldType.SUB_ACCOUNT_NUMBER))
												.oppPartyCode(getValue(record, FieldType.OPP_PARTY_CODE))
												.productGroupCode(getValue(record, FieldType.PRODUCT_GROUP_CODE))
												.exchangeCode(getValue(record, FieldType.EXCHANGE_CODE))
												.symbol(getValue(record, FieldType.SYMBOL))
												.expirationDate(getValue(record, FieldType.EXPIRATION_DATE))
												.currencyCode(getValue(record, FieldType.CURRENCY_CODE))
												.movementCode(getValue(record, FieldType.MOVEMENT_CODE))
												.buySellCode(getValue(record, FieldType.BUY_SELL_CODE).charAt(0))
												.qntyLongSign(getValue(record, FieldType.QUANTITY_LONG_SIGN).charAt(0))
												.quantityLong(Long.valueOf(getValue(record, FieldType.QUANTITY_LONG)))
											    .qntyShortSign(getValue(record, FieldType.QUANTITY_SHORT_SIGN).charAt(0))
											    .quantityShort(Long.valueOf(getValue(record, FieldType.QUANTITY_SHORT)))
											    .exchBrokerFee(getDoubleValue(getValue(record, FieldType.EXCH_BROKER_FEE), 2))
											    .exchBrokerFeeDorC(getValue(record, FieldType.EXCH_BROKER_D_C).charAt(0))
											    .exchBrokerFeeCurrCode(getValue(record, FieldType.EXCH_BROKER_CURR_CODE))
											    .clearingFee(getDoubleValue(getValue(record, FieldType.CLEARING_FEE), 2))
											    .clearingFeeDorC(getValue(record, FieldType.CLEARING_FEE_D_C).charAt(0))
											    .clearingFeeCurrCode(getValue(record, FieldType.CLEARING_FEE_CURR_CODE))
											    .commission(getDoubleValue(getValue(record, FieldType.COMMISSION), 2))
											    .commissionDorC(getValue(record, FieldType.COMMISSION_D_C).charAt(0))
											    .commissionCurrCode(getValue(record, FieldType.COMMISSION_CURR_CODE))
											    .transactionDate(getValue(record, FieldType.TRANSACTION_DATE))
											    .futureReference(Long.valueOf(getValue(record, FieldType.FUTURE_REFERENCE)))
											    .ticketNumber(getValue(record, FieldType.TICKET_NUMBER))
											    .externalNumber(Long.valueOf(getValue(record, FieldType.EXTERNAL_NUMBER)))
											    .transactionPrice(getDoubleValue(getValue(record, FieldType.TRANSACTION_PRICE), 7))
											    .traderInitials(getValue(record, FieldType.TRADER_INITS))
											    .oppTraderId(getValue(record, FieldType.OPP_TRADER_ID))
											    .openCloseCode(getValue(record, FieldType.OPEN_CLOSE_CODE).charAt(0))
												.build();
		
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("End of mapRecord. returning TransactionRecordBean = "+transactionBean);
		}
		
		return Optional.ofNullable(transactionBean);
	}
	
	/**
	 * Fetch the value for given field using substring
	 * @param record
	 * @param field
	 * @return
	 */
	private String getValue(String record, FieldType field) {
		return record.substring(field.getStartIndex(), field.getEndIndex());
	}
	
	/**
	 * Add decimal and format the string value to double
	 * @param doubleValue
	 * @param decimals
	 * @return
	 */
	private Double getDoubleValue(String doubleValue, int decimals) {
		doubleValue = new StringBuilder(doubleValue).insert(doubleValue.length()-decimals, '.').toString();
		return Double.valueOf(doubleValue);
	}
}
