package com.seraph.hrms.database.dao;

import java.io.Serializable;

import com.seraph.hrms.database.entity.base.BaseID;
import com.seraph.hrms.database.prototype.Prototype;

/**
 * The DAO interface.
 * 
 * @param <T> the persistent class, the class must implement {@link BaseID}
 * @param <ID> the type of the primary key of the class, the class must implement
 *          {@link Serializable}
 *
 * @version 1.0, Jan 22, 2016
 *
 */
public interface DAO<T extends BaseID<ID>, ID extends Serializable>
		extends Prototype<T, ID>, com.seraph.hrms.database.dao.base.DAO<T, ID>
{

}