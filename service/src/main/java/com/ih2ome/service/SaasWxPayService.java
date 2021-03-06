package com.ih2ome.service;

import com.ih2ome.common.Exception.PinganWxPayException;
import com.ih2ome.common.Exception.SaasWxPayException;
import com.ih2ome.common.PageVO.SaasWxNotifyReqVO;
import com.ih2ome.common.PageVO.SaasWxPayOrderReqVO;
import com.ih2ome.common.PageVO.SaasWxPayOrderResVO;
import com.ih2ome.model.lijiang.SubAccount;

import java.io.UnsupportedEncodingException;

/**
 * @author Sky
 * create 2018/08/01
 * email sky.li@ixiaoshuidi.com
 **/
public interface SaasWxPayService {


    /**
     * saas微信端下单
     *
     * @param reqVO
     * @param subAccount
     * @return
     * @throws SaasWxPayException
     */
    SaasWxPayOrderResVO placeOrder(SaasWxPayOrderReqVO reqVO, SubAccount subAccount) throws SaasWxPayException, PinganWxPayException, UnsupportedEncodingException;

    /**
     * 支付成功回调
     *
     * @param saasWxNotifyReqVO
     * @return
     */
    Boolean notify(SaasWxNotifyReqVO saasWxNotifyReqVO);
}
