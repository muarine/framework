package com.framework.excel;



import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.muarine.common.entity.MpNews;
import com.muarine.common.mapper.MpNewsMapper;

import junit.framework.TestCase;

/**
 * TestExportTable.
 * 
 * @author muarine maoyun@rtmap.com
 * @since 0.1
 */
public class TestExportTable extends TestCase{
	// 1. 初始化spring配置,依赖注入Bean
	private static ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	/**
	 * 1. 查询表数据
	 * 2. 导出表数据到excel
	 * @throws FileNotFoundException 
	 */
	public void testExportTable() throws FileNotFoundException{
		// 2. 加载Bean 
		MpNewsMapper mapper = ctx.getBean(MpNewsMapper.class);
		// 3. 查询数据列表 news
		List<MpNews> news = mapper.selectByParentId(4L);
		
		for (MpNews mpNews : news) {
			System.out.println(JSON.toJSONString(mpNews));
		}
		// 4. 封装Excel
		TestExportExcel<MpNews> excel = new TestExportExcel<MpNews>();
		// 2.1 新建文件流
		OutputStream out = new FileOutputStream("/home/muarine/tmp/export_table.xls");
		
		String[] headers2 = { "表的主键ID", "标题", "父类ID", "简介", "图片地址",
				"跳转地址", "详情内容", "创建时间", "修改时间" };
		excel.exportExcel(headers2 , news , out);
		
		System.exit(0);
	}
	
	
	
}