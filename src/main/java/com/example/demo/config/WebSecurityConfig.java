package com.example.demo.config;

import com.example.demo.config.handler.FailureHandler;
import com.example.demo.config.handler.SuccessHandler;
import com.example.demo.service.impl.AuthServiceImpl;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @NonNull
    private AuthServiceImpl service;

    @NonNull
    private FailureHandler failureHandler;

    @NonNull
    private SuccessHandler successHandler;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * WebSecurityの設定
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 静的リソース(images、css、javascript)とH2DBのコンソールに対するアクセスはセキュリティ設定を無視する
        web.ignoring().antMatchers("/css/**", "/fonts/**", "/images/**", "/js/**");
    }

    /**
     * HttpSecurityの設定
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/signup", "/error").permitAll().anyRequest().authenticated();

        //ログイン設定
        http.formLogin()
                //ログインフォームのパスを設定
                .loginPage("/login")
                //認証成功時にリダイレクトするURLを設定
//                .defaultSuccessUrl("/blog")
                //認証失敗時にforwardするURLを設定
//                .failureForwardUrl("/login")
                //認証成功時にforwardするURLを設定
                //.successForwardUrl("/")
                //認証成功時に呼ばれるハンドラクラスを設定
                .successHandler(successHandler)
                //認証失敗時にリダイレクトするURLを設定
//                .failureUrl("/menu/")
                //認証失敗時に呼ばれるハンドラクラスを設定
//                .failureHandler(failureHandler)
                //ユーザー名、パスワードのパラメータ名を設定
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll();

//        https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#csrf-logout
        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service)
                .passwordEncoder(bCryptPasswordEncoder());
    }
}
