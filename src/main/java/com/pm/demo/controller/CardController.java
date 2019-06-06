package com.pm.demo.controller;

import com.pm.demo.entity.Card;
import com.pm.demo.mapper.CardMapper;
import com.pm.demo.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class CardController {
    @Autowired
    private CardService cardService;

    @GetMapping("/show")
    public String findAll(ModelMap map){
        List<Card> cardList = cardService.findAll();
        map.put("cardList",cardList);
        return "show";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "add";
    }

    @RequestMapping("/doAdd")
    public String doAdd(Card card,String vtime){
        card.setVdate(cardService.dateFormat(vtime));
        cardService.add(card);
        return "redirect:/show";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(ModelMap map,int id){
        Card card = cardService.findById(id);
        map.put("card",card);
        return "update";
    }

    @RequestMapping("/doUpdate")
    public String doUpdate(Card card,String vtime){
        card.setVdate(cardService.dateFormat(vtime));
        cardService.upd(card);
        return "redirect:/show";
    }

    @RequestMapping("/doDelete")
    public String doDelete(int id){
        cardService.del(id);
        return "redirect:/show";
    }
}
