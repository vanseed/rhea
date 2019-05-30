package com.vanseed.rhea.api.handler.misc;


import com.vanseed.rhea.api.handler.base.BaseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.vanseed.rhea.common.enums.AppType;
import com.vanseed.rhea.common.enums.BooleanFlag;
import com.vanseed.rhea.common.enums.DeviceType;
import com.vanseed.rhea.common.mvc.MRequest;
import com.vanseed.rhea.common.mvc.MRequestHeader;
import com.vanseed.rhea.common.mvc.MResponse;
import com.vanseed.rhea.common.utils.ResponseUtils;
import com.vanseed.rhea.domain.model.AppVersion;
import com.vanseed.rhea.domain.repository.AppVersionRepository;
import com.vanseed.saturn.core.annotation.AHandler;
import com.vanseed.saturn.support.util.ParamUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * 
 * @author leon
 *
 */
@Component
@AHandler(protocol="0001", version = "1.0.0")
public class AppVersionHandler extends BaseHandler implements IAppVersionHandler {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AppVersionRepository appVersionRepository;

	@Override
	public MResponse doHandler(MRequest request ) {
		return getAppVersion(request.getRequestHeader(), request.getRequestBody());
	}


	private MResponse getAppVersion(MRequestHeader header, Map params ) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer appType = ParamUtils.convertInteger(params.get("app_type"));
		Integer deviceType = header.getDeviceType();
		String clientVersion = header.getClientVersion();

		Boolean newVserionFlag = false; //是否有新版本
		Boolean validFlag = true;	//当前版本是否有效

		//查找最新版本
		AppVersion latestVersion = appVersionRepository.findByAppTypeAndDeviceTypeAndLatestFlag(
				AppType.C.getIndex(), deviceType, BooleanFlag.YES.getIndex());

		if (latestVersion != null && clientVersion.compareTo(latestVersion.getVersion()) < 0) {
			newVserionFlag = true;
			map.put("new_version", latestVersion.getVersion());
			if (deviceType == DeviceType.ANDROID.getIndex()) {
				map.put("app_path", latestVersion.getAppPath());
				map.put("md5", latestVersion.getMd5());
			} else if (deviceType == DeviceType.IOS.getIndex()) {
				//map.put("app_path", PropertiesUtil.getProp("app.path.ios"));
			}
		}

		if(newVserionFlag){
			AppVersion client = appVersionRepository.findByAppTypeAndDeviceTypeAndVersion(
					AppType.C.getIndex(), deviceType, clientVersion);
			if(client.getValidFlag() == BooleanFlag.NO.getIndex()){
				validFlag = false;
			}
		}

		map.put("new_vserion_flag", newVserionFlag);
		map.put("valid_flag", validFlag);
		return ResponseUtils.getSuccessRespose(map);
	}
}
