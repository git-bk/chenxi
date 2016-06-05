package com.driverdata.chenxi.biz;

import java.util.List;

import com.driverdata.chenxi.dto.DicSettingDto;
import com.driverdata.chenxi.dto.DictionaryDto;
import com.driverdata.chenxi.exception.ChanceValidateException;

public interface DictionaryBo {

    DictionaryDto insertEntity(DictionaryDto entity) throws ChanceValidateException;

    void updateEntity(DictionaryDto entity) throws ChanceValidateException;

    void removeEntity(DictionaryDto entity) throws ChanceValidateException;

    List<DictionaryDto> findEntityByDto(DictionaryDto entity) throws ChanceValidateException;

    DictionaryDto findEntityById(Integer id) throws ChanceValidateException;

    List<DictionaryDto> findEntityByDtoFuzzy(DictionaryDto entity) throws ChanceValidateException;

    public List<DicSettingDto> findDicSettingByDto(DicSettingDto entity) throws ChanceValidateException;

    DicSettingDto findDicSettingById(Integer id) throws ChanceValidateException;

    void updateDicSetting(DicSettingDto entity) throws ChanceValidateException;

}
