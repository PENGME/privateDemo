package com.pm.demo.controller;

import com.github.pagehelper.PageInfo;
import com.pm.demo.entity.Card;
import com.pm.demo.mapper.CardMapper;
import com.pm.demo.service.CardService;
import com.pm.demo.util.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CardController {
    @Autowired
    private CardService cardService;

    @GetMapping("/show")
    public String findAll(@RequestParam(required = false,defaultValue = "1")int pageNum,
                          @RequestParam(required = false,defaultValue = "2")int pageSize,ModelMap map){
        //List<Card> cardList = cardService.findAll();
        //加分页
        PageInfo<Card> cardList = cardService.findByPage(pageNum, pageSize);
        map.put("pageInfo",cardList);
        return "show";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "add";
    }

    @RequestMapping("/doAdd")
    public String doAdd(Card card,String vtime){
        FormatUtil fu=new FormatUtil();
        card.setVdate(fu.dateFormat(vtime));
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
        FormatUtil fu=new FormatUtil();
        card.setVdate(fu.dateFormat(vtime));
        cardService.upd(card);
        return "redirect:/show";
    }

    @RequestMapping("/doDelete")
    public String doDelete(int id){
        cardService.del(id);
        return "redirect:/show";
    }
}
