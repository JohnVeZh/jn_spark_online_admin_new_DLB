package com.easecom.common.util;

import com.business.coupon.CouponMgr;
import com.business.coupon.dao.Coupon;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;
import java.util.TimerTask;

/**
 * 定时查询有效的优惠券 判断是否过期
 */
public class CouponTask extends TimerTask{

    public static Logger logger = Logger.getLogger(CouponTask.class);

    CouponMgr couponMgr = new CouponMgr();
    @Override
    public void run() {
        try {
            List<Coupon> couponList = couponMgr.couponList(" from Coupon  where status='1'");
            System.out.println("优惠券定时任务开始>>"+ new Date());
            for (Coupon coupon : couponList) {
                if(!CommUtil.between(new Date().getTime(),coupon.getEffectTime().getTime(),coupon.getInvalidTime().getTime())){
                    coupon.setStatus("4");
                    couponMgr.update(coupon);
                }
            }
            System.out.println("优惠券定时任务结束>>"+ new Date());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
