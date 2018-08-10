package com.dstz.bpm.rest.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dstz.base.api.query.QueryFilter;
import com.dstz.base.api.query.QueryOP;
import com.dstz.base.db.model.page.PageJson;
import com.github.pagehelper.Page;
import com.dstz.base.rest.GenericController;
import com.dstz.bpm.api.constant.InstanceStatus;
import com.dstz.bpm.core.manager.BpmDefinitionManager;
import com.dstz.bpm.core.manager.BpmInstanceManager;
import com.dstz.bpm.core.manager.BpmTaskManager;
import com.dstz.bpm.core.model.BpmDefinition;
import com.dstz.bpm.core.model.BpmInstance;
import com.dstz.bpm.core.model.BpmTask;
import com.dstz.bpm.core.model.BpmTaskApprove;
import com.dstz.sys.util.ContextUtil;


/**
 * 我的任务
 */
@RestController
@RequestMapping("/bpm/my")
public class BpmMyTaskController extends GenericController{
	@Resource
	BpmTaskManager bpmTaskManager;
	@Resource
	BpmInstanceManager bpmInstanceManager;
	@Resource 
	BpmDefinitionManager bpmDefinitionManager;
 
	/**
	 * 获取我待办事项
	 * @param request
	 * @param reponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("todoTaskList")
	public  PageJson todoTaskList(HttpServletRequest request,HttpServletResponse reponse) throws Exception{
		QueryFilter queryFilter=getQueryFilter(request);
		String userId = ContextUtil.getCurrentUserId();
		
		Page<BpmTask> bpmTaskList=(Page<BpmTask>)bpmTaskManager.getTodoList(userId,queryFilter);
		return new PageJson(bpmTaskList);
	}
	
	/**
	 * 获取我的申请
	 * @param request
	 * @param reponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("applyTaskList")
	public  PageJson applyTaskList(HttpServletRequest request,HttpServletResponse reponse) throws Exception{
		QueryFilter queryFilter=getQueryFilter(request);
		String userId = ContextUtil.getCurrentUserId();
		
		Page<BpmInstance> bpmTaskList=(Page<BpmInstance>)bpmInstanceManager.getApplyList(userId,queryFilter);
		return new PageJson(bpmTaskList);
	}
	
	/**
	 * 获取我的流程
	 * @param request
	 * @param reponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("definitionList")
	public  PageJson definitionList(HttpServletRequest request,HttpServletResponse reponse) throws Exception{
		QueryFilter queryFilter=getQueryFilter(request);
		String userId = ContextUtil.getCurrentUserId();
		
		List<BpmDefinition> list = bpmDefinitionManager.getMyDefinitionList(userId,queryFilter);
		return new PageJson(list);
	}
	
	/**
	 * 获取我审批过的
	 * @param request
	 * @param reponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("approveList")
	public PageJson approveList(HttpServletRequest request,HttpServletResponse reponse) throws Exception{
		QueryFilter queryFilter=getQueryFilter(request);
		String userId = ContextUtil.getCurrentUserId();
		
		List<BpmTaskApprove> bpmTaskList=bpmInstanceManager.getApproveHistoryList(userId,queryFilter);
		return new PageJson(bpmTaskList);
	}
	
	
	@RequestMapping("draftList")
	public PageJson draftList(HttpServletRequest request,HttpServletResponse reponse) throws Exception{
		QueryFilter queryFilter=getQueryFilter(request);
		String userId = ContextUtil.getCurrentUserId();
		queryFilter.addFilter("inst.status_", InstanceStatus.STATUS_DRAFT.getKey(), QueryOP.EQUAL);
		List<BpmInstance> instanceList = bpmInstanceManager.getApplyList(userId, queryFilter);
		return new PageJson(instanceList);
	}
	
	
	 
}
