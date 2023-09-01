package com.example.websocketitem.interceptor;

/*import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.websocketitem.mapper.AdministratorsMapper;
import com.example.websocketitem.model.Administrators;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class PermissionInterceptor implements HandlerInterceptor {
    @Resource
    private AdministratorsMapper administratorsMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String manageAccounts = request.getHeader("manageAccounts");
        return this.AdministratorsNumberAll(manageAccounts);
    }

    public boolean AdministratorsNumberAll(String manageAccounts){
        QueryWrapper<Administrators> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("manage_accounts",manageAccounts);
        Administrators administrators = administratorsMapper.selectOne(queryWrapper);
        if(administrators!=null){
            return true;
        }
        return false;
    }
}*/
