package com.auth.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
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

import com.auth.controller.service.LoginService;
import com.auth.model.AuthMenu;
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

    @Autowired
    private LoginService loginService;
	
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
    public int checkLogin(String username, String code, String pass, HttpSession session) throws IOException {
        // 验证验证码
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
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String sysMain() throws IOException {
        // 进入pages/main.jsp页面
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

        List<AuthMenu> menus = loginService.getMenusByUserIdPid(userId, 0L);
        nodeList = getMenuJsonObject(userId, menus);
        // 排序，因为从set里取出来的值是无序的
        Collections.sort(nodeList, comparator);
        try {
            System.out.println(new ObjectMapper().writeValueAsString(nodeList));
            return new ObjectMapper().writeValueAsString(nodeList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     *
     * @author lifangyu
     * @param menus
     * @return
     * 		List<ZTreeNode>
     */
    private List<ZTreeNode> getMenuJsonObject(long userId, List<AuthMenu> menus) {
        List<ZTreeNode> nodeList = new ArrayList<>();// 菜单跟目录集合
        for (Iterator<AuthMenu> iterator = menus.iterator(); iterator.hasNext();) {
            AuthMenu authMenu = (AuthMenu) iterator.next();
            // 一级菜单对象( 一级菜单展开，二级三级等菜单不展开)
            ZTreeNode node = new ZTreeNode(String.valueOf(authMenu.getId()), String.valueOf(authMenu.getParentId()),
                    authMenu.getMenuName(), authMenu.getFolder() == 1 ? "true" : "false", authMenu.getIconUrl(),
                    authMenu.getSort());

            if (authMenu.getFolder() == 1) {
                List<AuthMenu> menuChileds = loginService.getMenusByUserIdPid(userId, authMenu.getId());
                List<ZTreeNode> nodeChileList = new ArrayList<>();
                for (Iterator<AuthMenu> iteratorsec = menuChileds.iterator(); iteratorsec.hasNext();) {
                    AuthMenu authMenuChiled = (AuthMenu) iteratorsec.next();
                    // 一级菜单下的子菜单对象(二级菜单)
                    ZTreeNode sec = new ZTreeNode(String.valueOf(authMenuChiled.getId()),
                            String.valueOf(authMenuChiled.getParentId()), authMenuChiled.getMenuName(), "false",
                            authMenuChiled.getIconUrl(),
                            authMenuChiled.getSort());
                    if (authMenuChiled.getFolder() == 1) {
                        // 二级菜单下的子菜单(三级菜单)
                        sec.setChildren(this.getMenuJsonObjectSec(userId, authMenuChiled));
                    } else {
                        sec.setFile(authMenuChiled.getMenuUrl());
                    }
                    nodeChileList.add(sec);
                }
                node.setChildren(nodeChileList);
            } else {
                node.setFile(authMenu.getMenuUrl());
            }
            nodeList.add(node);
        }

        return nodeList;
    }

    /**
     * 级联菜单 递归处理
     *
     * @author lifangyu
     * @param nodeList 
     * @param userId
     * @param authMenuChiled
     * @return
     * 		List<ZTreeNode>
     */
    private List<ZTreeNode> getMenuJsonObjectSec(long userId, AuthMenu authMenuChiled) {
        List<ZTreeNode> nodeList = new ArrayList<>();
        List<AuthMenu> menuChileds = loginService.getMenusByUserIdPid(userId, authMenuChiled.getId());
        for (Iterator<AuthMenu> iterators = menuChileds.iterator(); iterators.hasNext();) {
            AuthMenu authMenuThird = (AuthMenu) iterators.next();
            // 三级菜单对象
            ZTreeNode third = new ZTreeNode(String.valueOf(authMenuThird.getId()),
                    String.valueOf(authMenuThird.getParentId()), authMenuThird.getMenuName(), "false",
                    authMenuThird.getIconUrl(),
                    authMenuThird.getSort());
            if (authMenuThird.getFolder() == 1) {
                // 三级菜单对象下的子菜单(以此递归)
                third.setChildren(this.getMenuJsonObjectSec(userId, authMenuThird));
            } else {
                third.setFile(authMenuThird.getMenuUrl());
            }
            nodeList.add(third);
        }
        return nodeList;
    }

}
