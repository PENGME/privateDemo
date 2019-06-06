package com.pm.demo.mapper;

import com.pm.demo.entity.Card;

import java.util.List;

public interface CardMapper {
    List<Card> findAll();

    Card findById(int id);

    int add(Card card);

    int del(int id);

    int upd(Card card);
}
