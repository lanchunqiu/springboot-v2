package com.springboot.chapter5.converter;

import com.springboot.chapter5.enumeration.SexEnum;

import javax.persistence.AttributeConverter;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/28
 **/
public class SexConverter implements AttributeConverter<SexEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(SexEnum sexEnum) {
        return sexEnum.getId();
    }

    @Override
    public SexEnum convertToEntityAttribute(Integer integer) {
        return SexEnum.getEnumById(integer);
    }
}
