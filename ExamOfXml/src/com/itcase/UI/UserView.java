package com.itcase.UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.itcase.Exception.StudentNotExistException;
import com.itcase.dao.StudentDao;
import com.itcase.domain.Student;

public class UserView {
	public static StudentDao dao = new StudentDao();

	public static void main(String[] args) {

		System.out.println("添加学生 (a)  查找学生(b)  删除学生(c)");
		System.out.print("请输入操作类型：");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String type;
		try {
			type = br.readLine();
			switch (type) {
			case "a": {
				try {					
					System.out.print("请输入添加学生的信息：");
					System.out.print("学生姓名：");
					String name = br.readLine();
					System.out.print("学生准考证号：");
					String examid = br.readLine();
					System.out.print("学生身份证号：");
					String idcard = br.readLine();
					System.out.print("学生所在地：");
					String location = br.readLine();
					System.out.print("学生成绩：");
					String grade = br.readLine();

					Student student = new Student();
					student.setExamid(examid);
					student.setGrade(Double.parseDouble(grade));
					student.setIdcard(idcard);
					student.setLocation(location);
					student.setName(name);

					dao.add(student);
					System.out.println("恭喜，录入成功！！！");
				} catch (Exception e) {
					System.out.println("对不起，录入失败！！");
				}
			}
				break;
			case "b": {
				System.out.print("请输入查询学生准考证号：");
				String examid = br.readLine();
				Student s = dao.find(examid);
				if (s != null) {
					System.out.print("学生姓名：" + s.getName());
					System.out.print("准考证号： " + s.getExamid());
					System.out.print("身份证号：" + s.getIdcard());
					System.out.print("所在地：" + s.getLocation());
					System.out.print("成绩：" + s.getGrade());
				} else {
					System.out.println("查无此人！");
				}
			}
				break;
			case "c": {
				System.out.print("请输入删除学生姓名：");
				String name = br.readLine();
				try {
					dao.delete(name);
					System.out.println("----已成功删除该学生信息----");
				} catch (StudentNotExistException e) {
					System.out.println("您要删除的用户不存在！！");
				}
			}
				break;
			default:
				System.out.println("不支持此类操作！！！");
			}
		} catch (IOException e) {
			System.out.println("啊偶！侬出错了！");
			e.printStackTrace();
		}

	}

}
