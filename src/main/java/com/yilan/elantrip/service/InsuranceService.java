package com.yilan.elantrip.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yilan.elantrip.domain.Insurance;
import com.yilan.elantrip.domain.InsuranceImage;
import com.yilan.elantrip.util.PagingTool;

/**
 * 保险的Service
 * 
 * @author Administrator
 *
 */
@Transactional // 开启事务注解,默认运行时异常事务回滚
public interface InsuranceService {

	/**
	 * 添加保险
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	Object addInsurance(Insurance insurance,List<InsuranceImage> insuranceImages)throws Exception;
	/**
	 * 修改保险
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	Object modifyInsurance(Insurance insurance)throws Exception;

	/**
	 * 获取保险详细信息
	 * 
	 * @param userId
	 *            用户Id
	 * @return
	 * @throws Exception
	 */
	Object getInsuranceById(int insuranceId)throws Exception;

	/**
	 * 获取保险总数
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	Object countTotal(PagingTool pagingTool)throws Exception;
	/**
	 * 获取保险列表
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	List<Insurance> getInsuranceList(PagingTool pagingTool)throws Exception;


}
