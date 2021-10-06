package com.colm.utils;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teaopenapi.models.Config;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * 短信发送工具类
 */
public class SMSUtils {
	public static final String VALIDATE_CODE = "SMS_159620392";//发送短信验证码
	public static final String ORDER_NOTICE = "SMS_159771588";//体检预约成功通知
	public static final String CONNECT_TIMEOUT_KEY = "sun.net.client.defaultConnectTimeout";
	public static final String CONNECT_TIMEOUT_VAL = "10000";
	public static final String READ_TIMEOUT_KEY = "sun.net.client.defaultReadTimeout";
	public static final String READ_TIMEOUT_VAL = "10000";
	// 初始化ascClient需要的几个参数
	public static final String PRODUCT = "Dysmsapi";// 短信API产品名称（短信产品名固定，无需修改）
	public static final String DOMAIN = "dysmsapi.aliyuncs.com";// 短信API产品域名（接口地址固定，无需修改）
	// 替换成你的AK
	public static final String ACCESSKEY_ID = "accessKeyId";// 你的accessKeyId,参考本文档步骤2
	public static final String ACCESSKEY_SECRET = "accessKeySecret";// 你的accessKeySecret，参考本文档步骤2
	// regionId
	public static final String REGION_ID = "cn-hangzhou";
	public static final String SIGN_NAME = "";

	/**
	 * 发送短信 -- 原版 API
	 * @param phoneNumbers
	 * @param param
	 * @throws ClientException
	 */
	public static boolean sendShortMessage(String templateCode,String phoneNumbers,String param) throws ClientException{
		// 设置超时时间-可自行调整
		System.setProperty(CONNECT_TIMEOUT_KEY, CONNECT_TIMEOUT_VAL);
		System.setProperty(READ_TIMEOUT_KEY, READ_TIMEOUT_VAL);

		// 初始化ascClient,暂时不支持多region（请勿修改）
		IClientProfile profile = DefaultProfile.getProfile(REGION_ID, ACCESSKEY_ID, ACCESSKEY_SECRET);
		DefaultProfile.addEndpoint(REGION_ID, REGION_ID, PRODUCT, DOMAIN);
		IAcsClient acsClient = new DefaultAcsClient(profile);
		// 组装请求对象
		SendSmsRequest request = new SendSmsRequest();
		// 使用post提交
		request.setMethod(MethodType.POST);
		// 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
		request.setPhoneNumbers(phoneNumbers);
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName(SIGN_NAME);
		// 必填:短信模板-可在短信控制台中找到
		request.setTemplateCode(templateCode);
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		// 友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
		request.setTemplateParam("{\"code\":\""+param+"\"}");
		// 可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
		// request.setSmsUpExtendCode("90997");
		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		// request.setOutId("yourOutId");
		// 请求失败这里会抛ClientException异常
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
			// 请求成功
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 发送短信 -- 升级 API
	 * @param templateCode
	 * @param phoneNumbers
	 * @param param
	 * @return
	 */
	public static boolean sendShortMessageNew(String templateCode,String phoneNumbers,String param) throws Exception {
		Config config = new Config().setAccessKeyId(ACCESSKEY_ID).setAccessKeySecret(ACCESSKEY_SECRET);
		config.endpoint = DOMAIN;
		Client client = new Client(config);
		com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest();
		com.aliyun.dysmsapi20170525.models.SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);
		return sendSmsResponse != null && sendSmsResponse.getBody().getCode().equals("OK");
	}
}