package com.driverdata.chenxi.dal.mapper;

import com.driverdata.chenxi.dal.model.DictionaryDo;

public interface DictionaryDoMapperExt extends DictionaryDoMapper {

    public int countTheSameTypeAndNameEntity(DictionaryDo dictionaryDo);

    public String selectDicKeyByDictionaryId(Integer id);
}
