package com.auth.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.auth.annotation.SystemLogAnnotation;
import com.auth.controller.service.LoginService;
import com.auth.controller.service.MenuService;
import com.auth.controller.service.UserService;
import com.auth.enums.SystemLogTypeEnum;
import com.auth.model.AuthUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.common.utils.entity.ZTreeNode;
import com.spring.common.utils.image.ImageUtils;

/**
 * 
 * 登录 Controller
 * @author lifangyu
 * @version V1.0
 */
@Controller
public class LoginController {

    @SuppressWarnings("unused")
    @Autowired
    private LoginService loginService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private UserService userService;
	
	/**
	 * 生成验证码图片
	 * @throws IOException 
	 */
    @RequestMapping(value = "createImage", method = RequestMethod.GET)
    public void createImage(HttpServletResponse response, HttpSession session) throws IOException {
		//生成验证码及图片
		Map<String, BufferedImage> map = ImageUtils.createImage();
		//取得验证码，存入session
		String code = map.keySet().iterator().next();
        session.setAttribute("imageCode", code);
		//取得图片，发送到页面
		BufferedImage image = map.get(code);
		response.setContentType("image/jpeg");
		OutputStream os = null;
		try{
		    os = response.getOutputStream();
		    ImageIO.write(image, "jpeg", os);
		} catch(IOException ie){
		    throw ie;
		}finally {
            if (os != null)
                os.close();
        }
	}

    /**
     * 生成验证码图片
     * @throws IOException 
     */
    @RequestMapping(value = "checkLogin", method = RequestMethod.POST)
    @ResponseBody
    @SystemLogAnnotation(message = "验证登录", systemLogTypeEnum = SystemLogTypeEnum.MENU, SerialParam = false, SerialReturnValue = false)
    public int checkLogin(String username, String code, String password,String rememberMe, HttpSession session) throws IOException {
        AuthUser user = userService.findUserByUserName(username);
        if (user == null || user.getId() <= 0) {
            // 用户名不存在
            return 1;
        }
        if (user.getPassword().equals(password)) {
            // 用户密码错误
            return 2;
        }
        if (user != null && user.getStatus() == 0) {
            // 用户无效
            return 3;
        }
        String imageCode = (String) session.getAttribute("imageCode");
        if (code == null || !code.equalsIgnoreCase(imageCode)) {
            // 验证码输入有误
            return 1;
        }
        return 0;
    }


    /**
     * 系统首页
     * @throws IOException 
     */
    @RequestMapping(value = { "login", "main" }, method = RequestMethod.POST)
    public String sysMain() throws IOException {
        // 进入pages/main.jsp页面
        // 记住密码处理

        return "main";
    }

    /**
     * tree 菜单
     */
    @SuppressWarnings("unused")
    @ResponseBody
    @RequestMapping(value = "treeView")
    public String treeView(HttpServletRequest request){
        Long userId = 0L;
        // 父节点
        List<ZTreeNode> nodeList = new ArrayList<ZTreeNode>();
        ZTreeNode treeNode = null;
        Comparator<ZTreeNode> comparator = new Comparator<ZTreeNode>() {
            @Override
            public int compare(ZTreeNode o1, ZTreeNode o2) {
                ZTreeNode p1 = (ZTreeNode) o1;
                ZTreeNode p2 = (ZTreeNode) o2;
                if (p1.getSort() == null || p2.getSort() == null) {
                    return Integer.parseInt(p1.getId()) - Integer.parseInt(p2.getId());
                } else {
                    if (p1.getSort() > p2.getSort()) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }
        };
        nodeList = menuService.getMenuNodeList(userId);
        // 排序，因为从set里取出来的值是无序的
        Collections.sort(nodeList, comparator);
        try {
            return new ObjectMapper().writeValueAsString(nodeList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

}
