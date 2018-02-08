package com.itcase.dao;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.itcase.Exception.StudentNotExistException;
import com.itcase.domain.Student;
import com.itcase.util.XmlUtils;

public class StudentDao {

	/*
	 * <student idcard="111" examid="222"> <name>张三</name>
	 * <location>沈阳</location> <grade>89</grade> </student>
	 * 
	 */

	public void add(Student student) {
		try {
			Document document = XmlUtils.getDocument();
			// 创建出封装学生信息的标签student，并设置属性；
			Element student_node = document.createElement("student");
			student_node.setAttribute("examid", student.getExamid());
			student_node.setAttribute("idcard", student.getIdcard());
			// 创建用于封装学生姓名、所在地和成绩的标签；
			Element name = document.createElement("name");
			Element location = document.createElement("location");
			Element grade = document.createElement("grade");
			// 填充内容
			name.setTextContent(student.getName());
			location.setTextContent(student.getLocation());
			grade.setTextContent(student.getGrade() + "");
			// 组合起来
			student_node.appendChild(name);
			student_node.appendChild(location);
			student_node.appendChild(grade);
			// 把封装了信息的学生标签，挂到文档上；
			document.getElementsByTagName("exam").item(0).appendChild(student_node);
			// 将更新写回硬盘
			XmlUtils.writeBack(document);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void delete(String name) throws StudentNotExistException {
		try {
			Document document = XmlUtils.getDocument();
			NodeList list = document.getElementsByTagName("name");
			for (int i = 0; i < list.getLength(); i++) {
				Element name_tag = (Element) list.item(i);
				if (name_tag.getTextContent().equals(name)) {
					//删除标签；
					list.item(i).getParentNode().getParentNode().removeChild(list.item(i).getParentNode());
					XmlUtils.writeBack(document);
					return;
				}
			}
			//抛出自定义异常；
			throw new StudentNotExistException(name + "不存在！");
		} catch (StudentNotExistException e) {
			throw e; //将编译时异常返回给上一层；不需要修改方法的声明；
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Student find(String examid) {
		try {
			Document document = XmlUtils.getDocument();
			// 得到所有的学生标签
			NodeList list = document.getElementsByTagName("student");
			for (int i = 0; i < list.getLength(); i++) {
				Element student_node = (Element) list.item(i);
				if (student_node.getAttribute("examid").equals(examid)) {
					// 找到匹配的学生；创建一个Student对象来封装该学生的信息；
					Student stu = new Student();
					stu.setExamid(examid);
					stu.setIdcard(student_node.getAttribute("idcard"));
					stu.setName(student_node.getElementsByTagName("name").item(0).getTextContent());
					stu.setLocation(student_node.getElementsByTagName("location").item(0).getTextContent());
					stu.setGrade(
							Double.parseDouble(student_node.getElementsByTagName("grade").item(0).getTextContent()));
					return stu;
				}
			}
			return null;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
