package com.pm.demo.service;

import com.github.pagehelper.PageInfo;
import com.pm.demo.entity.Card;

import java.util.List;

public interface CardService {
    List<Card> findAll();

    Card findById(int id);

    int add(Card card);

    int del(int id);

    int upd(Card card);

    //分页
    PageInfo<Card> findByPage(int pageNum, int pageSize);
}
