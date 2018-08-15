package com.ih2ome.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ih2ome.common.Exception.PinganMchException;
import com.ih2ome.common.PageVO.PinganMchVO.PinganMchRegisterReqVO;
import com.ih2ome.common.PageVO.PinganMchVO.PinganMchRegisterResVO;
import com.ih2ome.common.utils.BeanMapUtil;
import com.ih2ome.common.utils.pingan.SerialNumUtil;
import com.ih2ome.service.PinganMchService;
import com.pabank.sdk.PABankSDK;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Map;

/**
 * @author Sky
 * create 2018/08/09
 * email sky.li@ixiaoshuidi.com
 **/
@Service
public class PinganMchServiceImpl implements PinganMchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PinganMchServiceImpl.class);


    //资金汇总账号
    @Value("${pingan.mch.mainAcctNo}")
    private String mainAcctNo;

    //文件传输用户短号
    @Value("${pingan.wxPay.uid}")
    private String uid;

    /**
     * 平安开通商户子账户
     *
     * @param userId
     * @return
     * @throws PinganMchException
     */
    @Override
    public PinganMchRegisterResVO registerAccount(Integer userId) throws PinganMchException, IOException {
        PinganMchRegisterReqVO pinganMchRegisterReqVO = new PinganMchRegisterReqVO();
        pinganMchRegisterReqVO.setCnsmrSeqNo(uid + SerialNumUtil.generateSerial());
        pinganMchRegisterReqVO.setFunctionFlag("1");
        pinganMchRegisterReqVO.setFundSummaryAcctNo(mainAcctNo);
        pinganMchRegisterReqVO.setTranNetMemberCode(userId.toString());
        pinganMchRegisterReqVO.setMemberProperty("00");
        //开通商户子账户请求数据报文
        String reqJson = JSONObject.toJSONString(pinganMchRegisterReqVO);
        LOGGER.info("registerAccount--->请求数据:{}", reqJson);
        Map<String, Object> result = PABankSDK.getInstance().apiInter(reqJson, "OpenCustAcctId");
        LOGGER.info("registerAccount--->响应数据:{}", result);
        String code = (String) result.get("TxnReturnCode");
        if (!code.equals("000000")) {
            String txnReturnMsg = (String) result.get("TxnReturnMsg");
            LOGGER.error("registerAccount--->平安开通商户子账户失败,失败原因:{}", txnReturnMsg);
            throw new PinganMchException(txnReturnMsg);
        }
        PinganMchRegisterResVO resVO = BeanMapUtil.mapToObject(result, PinganMchRegisterResVO.class);
        return resVO;
    }
}
