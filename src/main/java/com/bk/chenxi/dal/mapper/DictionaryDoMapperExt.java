package com.bk.chenxi.dal.mapper;

import com.bk.chenxi.dal.model.DictionaryDo;

import javax.annotation.Resource;
import java.lang.reflect.Type;

@Resource
public interface DictionaryDoMapperExt extends DictionaryDoMapper {

    public int countTheSameTypeAndNameEntity(DictionaryDo dictionaryDo);

    public String selectDicKeyByDictionaryId(Integer id);
}
