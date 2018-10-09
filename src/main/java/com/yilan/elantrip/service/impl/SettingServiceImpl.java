package com.yilan.elantrip.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.yilan.elantrip.dao.SettingMapper;
import com.yilan.elantrip.domain.Setting;
import com.yilan.elantrip.service.SettingService;
import com.yilan.elantrip.util.ResultInfo;

@Service
public class SettingServiceImpl implements SettingService  {

	@Resource
	private SettingMapper settingMapper;  //注入dao
	
	@Override
	public Object getSetting(Integer settingId)throws Exception {
		ResultInfo resultInfo=new ResultInfo();
		Setting setting=settingMapper.selectByPrimaryKey(settingId);
		if (setting==null) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("配置不存在");
			return resultInfo;
		}
		resultInfo.setCode("0");
		resultInfo.setMessage("获取成功");
		resultInfo.setResult(setting);
		return resultInfo;
	}

	@Override
	public Object addSetting(Setting setting)throws Exception {
		ResultInfo resultInfo=new ResultInfo();
		int count=settingMapper.insertSelective(setting); 
		if (count==0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("添加失败");
			return resultInfo;
		}
		resultInfo.setCode("0");
		resultInfo.setMessage("添加成功");
		return resultInfo;
	}

	@Override
	public Object modifySetting(Setting setting)throws Exception {
		ResultInfo resultInfo=new ResultInfo();
		int count=settingMapper.updateByPrimaryKeySelective(setting);
		
		if (count==0) {
			resultInfo.setCode("-1");
			resultInfo.setMessage("修改失败");
			return resultInfo;
		}
		
		resultInfo.setMessage("修改成功");
		
		return resultInfo;
	}			
}
