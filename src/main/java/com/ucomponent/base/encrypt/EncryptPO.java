package com.ucomponent.base.encrypt;

import com.ucomponent.base.controller.BaseLayuiVO;

import java.lang.reflect.Method;
import java.util.List;

public class EncryptPO {
	/**
	 * 加密
	 * @param bo
	 * @return
	 */
	public static BaseLayuiVO encPO(BaseLayuiVO bo){
		try {
			Method method = bo.getClass().getMethod("getId", null);
			int l =(Integer)method.invoke(bo, null);
			Method pmethod = bo.getClass().getMethod("setEncCode", String.class);
			pmethod.invoke(bo, new EncryptStringGen().encrypt(String.valueOf(l)));

		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bo;
	}
	/**
	 * 解密
	 * @param bo
	 * @return
	 */
	public static BaseLayuiVO decPO(BaseLayuiVO bo){
		try {
			Method method = bo.getClass().getMethod("getEncCode", null);
			String l =(String)method.invoke(bo, null);
			Method pmethod = bo.getClass().getMethod("setId", int.class);
			pmethod.invoke(bo, Integer.parseInt(new EncryptStringGen().decrypt(l)));
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bo;
	}
	/**
	 * 加密
	 * @param bo
	 * @param mod
	 * @return
	 */
	public static BaseLayuiVO encPO(BaseLayuiVO bo,String mod){
		try {
			Method method = bo.getClass().getMethod("get" +mod, null);
			int l =(Integer)method.invoke(bo, null);
			Method pmethod = bo.getClass().getMethod("setEncCode", String.class);
			pmethod.invoke(bo, new EncryptStringGen().encrypt(String.valueOf(l)));

		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bo;
	}
	/**
	 * 解密
	 * @param bo
	 * @param mod
	 * @return
	 */
	public static BaseLayuiVO decPO(BaseLayuiVO bo,String mod){
		try {
			Method method = bo.getClass().getMethod("getEncCode", null);
			String l =(String)method.invoke(bo, null);
			Method pmethod = bo.getClass().getMethod("set" + mod, long.class);
			pmethod.invoke(bo, Long.parseLong(new EncryptStringGen().decrypt(l)));
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bo;
	}
	/**
	 * 加密EncCode
	 * @param bo
	 * @param mod
	 * @param key
	 * @return
	 */
	public static BaseLayuiVO encPOKey(BaseLayuiVO bo,String mod,String key){
		try {
			Method method = bo.getClass().getMethod("get" +mod, null);
			int l =(Integer)method.invoke(bo, null);
			Method pmethod = bo.getClass().getMethod("setEncCode", String.class);
			pmethod.invoke(bo, new EncryptStringGen(key).encrypt(String.valueOf(l)));

		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bo;
	}
	/**
	 * 解密
	 * @param bo
	 * @param mod
	 * @param key
	 * @return
	 */
	public static BaseLayuiVO decPOKey(BaseLayuiVO bo,String mod,String key){
		try {
			Method method = bo.getClass().getMethod("getEncCode", null);
			String l =(String)method.invoke(bo, null);
			Method pmethod = bo.getClass().getMethod("set" + mod, long.class);
			pmethod.invoke(bo, Long.parseLong(new EncryptStringGen(key).decrypt(l)));
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bo;
	}
	/**
	 * 加密->EncCode
	 * @param list
	 * @return
	 */
	public static List encList(List<BaseLayuiVO> list){
		try {
			for(Object bo:list){
				Method method = bo.getClass().getMethod("getId", null);
				int l =(Integer)method.invoke(bo, null);
				Method pmethod = bo.getClass().getMethod("setEncCode", String.class);
				pmethod.invoke(bo, new EncryptStringGen().encrypt(String.valueOf(l)));

			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 加密->EncCode
	 * @param list
	 * @param mod
	 * @return
	 */
	public static List encList(List<BaseLayuiVO> list,String mod){
		try {
			for(BaseLayuiVO bo:list){
				Method method = bo.getClass().getMethod("get"+mod, null);
				String l =(String)method.invoke(bo, null).toString();
				Method pmethod = bo.getClass().getMethod("setEncCode", String.class);
				pmethod.invoke(bo, new EncryptStringGen().encrypt(l));
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 加密->EncCode
	 * @param list
	 * @param key
	 * @return
	 */
	public static List encListKey(List<BaseLayuiVO> list,String key){
		try {
			for(BaseLayuiVO bo:list){
				Method method = bo.getClass().getMethod("getId", null);
				int l =(Integer)method.invoke(bo, null);
				Method pmethod = bo.getClass().getMethod("setEncCode", String.class);
				pmethod.invoke(bo, new EncryptStringGen(key).encrypt(String.valueOf(l)));
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 加密
	 * @param list
	 * @param mod
	 * @param key
	 * @return
	 */
	public static List encListKey(List<BaseLayuiVO> list,String mod,String key){
		try {
			for(BaseLayuiVO bo:list){
				Method method = bo.getClass().getMethod("get"+mod, null);
				String l =(String)method.invoke(bo, null).toString();
				Method pmethod = bo.getClass().getMethod("setEncCode", String.class);
				pmethod.invoke(bo, new EncryptStringGen(key).encrypt(l));
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
