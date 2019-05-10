package cn.doodlister.onlinejudge.vo;

import cn.doodlister.onlinejudge.utils.HashidUtil;
import io.swagger.models.auth.In;
import org.springframework.util.StringUtils;

import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;

public class BaseVo<T> {
    public BaseVo(){}


    /**
     * 不自动把id字段转换为hashid
     * @param clazz
     */
    public BaseVo(T clazz) {
        autoFillFields(clazz,false);
    }

    /**
     *
     * @param clazz
     * @param hashId hashid为true则转换id为hashid
     */
    public BaseVo(T clazz,Boolean hashId) {
        autoFillFields(clazz,hashId);
    }

    /**
     * 通过反射自动为子类字段赋值
     * 赋值规则为子类字段 与 clazz 名字相同且类型相同;
     * 如果hashID为TRUE且子类id字段为String类型且clazz id字段为Integer类型会自动HashidEndoce
     * @param clazz 赋值参考类
     * @param hashId 是否开启id字段hashid编码
     */
    private void autoFillFields(T clazz,Boolean hashId){
        HashMap<String,Field> hashMap =new HashMap<>();

        Class<?> entityClass = clazz.getClass();
        Field[] entityFields = entityClass.getDeclaredFields();
        for(int i=0;i<entityFields.length;++i){
            Field field = entityFields[i];
            hashMap.put(field.getName(),field);
        }

        Class<? extends BaseVo> voClass = this.getClass();
        Field[] voClassFields = voClass.getDeclaredFields(); //子类VO字段

        for(int i=0;i<voClassFields.length;++i){
            Field voField = voClassFields[i];
            String voFieldName = voField.getName();
            Field entityFiled = hashMap.get(voFieldName);

            if(entityFiled == null)
                continue;

            String entityFiledType = entityFiled.getType().getName();
            String voFiledType = voField.getType().getName();

            if(hashId){
                //进行hashiId 条件判断
                if(voFieldName.equals("id") && voFiledType.equals(String.class.getName())){
                    if(entityFiled.getName().equals("id") &&
                            (entityFiledType.equals(Integer.class.getName())||entityFiledType.equals("int"))){

                        voField.setAccessible(true);
                        entityFiled.setAccessible(true);
                        try {
                            Integer id = (Integer) entityFiled.get(clazz);
                            voField.set(this, HashidUtil.encode(id));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }finally {
                            continue;
                        }

                    }
                }
            }


            if(!entityFiledType.equals(voFiledType))
                continue;
            else{
                try {
                    voField.setAccessible(true);
                    entityFiled.setAccessible(true);
                    voField.set(this,entityFiled.get(clazz));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
