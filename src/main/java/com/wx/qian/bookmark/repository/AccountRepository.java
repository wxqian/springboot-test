package com.wx.qian.bookmark.repository;

import com.wx.qian.bookmark.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * <p>
 *
 * @author <a href="mailto:qwx890206@gmail.com">wxqian</a>
 * @version v1.0, 2015/10/8
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUserName(String userName);
}
