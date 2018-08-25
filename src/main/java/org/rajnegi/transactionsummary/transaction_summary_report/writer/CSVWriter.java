package org.rajnegi.transactionsummary.transaction_summary_report.writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.rajnegi.transactionsummary.transaction_summary_report.beans.TransactionRecordBean;

/**
 * CSVWriter - Works with the list of TransactionRecordBean and generate a summary report
 * @author RajeevNegi
 *
 */
public class CSVWriter {

	private static final String OUTPUT_FILE = "output.csv";
	private static final String OUTPUT_FILE_HEADER = "Client_Information,Product_Information,Total_Transaction_Amount";
	private static final String DELIMITER = ",";
	private static final String NEW_LINE = "\n";
	private static final Logger LOGGER = Logger.getLogger(CSVWriter.class);
	
	/**
	 * Works with the list of TransactionRecordBean and generate a summary report
	 * @param listOfTransactions
	 * @param outFilePath
	 */
	public static void generateSummaryReport(List<TransactionRecordBean> listOfTransactions, String outFilePath) {

		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("Start of generateSummaryReport. listOfTransactions = "+listOfTransactions+";outFilePath="+outFilePath);
		}
		
		try (FileWriter fwriter = new FileWriter(new File(outFilePath + "/" + OUTPUT_FILE))) {

			fwriter.append(OUTPUT_FILE_HEADER).append(NEW_LINE);

			Map<String, Map<String, List<TransactionRecordBean>>> groupByClientAndProduct = groupTransactionsByClientByProduct(
					listOfTransactions);

			for (String client_Info : groupByClientAndProduct.keySet()) {
				Map<String, List<TransactionRecordBean>> productGroup = groupByClientAndProduct.get(client_Info);
				for (String product_Info : productGroup.keySet()) {

					List<TransactionRecordBean> tranxPerClientPerGroup = productGroup.get(product_Info);
					fwriter.append(client_Info).append(DELIMITER);
					fwriter.append(product_Info).append(DELIMITER);

					Double totalTransactionAmount = getTotalTransactionAmount(tranxPerClientPerGroup);
					fwriter.append(String.valueOf(totalTransactionAmount)).append(NEW_LINE);
				}
			}

		} catch (IOException ex) {
			LOGGER.error("Error while creating the output file - "+ex.getMessage());
		}
		
		LOGGER.info("Daily Summary Report generated at - "+OUTPUT_FILE);
		
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("End of generateSummaryReport");
		}
	}

	/**
	 * Groups the transaction by client and product information.
	 * @param listOfTransactions - List of transactions mapped from the flat file
	 * @return
	 */
	private static Map<String, Map<String, List<TransactionRecordBean>>> groupTransactionsByClientByProduct(
			List<TransactionRecordBean> listOfTransactions) {
		
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("Start of groupTransactionsByClientByProduct. listOfTransactions = "+listOfTransactions);
		}
		
		Map<String, Map<String, List<TransactionRecordBean>>> groupByClientAndProduct = listOfTransactions.stream()
				.collect(Collectors.groupingBy(
						tranx -> tranx.getClientType().trim() + "_" + tranx.getClientNumber().trim() + "_"
								+ tranx.getAccountNumber().trim() + "_" + tranx.getSubAccountNumber().trim(),
						Collectors.groupingBy(tranx -> tranx.getProductGroupCode().trim() + "_"
								+ tranx.getExchangeCode().trim() + "_" + tranx.getSymbol().trim() + "_"
								+ tranx.getExpirationDate().trim())));
		
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("End of groupTransactionsByClientByProduct. returning = "+groupByClientAndProduct);
		}
		
		return groupByClientAndProduct;
	}
	
	/**
	 * Returns Total_Transaction_Amount = Net Total of (qntyLong*transactionPrice - qntyShort*transactionPrice)
	 * @param transactions
	 * @return
	 */
	private static Double getTotalTransactionAmount(List<TransactionRecordBean> transactions) {
		
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("Start of getTotalTransactionAmount. transactions = "+transactions);
		}
		
		double quantityLongSum = transactions.stream()
											 .mapToDouble(tranx -> tranx.getQuantityLong()*tranx.getTransactionPrice())
											 .sum();

		double quantityShortSum = transactions.stream()
											  .mapToDouble(tranx -> tranx.getQuantityShort()*tranx.getTransactionPrice())
											  .sum();
		
		Double totalTransactionAmount = quantityLongSum-quantityShortSum;
		
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("End of getTotalTransactionAmount. returning = "+totalTransactionAmount);
		}
		
		return totalTransactionAmount;
		
	}

}
