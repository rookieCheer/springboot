package com.lyf.springboot03web.listener;

import com.lyf.base.constant.Constants;
import com.lyf.base.entity.SysShiroUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * 单一登录实现
 */
@WebListener
public class MyListener implements HttpSessionAttributeListener {
    private static final Logger logger = LoggerFactory.getLogger(MyListener.class);

    //账号和session
    Map<String, HttpSession> map = new HashMap<String, HttpSession>();

    /**
     * 在session中添加对象时触发此操作 笼统的说就是调用setAttribute这个方法时候会触发的
     *
     * @param event
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        logger.info("session监听器 添加对象时触发  -->  绑定对象：" + event.getName());
        // 用户信息处理
        dispose(event);
    }

    /**
     * 删除session中添加对象时触发此操作  笼统的说就是调用 removeAttribute这个方法时候会触发的
     * 删除session--》判断是不是修改用户信息--》删除map中用户对应的信息    清除登录
     *
     * @param event
     */
    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        logger.info("session监听器  删除执行 --> ");
        String name = event.getName();
        // 注销
        if (name.contains(Constants.SESSION_USER_KEY)) {
            // 将该session从map中移除
            SysShiroUser sysShiroUser = (SysShiroUser) event.getValue();
            map.remove(sysShiroUser.getAccount());
            logger.info("帐号" + sysShiroUser.getAccount() + "注销。");
        }
    }

    /**
     * 在Session属性被重新设置时  修改时触发
     *
     * @param event
     */
    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        logger.info("session监听器  替换--> ");
        // 用户信息
        dispose(event);
    }

    // 用户信息处理
    public void dispose(HttpSessionBindingEvent event) {
        if (event.getName().contains(Constants.SESSION_USER_KEY)) {
            SysShiroUser sysShiroUser = (SysShiroUser) event.getValue();
            HttpSession mapSession = map.get(sysShiroUser.getAccount());
            if (null != mapSession) {
                //将在线的修改为老用户
                String key = mapSession.getAttribute(Constants.SESSION_USER_ACCOUNT)+ Constants.SESSION_USER_KEY;
                SysShiroUser oldUser = (SysShiroUser) mapSession.getAttribute(key);
                //删除session
                mapSession.removeAttribute(key);
                mapSession.setAttribute( mapSession.getAttribute(Constants.SESSION_USER_ACCOUNT)+ Constants.SESSION_OLD_USER_KEY, oldUser);
            }
            // 将session以用户名为索引，放入map中
            map.put(sysShiroUser.getAccount(), event.getSession());
        }
    }

}
