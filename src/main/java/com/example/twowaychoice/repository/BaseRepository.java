package com.example.twowaychoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 因为spring-data-jpa没有实现EntityManager中的refresh方法，所以需要自己实现
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean//防止spring自动创建对象
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {
    T refresh(T t);
}
