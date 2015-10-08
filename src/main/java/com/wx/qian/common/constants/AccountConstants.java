package com.wx.qian.common.constants;

import com.wx.qian.bookmark.domain.Account;
import com.wx.qian.bookmark.domain.BookMark;
import com.wx.qian.bookmark.repository.AccountRepository;
import com.wx.qian.bookmark.repository.BookMarkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * <p>应用启动时，在Account里插入数据，若Account表里存在数据，则不操作
 *
 * @author <a href="mailto:qwx890206@gmail.com">wxqian</a>
 * @version v1.0, 2015/10/8
 */
@Component
public class AccountConstants implements ApplicationListener<ApplicationPreparedEvent> {

    private Logger logger = LoggerFactory.getLogger(AccountConstants.class);
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BookMarkRepository bookMarkRepository;

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        logger.info("account constants start");
        List<Account> accounts = accountRepository.findAll();
        //若account为空，则插入用户
        if (CollectionUtils.isEmpty(accounts)) {
            Arrays.asList("Adam", "Bob", "Cathy", "Dave", "Ellen", "Frank", "George", "Hide", "Island").forEach(
                    a -> {
                        Account account = accountRepository.save(new Account(a, "password"));
                        bookMarkRepository.save(new BookMark(account, "http://bookmark.com/1/" +
                                a, "a description"));
                        bookMarkRepository.save(new BookMark(account, "http://bookmark.com/2/" +
                                a, "a description"));
                    });
        }
    }

    public AccountConstants() {
        logger.info("initial account constants");
    }
}
