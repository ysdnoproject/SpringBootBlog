package com.example.demo.doma.listener;

import com.example.demo.doma.annotation.TimestampableEntity;
import com.example.demo.doma.entity.BaseEntity;
import org.seasar.doma.jdbc.entity.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

public class CommonListener implements EntityListener<BaseEntity> {
    public void preInsert(BaseEntity entity, PreInsertContext context) {
        if (entity.getClass().isAnnotationPresent(TimestampableEntity.class)) {
            try {
                TimestampableEntity ano = entity.getClass().getDeclaredAnnotation(TimestampableEntity.class);
                Method m = entity.getClass().getDeclaredMethod(ano.createdAtSetter(), Date.class);
                m.invoke(entity, new Date());
                Method m2 = entity.getClass().getDeclaredMethod(ano.updatedAtSetter(), Date.class);
                m2.invoke(entity, new Date());
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                // TODO
//                throw e;
            }
        }
    }

    public void preUpdate(BaseEntity entity, PreUpdateContext context) {
        if (entity.getClass().isAnnotationPresent(TimestampableEntity.class)) {
            try {
                TimestampableEntity ano = entity.getClass().getDeclaredAnnotation(TimestampableEntity.class);
                Method m2 = entity.getClass().getDeclaredMethod(ano.updatedAtSetter(), Date.class);
                m2.invoke(entity, new Date());
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                // TODO
//                throw e;
            }
        }
    }

    public void preDelete(BaseEntity entity, PreDeleteContext context) {
    }

    public void postInsert(BaseEntity entity, PostInsertContext context) {
    }

    public void postUpdate(BaseEntity entity, PostUpdateContext context) {
    }

    public void postDelete(BaseEntity entity, PostDeleteContext context) {
    }
}
