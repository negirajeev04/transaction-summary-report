package org.rajnegi.transactionsummary.transaction_summary_report;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.rajnegi.transactionsummary.transaction_summary_report.beans.TransactionRecordBean;
import org.rajnegi.transactionsummary.transaction_summary_report.mapper.FlatRecordMapper;
import org.rajnegi.transactionsummary.transaction_summary_report.mapper.RecordMapper;
import org.rajnegi.transactionsummary.transaction_summary_report.writer.CSVWriter;


public class TransactionSummary {
	
	private static final Logger LOGGER = Logger.getLogger(TransactionSummary.class);

	public static void main(String[] args) {
		
		if (args == null || args.length < 1) {
			LOGGER.error(
					"Missing the input flat file path. Please mention absolute path for the file as part of the program arguments");
			System.exit(1);
		}

		File inputFile = new File(args[0]);
		TransactionSummary summary = new TransactionSummary();
		FlatRecordMapper mapper = new FlatRecordMapper();

		List<TransactionRecordBean> listOfTransactions = summary.processInputFile(inputFile, mapper::mapRecord);
		CSVWriter.generateSummaryReport(listOfTransactions, inputFile.getParentFile().getAbsolutePath());
	}


	/**
	 * Read the input flat file and map each record to a POJO.
	 * 
	 * @param inputFile
	 * @param recordMapper
	 * @return
	 */
	public List<TransactionRecordBean> processInputFile(File inputFile,
			RecordMapper<TransactionRecordBean> recordMapper) {

		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("Start of processInputFile; inputFile = "+inputFile.getName());
		}
		
		List<TransactionRecordBean> transactionRecords = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {

			String flatRecord = "";
			while ((flatRecord = br.readLine()) != null) {
				Optional<TransactionRecordBean> mapRecord = recordMapper.mapRecord(flatRecord);
				if (mapRecord.isPresent()) {
					transactionRecords.add(mapRecord.get());
				}
			}

		} catch (FileNotFoundException e) {
			LOGGER.error("Unable to find the input file - " + e.getMessage());
			System.exit(1);
		} catch (IOException e) {
			LOGGER.error("Error while reading the input file - " + e.getMessage());
			System.exit(1);
		}

		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("End of processInputFile; returning transactionRecords = "+transactionRecords);
		}
		
		return transactionRecords;
	}

}
