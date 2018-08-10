package com.dstz.bpm.rest.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dstz.base.api.aop.annotion.CatchErr;
import com.dstz.base.api.query.QueryFilter;
import com.dstz.base.api.response.impl.ResultMsg;
import com.dstz.base.core.util.StringUtil;
import com.dstz.base.db.model.page.PageJson;
import com.github.pagehelper.Page;
import com.dstz.base.rest.GenericController;
import com.dstz.base.rest.util.RequestUtil;
import com.dstz.bpm.act.service.BpmImageService;
import com.dstz.bpm.api.engine.data.BpmFlowDataAccessor;
import com.dstz.bpm.api.engine.data.result.BpmFlowData;
import com.dstz.bpm.api.engine.data.result.FlowData;
import com.dstz.bpm.api.model.inst.IBpmInstance;
import com.dstz.bpm.core.manager.BpmDefinitionManager;
import com.dstz.bpm.core.manager.BpmInstanceManager;
import com.dstz.bpm.core.manager.BpmTaskOpinionManager;
import com.dstz.bpm.core.model.BpmDefinition;
import com.dstz.bpm.core.model.BpmInstance;
import com.dstz.bpm.core.model.BpmTaskOpinion;
import com.dstz.bpm.engine.action.cmd.DefaultInstanceActionCmd;
import com.dstz.form.api.model.FormType;


/**
 * 流程实例 控制器类
 *
 * @author jeff
 * @email bairimeng@summer.com
 * @Time 2018-01-16 22:09:37
 */
@RestController
@RequestMapping("/bpm/instance")
public class BpmInstanceController extends GenericController {
    @Resource
    BpmInstanceManager bpmInstanceManager;
    @Resource
    BpmFlowDataAccessor bpmFlowDataAccessor;
    @Resource
    BpmTaskOpinionManager bpmTaskOpinionManager;
    @Resource
    BpmImageService bpmImageService;
    @Resource
    BpmDefinitionManager bpmDefinitionMananger;

    /**
     * 流程实例列表(分页条件查询)数据
     */
    @RequestMapping("listJson")
    public PageJson listJson(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        QueryFilter queryFilter = getQueryFilter(request);

        Page<BpmInstance> bpmInstanceList = (Page<BpmInstance>) bpmInstanceManager.query(queryFilter);
        return new PageJson(bpmInstanceList);
    }

    /**
     * 流程实例明细页面
     */
    @RequestMapping("getById")
    @CatchErr
    public ResultMsg<IBpmInstance> getBpmInstance(@RequestParam String id) throws Exception {
    	IBpmInstance bpmInstance = null;
    	if (StringUtil.isNotEmpty(id)) {
            bpmInstance = bpmInstanceManager.get(id);
        }

    	return getSuccessResult(bpmInstance);
    }

    /**
     * 获取展示流程实例需要的数据
     *
     * @param request
     * @param response
     * @throws Exception
     * 
     */
    @RequestMapping("getInstanceData")
    @CatchErr
    public ResultMsg<FlowData> getInstanceData(HttpServletRequest request) throws Exception {
        String instanceId = request.getParameter("instanceId");
        Boolean readonly = RequestUtil.getBoolean(request, "readonly",false);
        
        String defId = request.getParameter("defId");
        String formType = RequestUtil.getString(request, "formType", FormType.PC.value());
        
        FlowData data = bpmFlowDataAccessor.getStartFlowData(defId, instanceId, FormType.fromValue(formType),readonly);
        return getSuccessResult(data);
    }

    /**
     * 处理实例类型的动作
     *
     * @param request
     * @param response
     * @throws Exception void
     */
    @RequestMapping("doAction")
    @CatchErr
    public ResultMsg<String> doAction(@RequestParam String flowData) throws Exception {

        DefaultInstanceActionCmd instanceCmd = new DefaultInstanceActionCmd(flowData);
        String actionName = instanceCmd.executeCmd();
        
        return getSuccessResult(instanceCmd.getInstanceId(),  actionName + "成功");
    }


    @RequestMapping("getInstanceOpinion")
    
    @CatchErr
    public ResultMsg<List<BpmTaskOpinion>> getInstanceOpinion(@RequestParam String instId) throws Exception {
        List<BpmTaskOpinion> taskOpinion = bpmTaskOpinionManager.getByInstId(instId);

        return getSuccessResult(taskOpinion, "获取流程意见成功");
    }


    /**
     * 读取流程图
     */
    @RequestMapping("flowImage")
    public void flowImage(@RequestParam(required=false) String instId,@RequestParam(required=false) String defId ,HttpServletResponse response) throws Exception {
    	String actDefId,actInstId=null;
    	
    	if(StringUtil.isNotEmpty(instId)) {
    		BpmInstance inst = bpmInstanceManager.get(instId);
    		actInstId = inst.getActInstId();
    		actDefId = inst.getActDefId();
    	}else {
    		BpmDefinition def = bpmDefinitionMananger.get(defId);
    		actDefId = def.getActDefId();
    	}
        
        response.setContentType("image/png");
        IOUtils.copy(bpmImageService.draw(actDefId, actInstId), response.getOutputStream());
    }


    @RequestMapping("toForbidden")
    @CatchErr("操作失败")
    public ResultMsg<String> toForbidden(@RequestParam String id, @RequestParam Boolean forbidden) throws Exception {
        BpmInstance inst = bpmInstanceManager.get(id);
        String msg = "";

        if (forbidden && inst.getIsForbidden() == 0) {
            inst.setIsForbidden(IBpmInstance.INSTANCE_FORBIDDEN);
            msg = "禁用成功";
        } else if (!forbidden) {
            inst.setIsForbidden(IBpmInstance.INSTANCE_NO_FORBIDDEN);
            msg = "取消禁用成功";
        }

        bpmInstanceManager.update(inst);
        
        return getSuccessResult(msg);
    }
}
