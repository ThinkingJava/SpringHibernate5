package com.ych.controller;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ych.service.StudentService;
import com.ych.util.ApiResult;
import com.ych.util.Const;
import com.ych.util.ResultUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;


/**
* Created by GeneratorFx on 2017-05-03.
*/
@Controller
@RequestMapping(value = "/student", produces = "text/html;charset=UTF-8")
public class StudentController {
    public Logger log = Logger.getLogger(getClass());
@Resource
StudentService studentService;

    @RequestMapping(value = "/updatePassWord",method = RequestMethod.GET)
    public @ResponseBody String updatePassWord(@RequestParam(value = "requestContent") String requestContent){
        log.info("request param : " + requestContent);
        ApiResult<Object> resultJson = new ApiResult<Object>();
        ObjectMapper jsonMapper = new ObjectMapper();
        try{
            JsonNode jsonNode = jsonMapper.readTree(requestContent);
//            String cMobileTele = jsonNode.get("cMobileTele").textValue();
//            String oldPass = jsonNode.get("oldPass").textValue();
//            String newPass = jsonNode.get("newPass").textValue();
            List list = studentService.getStudentList();
            if(list!=null&&list.size()>0){
                resultJson.setCode(Const.RESULT_OK);
                resultJson.setResultContent(list);
                resultJson.setMsg("查询成功");
            }else{
                resultJson.setCode(Const.RESULT_USERNAME_OR_PWD_ERROR);
                resultJson.setResultContent(new HashMap<Object,Object>());
                resultJson.setMsg("查询失败");
            }
        }catch(Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            resultJson.setCode(Const.RESULT_DATA_UNWELLFORMED);
            resultJson.setMsg("请求数据不完整");
            resultJson.setResultContent(new HashMap<Object,Object>());
        }
        return ResultUtils.writeEntityToJSON(resultJson);

    }


}
