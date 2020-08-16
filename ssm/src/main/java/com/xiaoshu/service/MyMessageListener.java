package com.xiaoshu.service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.xiaoshu.dao.PersonMapper;
import com.xiaoshu.entity.Person;

import redis.clients.jedis.Jedis;

public class MyMessageListener implements MessageListener{

	@Autowired
	PersonMapper personMapper;
	
	@Override
	public void onMessage(Message message) {
		//接收消息
		TextMessage msg = (TextMessage) message;
		//获取消息
		try {
			String json = msg.getText();
			System.out.println("<<<<<<<<<<<<<<<<<<人员信息>>>>>>>>>>>>>>>>>>:"+json);
			Person person = JSONObject.parseObject(json,Person.class);
			
			Jedis jedis=new Jedis("127.0.0.1", 6379);
			person=personMapper.selectOne(person);
			
			//jedis.set(person.getExpressName(),person.getId()+"");
			jedis.hset("人员信息", person.getExpressName(), person.getId()+"");
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
