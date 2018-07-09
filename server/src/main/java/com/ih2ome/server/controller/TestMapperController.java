package com.ih2ome.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.ih2ome.common.support.ResponseBodyVO;
import com.ih2ome.dao.caspain.CaspainMoneyFlowDao;
import com.ih2ome.dao.caspain.ConfigPaymentsChannelDao;
import com.ih2ome.dao.volga.VolgaMoneyFlowDao;
import com.ih2ome.model.caspain.ConfigPaymentsChannel;
import com.ih2ome.model.volga.MoneyFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Sky
 * create 2018/07/06
 * email sky.li@ixiaoshuidi.com
 **/
@RestController
@RequestMapping("/test")
public class TestMapperController {

    @Autowired
    private ConfigPaymentsChannelDao configPaymentsChannelDao;
    @Autowired
    private VolgaMoneyFlowDao volgaMoneyFlowDao;
    @Autowired
    private CaspainMoneyFlowDao caspainMoneyFlowDao;

    @GetMapping("/one")
    @ResponseBody
    public ResponseBodyVO test01() {
        //��ҳ���
        PageHelper.startPage(0, 2);
        List<ConfigPaymentsChannel> configPaymentsChannels = configPaymentsChannelDao.selectAll();
        ConfigPaymentsChannel configPaymentsChannel = configPaymentsChannelDao.selectByPrimaryKey((Integer) 2);
        System.out.println(configPaymentsChannel);
        ResponseBodyVO responseBodyVO = new ResponseBodyVO();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello", "world");
        jsonObject.put("www", configPaymentsChannels);
        responseBodyVO.setData(jsonObject);
        responseBodyVO.setCode(0);
        responseBodyVO.setMsg("�ɹ�");
        return responseBodyVO;
    }

    @GetMapping("two")
    @ResponseBody
    public ResponseBodyVO test02() {
        ResponseBodyVO responseBodyVO = new ResponseBodyVO();
        MoneyFlow volgaMoneyFlow = volgaMoneyFlowDao.selectByPrimaryKey(1);
        Example example = new Example(MoneyFlow.class);
        example.createCriteria().andEqualTo("feeType", 3);
        volgaMoneyFlowDao.selectByExample(example);
        com.ih2ome.model.caspain.MoneyFlow caspainMoneyFlow = caspainMoneyFlowDao.selectByPrimaryKey(10);
//        List<com.ih2ome.model.caspain.MoneyFlow> fee_types = caspainMoneyFlowDao.selectByExample(new Example(com.ih2ome.model.caspain.MoneyFlow.class).createCriteria().andEqualTo("feeType", 3));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("caspainMoneyFlow", caspainMoneyFlow);
        ResponseBodyVO.generateResponseObject(0, jsonObject, "success");
        responseBodyVO.setCode(0);
        responseBodyVO.setMsg("�ɹ�");
        return ResponseBodyVO.generateResponseObject(0, jsonObject, "success");

    }

}