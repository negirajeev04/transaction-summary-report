package org.rajnegi.transactionsummary.transaction_summary_report.mapper;

import java.util.Optional;

@FunctionalInterface
public interface RecordMapper<T> {

	/**
	 * Maps a flat record to POJO of type T
	 * @param record
	 * @return
	 */
	Optional<T> mapRecord(String record);
	
}
