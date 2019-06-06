package com.pm.demo.mapper;

import com.pm.demo.entity.Execel;
import java.util.List;

public interface ExecelMapper {

    List<Execel> findAll();

    int add(Execel execel);

    //删除所有记录
    int del();
}
