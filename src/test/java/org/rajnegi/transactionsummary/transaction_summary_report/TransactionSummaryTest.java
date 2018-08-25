package org.rajnegi.transactionsummary.transaction_summary_report;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;
import org.rajnegi.transactionsummary.transaction_summary_report.beans.TransactionRecordBean;
import org.rajnegi.transactionsummary.transaction_summary_report.mapper.FlatRecordMapper;
import org.rajnegi.transactionsummary.transaction_summary_report.writer.CSVWriter;

public class TransactionSummaryTest {
	
	@Test
	public void inputFlatFileShouldBeProcessedToListOfTransactionRecordBean() {
		
		File inputFile = new File(this.getClass().getResource("/Input.txt").getFile());
		TransactionSummary transactionSummary = new TransactionSummary();
		FlatRecordMapper mapper = new FlatRecordMapper();
		List<TransactionRecordBean> listOfTransactions = transactionSummary.processInputFile(inputFile, mapper::mapRecord);
		
		assertTrue(listOfTransactions!=null);
		assertTrue(listOfTransactions.size()>0);
	}
	
	@Test
	public void recordMapperShouldReturnOptionalOfNullIfLengthIsNotEqualTo176() {
		FlatRecordMapper recordMapper = new FlatRecordMapper();
		String testRecord = "";
		Optional<TransactionRecordBean> result = recordMapper.mapRecord(testRecord);
		assertTrue(!result.isPresent());
	}
	
	@Test
	public void recordMapperShouldReturnTransactionRecordBean() {
		FlatRecordMapper recordMapper = new FlatRecordMapper();
		String testRecord = "315CL  432100020001SGXDC FUSGX NK    20100910JPY01B 0000000001 0000000000000000000060DUSD000000000030DUSD000000000000DJPY201008200012380     688032000092500000000             O                                                                                                                               ";
		assertThat(recordMapper.mapRecord(testRecord).get(), IsInstanceOf.instanceOf(TransactionRecordBean.class));
	}

	@Test
	public void recordMapperShouldMapFlatFieldsToTransactionRecordBean() {
		FlatRecordMapper recordMapper = new FlatRecordMapper();
		String testRecord = "315CL  432100020001SGXDC FUSGX NK    20100910JPY01B 0000000001 0000000000000000000060DUSD000000000030DUSD000000000000DJPY201008200012380     688032000092500000000             O                                                                                                                               ";
		Optional<TransactionRecordBean> result = recordMapper.mapRecord(testRecord);
		TransactionRecordBean transactionRecordBean = result.get();
		
		assertThat("315", IsEqual.equalTo(transactionRecordBean.getRecordCode()));
		assertThat("CL", IsEqual.equalTo(transactionRecordBean.getClientType().trim()));
		assertThat("4321", IsEqual.equalTo(transactionRecordBean.getClientNumber().trim()));
		assertThat("0002", IsEqual.equalTo(transactionRecordBean.getAccountNumber().trim()));
		assertThat("0001", IsEqual.equalTo(transactionRecordBean.getSubAccountNumber().trim()));
		assertThat("SGXDC", IsEqual.equalTo(transactionRecordBean.getOppPartyCode().trim()));
		assertThat("FU", IsEqual.equalTo(transactionRecordBean.getProductGroupCode().trim()));
		assertThat("SGX", IsEqual.equalTo(transactionRecordBean.getExchangeCode().trim()));
		assertThat("NK", IsEqual.equalTo(transactionRecordBean.getSymbol().trim()));
		assertThat("20100910", IsEqual.equalTo(transactionRecordBean.getExpirationDate().trim()));
		assertThat("JPY", IsEqual.equalTo(transactionRecordBean.getCurrencyMode().trim()));
		assertThat("01", IsEqual.equalTo(transactionRecordBean.getMovementCode().trim()));
		assertThat('B', IsEqual.equalTo(transactionRecordBean.getBuySellCode()));
		assertThat(1L, IsEqual.equalTo(transactionRecordBean.getQuantityLong()));
		assertThat(0L, IsEqual.equalTo(transactionRecordBean.getQuantityShort()));
		assertThat(0.6D, IsEqual.equalTo(transactionRecordBean.getExchBrokerFee()));
		assertThat('D', IsEqual.equalTo(transactionRecordBean.getExchBrokerFeeDorC()));
		assertThat("USD", IsEqual.equalTo(transactionRecordBean.getExchBrokerFeeCurrCode()));
		assertThat(0.0D, IsEqual.equalTo(transactionRecordBean.getCommission()));
		assertThat('D', IsEqual.equalTo(transactionRecordBean.getCommissionDorC()));
		assertThat("JPY", IsEqual.equalTo(transactionRecordBean.getCommissionCurrCode()));
		assertThat("20100820", IsEqual.equalTo(transactionRecordBean.getTransactionDate()));
		assertThat(1238L, IsEqual.equalTo(transactionRecordBean.getFutureReference()));
		assertThat("0", IsEqual.equalTo(transactionRecordBean.getTicketNumber().trim()));
		assertThat(688032L, IsEqual.equalTo(transactionRecordBean.getExternalNumber()));
		assertThat(9250D, IsEqual.equalTo(transactionRecordBean.getTransactionPrice()));
		assertThat('O', IsEqual.equalTo(transactionRecordBean.getOpenCloseCode()));
	}
	
	@Test
	public void givenListOfTransactionsCSVWriterShouldGenerateACSVFile() {
		
		File inputFile = new File(this.getClass().getResource("/Input.txt").getFile());
		TransactionSummary transactionSummary = new TransactionSummary();
		FlatRecordMapper mapper = new FlatRecordMapper();
		List<TransactionRecordBean> listOfTransactions = transactionSummary.processInputFile(inputFile, mapper::mapRecord);
		
		String outputFilePath = inputFile.getParent()+"/output.csv";
		
		File csvFile = new File(outputFilePath);
		assertTrue(!csvFile.exists());//File should not exists before
		CSVWriter.generateSummaryReport(listOfTransactions, inputFile.getParent());
		assertTrue(csvFile.exists());
	}
	
}
