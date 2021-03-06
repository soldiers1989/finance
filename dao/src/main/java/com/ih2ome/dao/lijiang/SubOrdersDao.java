package com.ih2ome.dao.lijiang;

import com.ih2ome.common.PageVO.PinganMchVO.PinganMchAccRegulationReqVO;
import com.ih2ome.dao.BaseDao;
import com.ih2ome.model.lijiang.SubOrders;
import org.springframework.stereotype.Repository;

/**
 * @author Sky
 * create 2018/08/06
 * email sky.li@ixiaoshuidi.com
 **/
@Repository
public interface SubOrdersDao extends BaseDao<SubOrders> {
    /**
     * 根据子订单号
     *
     * @param outNo
     * @return
     */
    PinganMchAccRegulationReqVO selectRegulationAccount(String outNo);
}
