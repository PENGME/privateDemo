package com.pm.demo.service.impl;

import com.pm.demo.entity.Card;
import com.pm.demo.mapper.CardMapper;
import com.pm.demo.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardMapper cardMapper;

    @Override
    public List<Card> findAll() {
        List<Card> cardList = cardMapper.findAll();
        return cardList;
    }

    @Override
    public Card findById(int id) {
        Card card = cardMapper.findById(id);
        return card;
    }

    @Override
    public int add(Card card) {
        int num=cardMapper.add(card);
        System.out.println("受影响行数："+num);
        return num;
    }

    @Override
    public int del(int id) {
        return cardMapper.del(id);
    }

    @Override
    public int upd(Card card) {
        return cardMapper.upd(card);
    }

    //字符串转时间
    public Date dateFormat(String vtime){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date=null;
        try {
            date=simpleDateFormat.parse(vtime);
        } catch(Exception px) {
            px.printStackTrace();
        }
        return date;
    }
}
