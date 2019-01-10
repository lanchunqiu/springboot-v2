package com.springboot.chapter12.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/9
 **/
@SpringBootApplication(scanBasePackages = "com.springboot.chapter12")
@MapperScan(basePackages = "com.springboot.chapter12", annotationClass = Repository.class)
// 使用注解驱动缓存机制
//@EnableCaching
public class Chapter12Application extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource = null;

    String pwdQuery = "select user_name,pwd,available from t_user where user_name = ?";
    String roleQuery = " select u.user_name,r.role_name from t_user u, t_user_role ur, t_role r"
            +" where u.id=ur.user_id and r.id=ur.role_id and u.user_name = ?";

    @Value("${system.user.password.secret}")
    private String secret = null;


    @Autowired
    private UserDetailsService UserDetailsService = null;

    public static void main(String[] args) {
        SpringApplication.run(Chapter12Application.class, args);
        //密码编码器
        //PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder("uvwxyz");
        //System.out.println(passwordEncoder.encode("123456"));
        //System.out.println(passwordEncoder.encode("abc"));
    }

    /**
     * 覆盖WebSecurityConfigurerAdapter用户详情方法
     *
     * @param auth
     *            用户签名管理器构造器
     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //密码编码器
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        //使用内存存储
//        auth.inMemoryAuthentication()
//                .passwordEncoder(encoder)//设置密码编码器
//                .withUser("admin")//注册用户admin
//                .password(encoder.encode("abc"))//设置密码
//                .roles("USER", "ADMIN")
//                .and()
//                .withUser("myuser")//注册用户myuser
//                .password(encoder.encode("123456"))
//                .roles("USER");
//
//    }

    /**
     * 覆盖WebSecurityConfigurerAdapter用户详情方法
     *
     * @param auth
     *            用户签名管理器构造器
     */
//     @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception
//     {
//         // 密码编码器
//         PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//         auth.jdbcAuthentication()
//         //密码编码器
//         .passwordEncoder(passwordEncoder)
//         // 数据源
//         .dataSource(dataSource)
//         // 查询用户，自动判断密码是否一致
//         .usersByUsernameQuery(pwdQuery)
//         // 赋予权限
//         .authoritiesByUsernameQuery(roleQuery);
//     }

    /**
     * 覆盖WebSecurityConfigurerAdapter用户详情方法
     *
     * @param auth
     *            用户签名管理器构造器
     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //密码编码器
//        PasswordEncoder encoder = new Pbkdf2PasswordEncoder(this.secret);
//
//        //使用数据库
//        auth.jdbcAuthentication()
//                //密码标明器
//                .passwordEncoder(encoder)
//                //数据源
//                .dataSource(dataSource)
//                //查询用户，自动判断密码是否一致
//                .usersByUsernameQuery(pwdQuery)
//                //赋予权限
//                .authoritiesByUsernameQuery(roleQuery);
//
//    }

    /**
     * 覆盖WebSecurityConfigurerAdapter用户详情方法
     *
     * @param auth
     *            用户签名管理器构造器
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //密码编码器
        PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder(this.secret);
        //设置用户名和密码
        auth.userDetailsService(UserDetailsService).passwordEncoder(passwordEncoder);
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // 访问/admin下的请求需要管理员权限
//        http.authorizeRequests().antMatchers("/admin/**").access("hasRole('ADMIN')")
//                // 通过签名后可以访问任何请求
//                .and()
//                .authorizeRequests().antMatchers("/**").permitAll()
//                // 设置登录页和默认的跳转路径
//                .and()
//                .formLogin().loginPage("/login/page").defaultSuccessUrl("/admin/welcome1")
//                // 登出页面和默认跳转路径
//                .and().logout().logoutUrl("/logout/page")
//                .logoutSuccessUrl("/welcome");
//    }

//    /**
//     * 使用Ant风格配置限定
//     * @param http
//     * @throws Exception
//     */
//    @Override
//	protected void configure(HttpSecurity http) throws Exception {
//         // 限定签名后的权限
//         http.
//         /* ########第一段######## */
//         authorizeRequests()
//         // 限定"/user/welcome"请求赋予角色ROLE_USER或者ROLE_ADMIN
//         .antMatchers("/user/welcome", "/user/details").hasAnyRole("USER", "ADMIN")
//         // 限定"/admin/"下所有请求权限赋予角色ROLE_ADMIN
//         .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
//         // 其他路径允许签名后访问
//         .anyRequest().permitAll()
//
//         /* ######## 第二段 ######## */
//         /** and代表连接词 **/
//         // 对于没有配置权限的其他请求允许匿名访问
//         .and().anonymous()
//
//         /* ######## 第三段 ######## */
//         // 使用Spring Security默认的登录页面
//         .and().formLogin()
//         // 启动HTTP基础验证
//         .and().httpBasic();
//	}


