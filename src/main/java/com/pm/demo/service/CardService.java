package com.pm.demo.service;

import com.pm.demo.entity.Card;

import java.util.Date;
import java.util.List;

public interface CardService {
    List<Card> findAll();

    Card findById(int id);

    int add(Card card);

    int del(int id);

    int upd(Card card);

    Date dateFormat(String vtime);
}
