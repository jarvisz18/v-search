package com.ixan.boot.domain;

import com.ixan.boot.mapper.LogOneLineFileMapper;

import java.util.List;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/8/14 下午4:40
 * @description 批量任务
 */
public class ConvertTask implements Runnable {
	private List<LogFile> list;
	private LogOneLineFileMapper logOneLineFileMapper;

	public ConvertTask(List<LogFile> list, LogOneLineFileMapper logOneLineFileMapper) {
		this.list = list;
		this.logOneLineFileMapper = logOneLineFileMapper;
	}

	@Override
	public void run() {
		logOneLineFileMapper.insertList(list);
	}
}
