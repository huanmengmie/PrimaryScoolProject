package com.primaryschool.home.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.primaryschool.home.dao.IManageDao;
import com.primaryschool.home.dao.ITypeFlagToTypeIdDao;

@Repository
public class ManageDao<T> implements IManageDao<T> {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private ITypeFlagToTypeIdDao typeFlagToTypeIdDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findManageInfo(String flag, int position, int item_per_page) {
		// TODO Auto-generated method stub
		int id=typeFlagToTypeIdDao.findManageTypeIdByTypeFlag(flag);
		String hql="from Manage m where m.typeId=? and m.isPublish=1 order by m.addTime desc";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		query.setFirstResult(position);
		query.setMaxResults(item_per_page);
		return query.list();
	}

	@Override
	public List<T> findManageInfoById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveManageInfo(int id, String flag, String title, String content, String add_time, int is_publish,
			int is_image) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int addManageInfo(String flag, String title, String content, String add_time, int is_publish, int is_image) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteManageById(String[] ids) {
		// TODO Auto-generated method stub
		return false;
	}

}