package com.ixan.boot.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.ixan.boot.domain.ChinaRegions;
import com.ixan.boot.service.ChinaRegionsService;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/10/7 14:25
 * @description ChinaRegionsController
 * @version 1.0
 */
@RestController
public class ChinaRegionsController {
	private static final Logger logger = LoggerFactory.getLogger(ChinaRegionsController.class);
	//需要抓取的网页地址
	@Value("${china.regions}")
	private String URL;
	@Value("${class.type}")
	private String className;
	@Autowired
	private ChinaRegionsService chinaRegionsService;

	@PutMapping("/regions/pull")
	public void pullRegions() throws IOException {
		List<ChinaRegions> regionsInfoList = getChinaRegionsInfos();
		//打印结果
		logger.info(JSONArray.toJSONString(regionsInfoList));
		chinaRegionsService.saveBatch(regionsInfoList);
	}

	private List<ChinaRegions> getChinaRegionsInfos() throws IOException {
		List<ChinaRegions> regionsInfoList = new ArrayList<>();
		//抓取网页信息
		Document document = Jsoup.connect(URL).get();
		//获取真实的数据体
		Element element = document.getElementsByTag("tbody").get(0);
		String provinceCode = "";//省级编码
		String cityCode = "";//市级编码
		if (Objects.nonNull(element)) {
			Elements trs = element.getElementsByTag("tr");
			for (int i = 3; i < trs.size(); i++) {
				Elements tds = trs.get(i).getElementsByTag("td");
				if (tds.size() < 3) {
					continue;
				}
				Element td1 = tds.get(1);//行政区域编码
				Element td2 = tds.get(2);//行政区域名称
				if (StringUtils.isNotEmpty(td1.text())) {
					if (td1.classNames().contains(className)) {
						if (td2.toString().contains("span")) {
							//市级
							ChinaRegions chinaRegions = new ChinaRegions();
							chinaRegions.setCode(td1.text());
							chinaRegions.setName(td2.text());
							chinaRegions.setType(2);
							chinaRegions.setParentCode(provinceCode);
							regionsInfoList.add(chinaRegions);
							cityCode = td1.text();
						} else {
							//省级
							ChinaRegions chinaRegions = new ChinaRegions();
							chinaRegions.setCode(td1.text());
							chinaRegions.setName(td2.text());
							chinaRegions.setType(1);
							chinaRegions.setParentCode("");
							regionsInfoList.add(chinaRegions);
							provinceCode = td1.text();
						}

					} else {
						//区或者县级
						ChinaRegions chinaRegions = new ChinaRegions();
						chinaRegions.setCode(td1.text());
						chinaRegions.setName(td2.text());
						chinaRegions.setType(3);
						chinaRegions.setParentCode(StringUtils.isNotEmpty(cityCode) ? cityCode : provinceCode);
						regionsInfoList.add(chinaRegions);
					}
				}
			}
		}
		return regionsInfoList;
	}
}
