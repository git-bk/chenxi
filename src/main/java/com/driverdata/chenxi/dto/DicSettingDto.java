package com.driverdata.chenxi.dto;

import com.driverdata.chenxi.dal.model.DicSettingDo;
import com.driverdata.chenxi.dal.plugin.Page;

/**
 * Created by wb-yaobingke on 2016/5/13.
 */
public class DicSettingDto extends DicSettingDo {
    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    private Page page;
}
