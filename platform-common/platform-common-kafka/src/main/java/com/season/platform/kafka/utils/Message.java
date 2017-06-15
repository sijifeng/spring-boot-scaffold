package com.season.platform.kafka.utils;

/**
 * kafka manager 返回的数据格式
 * Created by jiyc on 2017/6/15.
 */
public class Message {
	private String record;
	private int partition;
	private long offset;

	public Message(String record, long offset, int partition) {
		this.record = record;
		this.offset = offset;
		this.partition = partition;
	}


	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public int getPartition() {
		return partition;
	}

	public void setPartition(int partition) {
		this.partition = partition;
	}
}
