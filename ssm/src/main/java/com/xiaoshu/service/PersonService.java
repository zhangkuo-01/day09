package com.xiaoshu.service;

import java.util.List;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.CompanyMapper;
import com.xiaoshu.dao.PersonMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Company;
import com.xiaoshu.entity.Person;
import com.xiaoshu.entity.PersonVo;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;
import com.xiaoshu.entity.UserExample.Criteria;

@Service
public class PersonService {

	@Autowired
	private PersonMapper personMapper;

	@Autowired
	private CompanyMapper companyMapper;
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Autowired
	Destination queueTextDestination;
	
	public PageInfo<PersonVo> findPage(PersonVo personVo,Integer pageNum,Integer pageSize,String ordername,String order){
		PageHelper.startPage(pageNum, pageSize);
		
		List<PersonVo> list = personMapper.findList(personVo);
		
		return new PageInfo<PersonVo>(list);
	}
	public List<Company> findAll(){
		return companyMapper.selectAll();
	}
	public void addPerson(Person person) {
		// TODO Auto-generated method stub
		personMapper.insert(person);
		jmsTemplate.convertAndSend(queueTextDestination, JSONObject.toJSONString(person));
		
		
	}
	public void updatePerson(Person person) {
		// TODO Auto-generated method stub
		personMapper.updateByPrimaryKey(person);
	}
	public Person findByName(String expressName) {
		// TODO Auto-generated method stub
		Person person = new Person();
		person.setExpressName(expressName);
		return personMapper.selectOne(person);
	}
	public void deletePerson(int id) {
		// TODO Auto-generated method stub
		personMapper.deleteByPrimaryKey(id);
	}
	public List<PersonVo> findList(PersonVo personVo) {
		// TODO Auto-generated method stub
		return personMapper.findList(personVo);
	}
	public Integer findBycName(String cname) {
		// TODO Auto-generated method stub
		Company company = new Company();
		company.setExpressName(cname);
		Company selectOne = companyMapper.selectOne(company);
		return selectOne.getId();
	}
	public List<PersonVo> countPerson() {
		// TODO Auto-generated method stub
		return personMapper.countPerson();
	}
}
