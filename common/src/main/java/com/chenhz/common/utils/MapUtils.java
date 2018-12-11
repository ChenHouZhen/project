/**
 *
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.chenhz.common.utils;

import com.chenhz.common.utils.format.Format;
import com.chenhz.common.utils.format.FormatFactory;
import com.chenhz.common.utils.format.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;


/**
 * Map工具类
 *
 * @author Mark sunlightcs@gmail.com
 * @since 3.0.0
 */
public class MapUtils extends HashMap<String, Object> {

    private static final Logger log = LoggerFactory.getLogger(MapUtils.class);

    @Override
    public MapUtils put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * pojo对象转Map
     * @param obj pojo
     * @return object
     */
    public static Map<String, Object> map(Object obj){
        Field[] fields = obj.getClass().getDeclaredFields();
        Map<String,Object> m = new HashMap<>(fields.length);
        for (Field field : fields){
            String varName = field.getName();
            try {
                boolean accessFlag = field.isAccessible();
                field.setAccessible(true);
                Object o = field.get(obj);
                Format f =  FormatFactory.creatInstance(Type.getFormat(field.getGenericType().toString()));
                m.put(varName,f.format(o));
                field.setAccessible(accessFlag);
            }catch (IllegalAccessException ex) {
                log.error("对象转 MAP 工具类 出错！错误信息如下: {}",ex.getMessage());
            }
        }
        return m;
    }


    /**
     * object 转 Object
     * @param map object
     * @param cls class
     * @return obj
     */
    public static Object object(Map<String,Object> map , Class<?> cls){
        if (MapUtils.isNullMap(map)){
            return null;
        }
        try {
            Object obj = cls.newInstance();
            Field[] declareFields = cls.getDeclaredFields();
            for (Field field:declareFields) {
                String varName = field.getName();
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)){
                    continue;
                }
                field.setAccessible(true);
                Object val = map.get(varName);
                if (null != val){
                    if (val.getClass() != field.getType()){
                        val = null;
                    }
                }

                //获取注解,注解的结果会覆盖原值
                FieldTransform fieldTransform = field.getAnnotation(FieldTransform.class);
                if (null != fieldTransform){
                    Class cla = fieldTransform.trans();
                    if (cla != Class.class){
                        Transform t = (Transform) cla.newInstance();
                        val = t.trans(map.get(varName));
                    }
                }

                field.set(obj, val);
            }
            return obj;
        } catch (Exception e) {
            log.error("Map 转 Obj 工具类 出错！错误信息如下: {}",e.getMessage());
        }
        return null;
    }

    public static <T,H> boolean isNullMap(Map<T,H> m){
        return m == null || m.size() == 0;
    }

}


