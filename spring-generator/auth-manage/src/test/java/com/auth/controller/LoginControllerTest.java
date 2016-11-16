package com.auth.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;

/**
 * 
 * 登录Controller 测试类
 * @author lifangyu
 * @version V1.0
 */

// @Ignore
public class LoginControllerTest extends BaseControllerTest {

    @Test
    public void controllerTest() {
        try {
            MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/checkLogin");
            ResultActions result = this.mockMvc.perform(request);
            result.andExpect(MockMvcResultMatchers.status().isOk());
            MvcResult andReturn = result.andReturn();
            andReturn.getModelAndView();
            logger.info("return datas:" + andReturn.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
