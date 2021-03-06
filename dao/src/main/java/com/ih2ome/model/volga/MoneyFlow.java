package com.ih2ome.model.volga;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Entity
@Table(name = "money_flow")
public class MoneyFlow {

 @Id
 @Column(name = "id")
  private Integer id;
 @Column(name = "uuid")
  private String uuid;
 @Column(name = "created_at")
  private Date createdAt;
 @Column(name = "is_delete")
  private Integer isDelete;
 @Column(name = "flow_type")
  private Integer flowType;
 @Column(name = "fee_type")
  private Integer feeType;
 @Column(name = "real_amount")
  private Double realAmount;
 @Column(name = "ought_amount")
  private Double oughtAmount;
 @Column(name = "trade_no")
  private String tradeNo;
 @Column(name = "trade_code")
  private String tradeCode;
 @Column(name = "pay_method")
  private Integer payMethod;
 @Column(name = "source_from")
  private Integer sourceFrom;
 @Column(name = "order_uuid")
  private String orderUuid;
 @Column(name = "is_whole_house")
  private Integer isWholeHouse;
 @Column(name = "trade_at")
  private Date tradeAt;
 @Column(name = "trader_name")
  private String traderName;
 @Column(name = "remark")
  private String remark;
 @Column(name = "room_id")
  private Integer roomId;
 @Column(name = "apartment_id")
  private Integer apartmentId;
 @Column(name = "created_by")
  private Integer createdBy;
 @Column(name = "operator_id")
  private Integer operatorId;
 @Column(name = "deleted_at")
  private Date deletedAt;
 @Column(name = "deleted_by")
  private Integer deletedBy;
 @Column(name = "deleted_type")
  private Integer deletedType;
 @Column(name = "trade_serial_no")
  private String tradeSerialNo;
 @Column(name = "transaction_id")
  private String transactionId;
 @Column(name = "management_id")
  private Integer managementId;
 @Column(name = "payment_id")
  private Integer paymentId;

}
