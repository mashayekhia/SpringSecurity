package ir.man.spring.security.service;

import org.springframework.stereotype.Service;

public interface IDao<T> {
    T register(T t);
}
