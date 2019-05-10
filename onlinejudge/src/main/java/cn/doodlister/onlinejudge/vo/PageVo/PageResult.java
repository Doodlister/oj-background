package cn.doodlister.onlinejudge.vo.PageVo;

import cn.doodlister.onlinejudge.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


@ToString
public class PageResult<T>{
    @Getter
    @Setter
    private int total;
    @Getter
    @Setter
    private int page;
    @Getter
    @Setter
    private int pageSize = 10;
    @Getter
    @Setter
    private List data;
    private Page<T> pageData;
    public PageResult(Page<T> pageData,Class<T> clazz){
        data = new ArrayList<>();
        this.pageData = pageData;
        this.pageSize=pageData.getSize();
        this.total = (int) pageData.getTotalElements();
        this.page = pageData.getNumber();
        String voName = clazz.getName()+"Vo";
        voName = voName.replace("entity", "vo");

        try {
            Class<?> voClass = voClass = Class.forName(voName);
            data = new ArrayList<>();
            for (T t : pageData.getContent()) {
                Constructor<?> constructor = voClass.getConstructor(t.getClass());
                Object o =  constructor.newInstance(t);
                data.add(o);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }
}
