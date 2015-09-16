package com.wx.qian.security.repository;

import com.wx.qian.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 *
 * @author <a href="mailto:qwx890206@gmail.com">wxqian</a>
 * @version v1.0, 2015/9/16
 */
public interface UserRepository  extends JpaRepository<User,Integer>{
    User findByLogin(String login);
}
