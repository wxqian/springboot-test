package com.wx.qian.bookmark.repository;

import com.wx.qian.bookmark.domain.BookMark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

/**
 * <p>
 *
 * @author <a href="mailto:qwx890206@gmail.com">wxqian</a>
 * @version v1.0, 2015/10/8
 */
public interface BookMarkRepository extends JpaRepository<BookMark, Long> {
    Collection<BookMark> findByAccountUserName(String username);
}
