package com.pm.demo.service.impl;

import com.pm.demo.entity.Execel;
import com.pm.demo.mapper.ExecelMapper;
import com.pm.demo.service.ExecelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExecelServiceImpl implements ExecelService {
    @Autowired
    private ExecelMapper execelMapper;

    @Override
    public List<Execel> findAll() {
        return execelMapper.findAll();
    }

    @Override
    public int add(Execel execel) {
        return execelMapper.add(execel);
    }

    @Override
    public int del() {
        return execelMapper.del();
    }
}
