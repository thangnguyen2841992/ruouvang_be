package com.thang.story.config.security;



import com.thang.story.config.CustomAccessDeniedHandler;
import com.thang.story.config.JwtAuthenticationFilter;
import com.thang.story.config.RestAuthenticationEntryPoint;
import com.thang.story.model.entity.Role;
import com.thang.story.model.entity.User;
import com.thang.story.repository.IRoleRepository;
import com.thang.story.repository.IUserRepository;
import com.thang.story.service.role.IRoleService;
import com.thang.story.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;






    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder() { //bean m?? h??a pass
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //l???y user t??? DB
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @PostConstruct
    public void init() {
        List<User> users = userService.findAll();
        List<Role> roleList = roleService.findAll();
        if (roleList.size() == 0) {
            Role roleAdmin = new Role("ROLE_ADMIN");
            roleService.save(roleAdmin);
            Role roleUser = new Role("ROLE_USER");
            roleService.save(roleUser);

        }
        if (users.size() == 0) {
            User admin = new User("admin", "thuthuyda1");
            userService.saveAdmin(admin);
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/**");
        http.httpBasic().authenticationEntryPoint(restServicesEntryPoint());//T??y ch???nh l???i th??ng b??o 401 th??ng qua class restEntryPoint
        http.authorizeRequests()
                .antMatchers("/login",
                        "/register", "/**").permitAll() // t???t c??? truy c???p ???????c
                .anyRequest().authenticated()  //c??c request c??n l???i c???n x??c th???c
                .and().csrf().disable(); // v?? hi???u h??a b???o v??? c???a csrf (ki???m so??t quy???n truy c???p)
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors();
    }
}