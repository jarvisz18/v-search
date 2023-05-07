package cn.ixan.search.service;

import cn.ixan.search.dao.SendLogRepository;
import cn.ixan.search.domain.SendLog;
import cn.ixan.search.domain.SendLogDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/7/5 10:21
 * @description 日志
 */
@Service
public class SendLogServiceImpl implements SendLogService {
	@Autowired
	private SendLogRepository sendLogRepository;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updateSendLogByIdAndType() {
		sendLogRepository.updateSendLogByIdAndType(1L, "1", "发送日志模版");
	}

	@Override
	public List<SendLog> findByCondition(String template) {
		return sendLogRepository.findByCondition(template);
	}

	@Override
	public List<SendLog> findByTypeIn(List<String> typeList) {
		return sendLogRepository.findSendLogByTypeList(typeList);
	}

	@Override
	public Page<SendLog> findAll(SendLogDTO logDto, Pageable pageable) {
		Specification specification = (root, query, criteriaBuilder) -> {
			Predicate predicate = criteriaBuilder.conjunction();
			if (!StringUtils.isEmpty(logDto.getTemplateName())) {
				predicate.getExpressions().add(criteriaBuilder.like(root.get("templateName"), logDto.getTemplateName()));
			}
			query.where(predicate);
			return predicate;
		};
		return sendLogRepository.findAll(specification, pageable);
	}

	@Override
	public List<SendLog> findAll() {
		return sendLogRepository.findAll();
	}

	@Override
	public List<SendLog> findSendLogByTemplateName(String templateName) {
		return sendLogRepository.findByTempLateName(templateName);
	}

	@Override
	public void addLog(SendLog sendLog) {
		sendLogRepository.save(sendLog);
	}

	@Override
	public Page<SendLog> findSendLogsByType(SendLogDTO logDto) {
		Assert.notNull(logDto.getPageNo(), "pageNo cannot be null");
		Assert.notNull(logDto.getPageSize(), "pageSize cannot be null");
		Pageable pageable = PageRequest.of(0, 10);
		String type = logDto.getType();
		return sendLogRepository.findSendLogsByType5(type, pageable);
	}

	@Override
	public void batchAddLog(List<SendLog> sendLogList) {
		sendLogRepository.batchInsert(sendLogList, 1000);
		//sendLogRepository.saveAll(sendLogList);
	}
}
