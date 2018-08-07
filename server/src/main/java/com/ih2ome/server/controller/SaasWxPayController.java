package com.ih2ome.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.ih2ome.common.Exception.PinganApiException;
import com.ih2ome.common.Exception.SaasWxPayException;
import com.ih2ome.common.PageVO.SaasWxNotifyReqVO;
import com.ih2ome.common.PageVO.SaasWxPayOrderReqVO;
import com.ih2ome.common.PageVO.SaasWxPayOrderResVO;
import com.ih2ome.common.support.ResponseBodyVO;
import com.ih2ome.service.PinganApiService;
import com.ih2ome.service.SaasWxPayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Sky
 * create 2018/08/01
 * email sky.li@ixiaoshuidi.com
 **/
@Api(description = "Saas微信支付接口")
@RestController
@RequestMapping("/saas/wx")
@CrossOrigin
public class SaasWxPayController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigPaymentsController.class);

    @Autowired
    private SaasWxPayService saasWxPayService;


    @PostMapping(value = "placeOrder", produces = "application/json;charset=UTF-8")
    @ApiOperation("微信下单接口")
    public ResponseBodyVO placeOrder(@RequestBody @Valid SaasWxPayOrderReqVO reqVO, BindingResult bindingResult) {
        JSONObject data = new JSONObject();
        LOGGER.info("placeOrder--->请求参数:{}", reqVO.toString());
        //验证参数
        if (bindingResult.hasErrors()) {
            return ResponseBodyVO.generateResponseObject(-1, data, "参数异常");
        }
        try {
            SaasWxPayOrderResVO resVO = saasWxPayService.placeOrder(reqVO);
            data.put("payInfo", resVO);
        } catch (SaasWxPayException e) {
            e.printStackTrace();
            LOGGER.error("placeOrder--->水滴下单失败,请求参数:{},失败原因:{}", reqVO.toString(), e.getMessage());
            return ResponseBodyVO.generateResponseObject(-1, data, e.getMessage());
        } catch (PinganApiException e) {
            e.printStackTrace();
            LOGGER.error("placeOrder--->平安下单失败,请求参数:{},信息:{}", reqVO.toString(), e.getMessage());
            return ResponseBodyVO.generateResponseObject(-1, data, e.getMessage());
        }
        return ResponseBodyVO.generateResponseObject(0, data, "下单成功");
    }


    @PostMapping(value = "notify", produces = "application/json;charset=UTF-8")
    @ApiOperation("支付成功回调")
    public String notify(SaasWxNotifyReqVO saasWxNotifyReqVO) {
        LOGGER.info("notify--->平安微信支付回调参数:{}", saasWxNotifyReqVO.toString());
        Boolean flag = saasWxPayService.notify(saasWxNotifyReqVO);
        if (flag) {
            return "notify_success";
        }
        return "notify_error";
    }
}