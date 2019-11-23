package com.zhuoer.qmaintance.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceResponse;

import net.sf.json.JSONObject;

/**
 * 百度推送工具类
 * @author Administrator
 *
 */
public class BaiDuTools {
	
	public static void sendBaidu(String channelId,String message)
	{
		String sql3="select * from pushinfo";
		List li3=RepairInfoTools.executeQuary(sql3);
		String apiKey="";
		String secretKey="";
		if(li3.size()>0)
		{
			Map m=new HashMap();
			m=(Map) li3.get(0);
			apiKey=(String) m.get("apiKey");
			secretKey=(String) m.get("secretKey");
		}
		/*1. 创建PushKeyPair
         *用于app的合法身份认证
         *apikey和secretKey可在应用详情中获取
         */
        
        PushKeyPair pair = new PushKeyPair(apiKey,secretKey);

        // 2. 创建BaiduPushClient，访问SDK接口
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);

        // 3. 注册YunLogHandler，获取本次请求的交互信息
        pushClient.setChannelLogHandler (new YunLogHandler () {
            @Override
            public void onHandle (YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {
        // 4. 设置请求参数，创建请求实例
        	
            PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest().
                addChannelId(channelId).
                addMsgExpires(new Integer(3600)).   //设置消息的有效时间,单位秒,默认3600*5.
                addMessageType(1).addMessage(message).addDeviceType(3);         //设置消息类型,0表示透传消息,1表示通知,默认为0.
//                add("{\"title\":\"TEST\",\"description\":\"Hello Baidu push!\"}").
//                addDeviceType(3);      //设置设备类型，deviceType => 1 for web, 2 for pc, 
                                       //3 for android, 4 for ios, 5 for wp.
        // 5. 执行Http请求
            PushMsgToSingleDeviceResponse response = pushClient.
                pushMsgToSingleDevice(request);
        // 6. Http请求返回值解析
            System.out.println("msgId: " + response.getMsgId()
                    + ",sendTime: " + response.getSendTime());
        } catch (PushClientException e) {
            //ERROROPTTYPE 用于设置异常的处理方式 -- 抛出异常和捕获异常,
            //'true' 表示抛出, 'false' 表示捕获。
            if (BaiduPushConstants.ERROROPTTYPE) { 
                try {
					throw e;
				} catch (PushClientException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                try {
					throw e;
				} catch (PushServerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMsg: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
	}

}
