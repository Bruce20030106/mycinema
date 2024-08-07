package com.example.mycinema.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.mycinema.common.JwtUtils;
import com.example.mycinema.common.R;
import com.example.mycinema.common.TokenBlacklistService;
import com.example.mycinema.common.UserContextHolder;
import com.example.mycinema.domain.dto.LoginInfo;
import com.example.mycinema.domain.po.User;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@AllArgsConstructor
public class LoginCheckInterceptor implements HandlerInterceptor {

    private TokenBlacklistService tokenBlacklistService;

    @Override //目标资源方法运行前运行, 返回true: 放行, 放回false, 不放行
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        //1.获取请求url。
        String url = req.getRequestURL().toString();
        log.info("请求的url: {}", url);

        //3.获取请求头中的令牌（token）。
        String jwt = req.getHeader("token");

        //4.判断令牌是否存在，如果不存在，返回错误结果（未登录）。
        if (!StringUtils.hasLength(jwt)) {
            log.info("请求头token为空,返回未登录的信息");
            R error = R.error("NOT_LOGIN");
            //手动转换 对象--json --------> 阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return false;
        }
        // 检查Token是否在黑名单中
        if (tokenBlacklistService.isBlacklisted(jwt)) {
            //手动转换 对象--json --------> 阿里巴巴fastJSON
            R error = R.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return false;
        }
        //5.解析token，如果解析失败，返回错误结果（未登录）。
        Claims claims;
        try {
            claims = JwtUtils.parseJwt(jwt);
        } catch (Exception e) {//jwt解析失败
            e.printStackTrace();
            log.info("解析令牌失败, 返回未登录错误信息");
            R error = R.error("NOT_LOGIN");
            //手动转换 对象--json --------> 阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return false;
        }

        //6.放行。
        log.info("令牌合法, 放行");
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUserName(claims.get("userName",String.class));
        UserContextHolder.setUser(loginInfo);
        return true;
    }

    @Override //目标资源方法运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle ...");
    }

    @Override //视图渲染完毕后运行, 最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContextHolder.clear();
        System.out.println("afterCompletion...");
    }
}