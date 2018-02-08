package util;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;


/*通过配置文件反向在数据库中生成测试表*/
public class ExportSchema {

	public static void main(String[] args) {
		Configuration cfg = new Configuration().configure();
		SchemaExport se = new SchemaExport(cfg);
		//打到控制台，输出到数据库
		se.create(true, true);
	}

}
