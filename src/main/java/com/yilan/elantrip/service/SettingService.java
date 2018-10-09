package com.yilan.elantrip.service;

import org.springframework.transaction.annotation.Transactional;

import com.yilan.elantrip.domain.Setting;;

/**
 * 配置的Service
 * @author Administrator
 *
 */
@Transactional  //开启事务注解,默认运行时异常事务回滚
public interface SettingService {
	
	/**
	 * 获取配置详细信息
	 * @param settingId 配置Id
	 * @return
	 * @throws Exception
	 */
	Object getSetting(Integer settingId)throws Exception;
	
	/**
	 * 添加配置
	 * @param setting
	 * @return
	 * @throws Exception
	 */
	Object addSetting(Setting setting)throws Exception;
	
	/**
	 * 修改配置信息
	 * @param setting
	 * @return
	 * @throws Exception
	 */
	Object modifySetting(Setting setting)throws Exception;
	

}
