package com.leyou.item.service;

import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecificationService {
    @Autowired
    SpecGroupMapper specGroupMapper;

    @Autowired
    SpecParamMapper specParamMapper;


    public List<SpecGroup> queryGroupByCid(Long cid) {

        SpecGroup record = new SpecGroup();
        record.setCid(cid);

        return this.specGroupMapper.select(record);
    }

    public List<SpecParam> queryParams(Long gid) {
        SpecParam record = new SpecParam();
        record.setGroupId(gid);

        return this.specParamMapper.select(record);
    }
}
