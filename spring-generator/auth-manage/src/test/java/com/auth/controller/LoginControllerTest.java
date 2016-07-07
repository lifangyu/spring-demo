package com.auth.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * 
 * 登录Controller 测试类
 * @author lifangyu
 * @version V1.0
 */
public class LoginControllerTest extends BaseControllerTest {

    @Test
    public void getDriefingDatas() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/login/createImage");
		ResultActions result = this.mockMvc.perform(request);
        result.andExpect(MockMvcResultMatchers.status().isOk());
		MvcResult andReturn = result.andReturn();
		andReturn.getModelAndView();
		System.err.println("返回数据为:" + andReturn.getResponse().getContentAsString());
	}

}
