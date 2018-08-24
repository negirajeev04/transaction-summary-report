package org.rajnegi.transactionsummary.transaction_summary_report;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.rajnegi.transactionsummary.transaction_summary_report.beans.TransactionRecordBean;
import org.rajnegi.transactionsummary.transaction_summary_report.mapper.RecordMapper;
import org.rajnegi.transactionsummary.transaction_summary_report.mapper.FlatRecordMapper;

/**
 * Hello world!
 *
 */
public class TransactionSummary 
{
	private static final Logger LOGGER = Logger.getLogger(TransactionSummary.class);
	
    public static void main( String[] args ) throws IOException
    {
    	Properties props = new Properties();
    	props.load(TransactionSummary.class.getResourceAsStream("/log4j.properties"));
    	PropertyConfigurator.configure(props);
        
        if(args == null || args.length < 1) {
        	LOGGER.error("Missing the input flat file path. Please mention absolute path for the file as part of the program arguments");
        	System.exit(1);
        }
        
        File inputFile = new File(args[0]);
        TransactionSummary summary = new TransactionSummary();
        FlatRecordMapper mapper = new FlatRecordMapper();
        List<TransactionRecordBean> listOfTransactions = summary.processInputFile(inputFile, mapper::mapRecord);
        
        Map<String, List<TransactionRecordBean>> collectByClient = listOfTransactions.stream()
        				  .collect(Collectors.groupingBy(tranx -> tranx.getClientType()+"_"+tranx.getClientNumber()+"_"+tranx.getAccountNumber()+"_"+tranx.getSubAccountNumber()));
        
        //collectByClient.forEach((client, tranx) -> System.out.printf("Client - %s; tranx : %s\n",client,tranx));
        
        Map<String, List<TransactionRecordBean>> collectByProduct = listOfTransactions.stream()
				  .collect(Collectors.groupingBy(tranx -> tranx.getProductGroupCode()+"_"+tranx.getExchangeCode()+"_"+tranx.getSymbol()+"_"+tranx.getExpirationDate()));

        //collectByProduct.forEach((product, tranx) -> System.out.printf("product - %s; tranx : %s\n",product,tranx));
        
        Map<String, Map<String, List<TransactionRecordBean>>> groupByClientAndProduct = listOfTransactions.stream()
		  .collect(Collectors.groupingBy(tranx -> tranx.getClientType().trim()+"_"+tranx.getClientNumber().trim()+"_"+tranx.getAccountNumber().trim()+"_"+tranx.getSubAccountNumber().trim()
				  , Collectors.groupingBy(tranx -> tranx.getProductGroupCode().trim()+"_"+tranx.getExchangeCode().trim()+"_"+tranx.getSymbol().trim()+"_"+tranx.getExpirationDate().trim())));
        
        System.out.println(groupByClientAndProduct);
        
    }

    
    /**
     * Read the input flat file and map each record to a POJO.
     * @param inputFile
     * @param recordMapper
     * @return
     */
	private List<TransactionRecordBean> processInputFile(File inputFile, RecordMapper<TransactionRecordBean> recordMapper) {

		List<TransactionRecordBean> transactionRecords = new ArrayList<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(inputFile))){
			
			String flatRecord="";
			while((flatRecord = br.readLine()) != null) {
				Optional<TransactionRecordBean> mapRecord = recordMapper.mapRecord(flatRecord);
				if(mapRecord.isPresent()) {
					transactionRecords.add(mapRecord.get());
				}
			}
			
		} catch (FileNotFoundException e) {
			LOGGER.error("Unable to find the input file - "+e.getMessage());
			System.exit(1);
		} catch (IOException e) {
			LOGGER.error("Error while reading the input file - "+e.getMessage());
			System.exit(1);
		}
		
		return transactionRecords;
	}

}
