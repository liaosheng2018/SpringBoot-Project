package com.zzg.security.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zzg.model.SysRole;
import com.zzg.model.SysUser;
import com.zzg.security.entity.UserDetails;
import com.zzg.quartz.service.SysRoleService;
import com.zzg.quartz.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户登录Service
 *
 * @author zzg
 *
 */
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Reference
    private SysUserService userService;

    @Reference
    private SysRoleService roleService;




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EntityWrapper<SysUser> queryWrapper = new EntityWrapper<>();
        queryWrapper.eq("username", username);
        SysUser user = userService.selectOne(queryWrapper);
        if (user != null) {
            UserDetails userDetails = new UserDetails();
            BeanUtils.copyProperties(user, userDetails);

            // 用户角色
            Set<GrantedAuthority> authorities = new HashSet<>();

            // 查询用户角色
            List<SysRole> roleList = roleService.findByUserId(userDetails.getId());
            roleList.forEach(role -> {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleCode()));
            });

            userDetails.setAuthorities(authorities);

            return userDetails;
        }

        return null;
    }
}
