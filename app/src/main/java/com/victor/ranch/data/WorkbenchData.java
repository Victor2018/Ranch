package com.victor.ranch.data;

import android.view.Gravity;

import java.util.List;

/**
 * @Author Victor
 * @Date Create on 2018/2/5 09:34.
 * @Describe
 */

public class WorkbenchData {
    public int category_id;
    public String category_name;
    public List<WorkbenchCellData> content;

    public  int gravity = Gravity.CENTER_HORIZONTAL;
}
