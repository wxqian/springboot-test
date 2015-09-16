package com.wx.qian.jpa;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

import javax.persistence.EntityManager;

/**
 * <p>
 *
 * @author <a href="mailto:qwx890206@gmail.com">wxqian</a>
 * @version v1.0, 2015/9/16
 */
public class GenericJpaRepositoryFactory extends JpaRepositoryFactory{

    public GenericJpaRepositoryFactory(EntityManager entityManager) {
        super(entityManager);
    }
}
