package com.example.demo.converter;

import com.example.demo.doma.dao.BaseDao;
import com.example.demo.doma.entity.BaseEntity;
import com.example.demo.exception.ResourceNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class IdToEntityConverterFactory implements ConverterFactory<String, BaseEntity> {
    @NonNull
    ApplicationContext applicationContext;

    @RequiredArgsConstructor
    private class IdToEntityConverter<T extends BaseEntity> implements Converter<String, T> {
        @NonNull
        private Class<T> entity;

        @SuppressWarnings("unchecked")
        public T convert(String source) {
            String[] beanNames = applicationContext.getBeanNamesForType(ResolvableType.forClassWithGenerics(BaseDao.class, entity));
            BaseDao dao = (BaseDao) applicationContext.getBean(beanNames[0]);
            Optional<T> result = dao.findById(Long.parseLong(source));

            if (result.isPresent()) {
                return result.get();
            } else {
                throw new ResourceNotFoundException();
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends BaseEntity> Converter<String, T> getConverter(Class<T> targetType) {
        return new IdToEntityConverter(targetType);
    }
}