//    /**
//     * 采用正则表达式的方法给予请求路径的限定
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        //采用正则表达式的方法给予请求路径的限定
//		 http.authorizeRequests()
//                .regexMatchers("/user/welcome", "/user/details").hasAnyRole("USER", "ADMIN")
//				.regexMatchers("/admin/.*").hasAuthority("ROLE_ADMIN")
//                .and().formLogin()
//                .and().httpBasic();
//
//    }

//    /**
//     * 使用Spring表达式限定
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        //http.csrf().disable().authorizeRequests()//关闭csrf验证
//        http.authorizeRequests()
//
//		// 使用Spring表达式限定只有角色ROLE_USER或者ROLE_ADMIN
//		.antMatchers("/user/**","/csrf/**").access("hasRole('USER') or hasRole('ADMIN')")
//
//        // 设置访问权限给角色ROLE_ADMIN，要求是完整登录(非记住我登录)
//		.antMatchers("/admin/welcome1").access("hasAuthority('ROLE_ADMIN') && isFullyAuthenticated()")
//
//	    // 限定"/admin/welcome2"访问权限给角色ROLE_ADMIN，允许不完整登录
//		.antMatchers("/admin/welcome2").access("hasAuthority('ROLE_ADMIN')")
//
//		// 使用记住我的功能
//		.and().rememberMe()
//
//		// 使用Spring Security默认的登录页面
//		.and().formLogin()
//
//		// 启动HTTP基础验证
//		.and().httpBasic();
//
//    }

//    /**
//     * 强制使用HTTPS请求
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//
//        http    //使用安全通道,限定为https请求
//                .requiresChannel().antMatchers("/user/**").requiresSecure()
//                // 不适用https
//                .and().requiresChannel().antMatchers("/admin/welcome1").requiresInsecure()
//                // 限定允许的访问角色
//                .and().authorizeRequests().antMatchers("/admin/**").hasAnyRole("ADMIN")
//                .antMatchers("/user/**").hasAnyRole("ROLE", "ADMIN");
//
//    }

//    /**
//     * 记住我
//     * @param http
//     * @throws Exception
//     */
//    @Override
//	protected void configure(HttpSecurity http) throws Exception {
//	    http .httpBasic().realmName("my-basic-name").and()
//	    // 访问/admin下的请求需要管理员权限
//	     .authorizeRequests().antMatchers("/admin/**").access("hasRole('ADMIN')")
//	    // 启用remember me功能
//	    .and().rememberMe().tokenValiditySeconds(86400).key("remember-me-key")
//	    // 启用HTTP Batic功能
//	    .and().httpBasic()
//	    // 通过签名后可以访问任何请求
//	    .and().authorizeRequests().antMatchers("/**").permitAll()
//	    // 设置登录页和默认的跳转路径
//	    .and().formLogin().loginPage("/login/page").defaultSuccessUrl("/admin/welcome1");
//	}

    /**
     * 记住我
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http    //访问/admin下的请求需要管理员权限
                .authorizeRequests().antMatchers("/admin/**").access("hasRole('ADMIN')")
                // 通过签名后可以访问任何请求
                .and().authorizeRequests().antMatchers("/**").permitAll()
                // 设置登录页和默认的跳转路径
                .and().formLogin().loginPage("/login/page")
                .defaultSuccessUrl("/admin/welcome1")

                // 登出页面和默认跳转路径
                .and().logout().logoutUrl("/logout/page")
                .logoutSuccessUrl("/welcome");

    }


    @Autowired
    private RedisTemplate redisTemplate = null;

    @PostConstruct
    public void initRedisTemplate() {
        RedisSerializer<String> strSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(strSerializer);
        redisTemplate.setHashKeySerializer(strSerializer);
    }


}
