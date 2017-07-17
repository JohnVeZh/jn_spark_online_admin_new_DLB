package com.business.Dlb.Message;

import com.easecom.common.util.JPushClient;
import com.easecom.common.util.LogUtils;
import org.json.JSONObject;

import javax.servlet.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by qiu on 2017/7/10.
 */
public class TbMessageSchedule implements Servlet {
    private static LogUtils logger = LogUtils.getLogger(TbMessageSchedule.class);
    TbMessageMgr mgr = new TbMessageMgr();

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        logger.info("TbMessageSchedule__started");

        Runnable runnable = new Runnable() {
            public void run() {
                // task to run goes here
                try{
                    logger.info("TbMessageSchedule_Thread__started");
                    List<TbMessage> list =  mgr.listWillSend();
                    if(list!=null){
                        logger.info("list size:"+list.size());
                        for(TbMessage m : list){
                            logger.info(m.toString());
                            Map<String ,Object> extra = new HashMap<>();
                            extra.put("msgId", m.getId());
                            extra.put("id", m.getContentId());
                            extra.put("contentId", m.getContentId());
                            extra.put("content", m.getContent());
                            extra.put("jumpType", m.getJumpType());
                            String pushResult = JPushClient.push(m.getTitle(), m.getIntro(),extra);
                            logger.info("JPush return:"+pushResult);
                            JSONObject json = new JSONObject(pushResult);
                            if(Objects.nonNull(json.get("msg_id"))){
                                //更改消息push_status
                                mgr.updatePushStatus(m.getId(), 1);
                            }
                        }
                    }
                    logger.info("TbMessageSchedule_Thread__end");
                }catch (Exception e){
                    logger.error("TbMessageSchedule_Thread__Error:"+e.getLocalizedMessage());
                    e.printStackTrace();
                }

            }
        };
        ScheduledExecutorService service = Executors
                .newSingleThreadScheduledExecutor();
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        service.scheduleAtFixedRate(runnable, 1, 1, TimeUnit.MINUTES);
    }



    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
