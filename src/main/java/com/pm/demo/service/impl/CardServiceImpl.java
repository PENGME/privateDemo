package com.pm.demo.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pm.demo.entity.Card;
import com.pm.demo.mapper.CardMapper;
import com.pm.demo.service.CardService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    private CardMapper cardMapper;

    public CardServiceImpl(CardMapper cardMapper) {
        this.cardMapper = cardMapper;
    }

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
        int num = cardMapper.add(card);
        System.out.println("受影响行数：" + num);
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

    //该方法查询到的数据集合，最终将以 emp.1、emp.2 形式的key存储在内存中
    //当下一次调用该方法时，发现 emp.1 key时，直接从缓存中获取数据，该方法将不再执行
    @Cacheable(value = "emp.page", key = "#p0")
    @Override
    public PageInfo<Card> findByPage(int pageNum, int pageSize) {
        Page<Card> page = PageHelper.startPage(pageNum, pageSize);
        cardMapper.findAll();
        PageInfo<Card> pageInfo = new PageInfo<>(page);
        return pageInfo;
    }

}
