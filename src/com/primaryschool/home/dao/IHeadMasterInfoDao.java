package com.primaryschool.home.dao;

import java.util.List;

public interface IHeadMasterInfoDao<T> {
    //获取校长信息
	List<T> findHeadMasterInfo();
}
