package org.rajnegi.transactionsummary.transaction_summary_report;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
		
		Path path = Paths.get(inputFile.toURI());
		try (Stream<String> lineStream = Files.lines(path)){
			
			List<TransactionRecordBean> transactionRecords = lineStream.map(record -> recordMapper.mapRecord(record).get())
					  .collect(Collectors.toList());
			
			return transactionRecords;
			
		} catch (IOException e) {
			LOGGER.error("Error while reading the input file - " + e.getMessage());
			System.exit(1);
		}
		
		return Collections.<TransactionRecordBean>emptyList();
	}

}
