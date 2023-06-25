package cn.ixan.mybatis.mapper;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Map;

public class SendLogMapperTest {
	private SqlSession sqlSession;
	private SendLogMapper mapper;

	@Before
	public void before() throws Exception {
		InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		sqlSession = sqlSessionFactory.openSession();
		mapper = sqlSession.getMapper(SendLogMapper.class);
	}


	@Test
	public void getLogCount() {
		Integer logCount = mapper.getLogCount();
		System.out.println(logCount);
	}

	@Test
	public void findById() {
		Map<String, Object> resultMap = mapper.findById(1);
		System.out.println(resultMap);
	}

	@Test
	public void findByTypeLike() {
		Map<String, Object> resultMap = mapper.findByTypeLike("64");
		System.out.println(resultMap);
	}


	@After
	public void after() {
		sqlSession.close();
	}
}
