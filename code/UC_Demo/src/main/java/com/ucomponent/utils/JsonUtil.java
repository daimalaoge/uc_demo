package com.ucomponent.utils;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * 2018年9月30日
 * 代码老哥
 * NAME:Json操作类
 * Descp:
**/
public class JsonUtil {
  public static String object2Json(Object obj) {
    StringBuilder json = new StringBuilder();
    if (obj == null) {
      json.append("\"\"");
    } else if (obj instanceof String || obj instanceof Integer || obj instanceof Float || obj instanceof Boolean
        || obj instanceof Short || obj instanceof Double || obj instanceof Long || obj instanceof BigDecimal
        || obj instanceof BigInteger || obj instanceof Byte) {
      json.append("\"").append(string2Json(obj.toString())).append("\"");
    } else if (obj instanceof Object[]) {
      json.append(array2Json((Object[]) obj));
    } else if (obj instanceof List) {
      json.append(list2Json((List<?>) obj));
    } else if (obj instanceof Map) {
      json.append(map2Json((Map<?, ?>) obj));
    } else if (obj instanceof Set) {
      json.append(set2Json((Set<?>) obj));
    } else {
      json.append(bean2Json(obj));
    }
    return json.toString();
  }

  public static String bean2Json(Object bean) {
    StringBuilder json = new StringBuilder();
    json.append("{");
    PropertyDescriptor[] props = null;
    try {
      props = Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors();
    } catch (IntrospectionException e) {
    }
    if (props != null) {
      for (int i = 0; i < props.length; i++) {
        try {
          String name = object2Json(props[i].getName());
          String value = object2Json(props[i].getReadMethod().invoke(bean));
          json.append(name);
          json.append(":");
          json.append(value);
          json.append(",");
        } catch (Exception e) {
        }
      }
      json.setCharAt(json.length() - 1, '}');
    } else {
      json.append("}");
    }
    return json.toString();
  }

  public static String list2Json(List<?> list) {
    StringBuilder json = new StringBuilder();
    json.append("[");
    if (list != null && list.size() > 0) {
      for (Object obj : list) {
        json.append(object2Json(obj));
        json.append(",");
      }
      json.setCharAt(json.length() - 1, ']');
    } else {
      json.append("]");
    }
    return json.toString();
  }

  public static String array2Json(Object[] array) {
    StringBuilder json = new StringBuilder();
    json.append("[");
    if (array != null && array.length > 0) {
      for (Object obj : array) {
        json.append(object2Json(obj));
        json.append(",");
      }
      json.setCharAt(json.length() - 1, ']');
    } else {
      json.append("]");
    }
    return json.toString();
  }

  public static String map2Json(Map<?, ?> map) {
    StringBuilder json = new StringBuilder();
    json.append("{");
    if (map != null && map.size() > 0) {
      for (Object key : map.keySet()) {
        json.append(object2Json(key));
        json.append(":");
        json.append(object2Json(map.get(key)));
        json.append(",");
      }
      json.setCharAt(json.length() - 1, '}');
    } else {
      json.append("}");
    }
    return json.toString();
  }

  public static String set2Json(Set<?> set) {
    StringBuilder json = new StringBuilder();
    json.append("[");
    if (set != null && set.size() > 0) {
      for (Object obj : set) {
        json.append(object2Json(obj));
        json.append(",");
      }
      json.setCharAt(json.length() - 1, ']');
    } else {
      json.append("]");
    }
    return json.toString();
  }

  public static String string2Json(String s) {
    if (s == null)
      return "";
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      switch (ch) {
      case '"':
        sb.append("\\\"");
        break;
      case '\\':
        sb.append("\\\\");
        break;
      case '\b':
        sb.append("\\b");
        break;
      case '\f':
        sb.append("\\f");
        break;
      case '\n':
        sb.append("\\n");
        break;
      case '\r':
        sb.append("\\r");
        break;
      case '\t':
        sb.append("\\t");
        break;
      case '/':
        sb.append("\\/");
        break;
      default:
        if (ch >= '\u0000' && ch <= '\u001F') {
          String ss = Integer.toHexString(ch);
          sb.append("\\u");
          for (int k = 0; k < 4 - ss.length(); k++) {
            sb.append('0');
          }
          sb.append(ss.toUpperCase());
        } else {
          sb.append(ch);
        }
      }
    }
    return sb.toString();
  }
}
