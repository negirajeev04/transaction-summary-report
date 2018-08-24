package org.rajnegi.transactionsummary.transaction_summary_report;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.rajnegi.transactionsummary.transaction_summary_report.beans.TransactionRecordBean;
import org.rajnegi.transactionsummary.transaction_summary_report.exception.InvalidRecordException;
import org.rajnegi.transactionsummary.transaction_summary_report.mapper.RecordMapper;
import org.rajnegi.transactionsummary.transaction_summary_report.mapper.FlatRecordMapper;

public class TransactionSummaryTest {
	@Test
	public void recordMapperShouldReturnTransactionRecordBean() {
		FlatRecordMapper recordMapper = new FlatRecordMapper();
		String testRecord = "315CL  432100020001SGXDC FUSGX NK    20100910JPY01B 0000000001 0000000000000000000060DUSD000000000030DUSD000000000000DJPY201008200012380     688032000092500000000             O                                                                                                                               ";
		assertTrue(recordMapper.mapRecord(testRecord).get() instanceof TransactionRecordBean);
	}

	@Test(expected = InvalidRecordException.class)
	public void recordMapperShouldThrowInvalidRecordExceptionIfLengthIsNotEqualTo303() {
		FlatRecordMapper recordMapper = new FlatRecordMapper();
		String testRecord = "";
		recordMapper.mapRecord(testRecord);
	}
}
