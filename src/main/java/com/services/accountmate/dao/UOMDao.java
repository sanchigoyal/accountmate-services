package com.services.accountmate.dao;

import java.util.List;

import com.services.accountmate.bean.UnitOfMeasureEntity;

public interface UOMDao {

	List<UnitOfMeasureEntity> getUnitOfMeasures(String userUUID);

	UnitOfMeasureEntity getUnitOfMeasure(String userUUID, int uomId);

	UnitOfMeasureEntity createUnitOfMeasure(UnitOfMeasureEntity unitOfMeasureEntity);

	UnitOfMeasureEntity updateUnitOfMeasure(UnitOfMeasureEntity unitOfMeasureEntity);

	UnitOfMeasureEntity deleteUnitOfMeasure(String userUUID, int uomId);

}
