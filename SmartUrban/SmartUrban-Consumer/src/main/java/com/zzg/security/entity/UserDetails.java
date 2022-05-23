package com.zzg.security.entity;

import com.zzg.model.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;
import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserDetails extends SysUser
        implements org.springframework.security.core.userdetails.UserDetails{
    /**
     * 用户角色
     */
    private Collection<GrantedAuthority> authorities;

    /**
     * 账号是否过期
     */
    private boolean isAccountNonExpired = false;

    /**
     * 账号是否锁定
     */
    private boolean isAccountNonLocked = false;

    /**
     * 证书是否过期
     */
    private boolean isCredentialsNonExpired = false;

    /**
     * 账号是否有效
     */
    private boolean isEnabled = true;

    /**
     * 获得用户权限
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * 判断账号是否过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    /**
     * 判断账号是否锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    /**
     * 判断证书是否过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    /**
     * 判断账号是否有效
     */
    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public static void main(String[] args) {
        String pass = "123456";
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String hashPass = bcryptPasswordEncoder.encode(pass);
        System.out.println(hashPass);

        boolean f = bcryptPasswordEncoder.matches("123456",hashPass);
        System.out.println(f);
    }

}
