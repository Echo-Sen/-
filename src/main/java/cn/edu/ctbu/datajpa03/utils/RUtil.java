package cn.edu.ctbu.datajpa03.utils;

import cn.edu.ctbu.datajpa03.constain.REnum;
import cn.edu.ctbu.datajpa03.vo.R;

public class RUtil {
    public static R success(Object object){
        R result=new R();
        result.setCode(1);
        result.setData(object);
        result.setMsg("成功");
        return result;
    }
    public static R success(){

        return success(null);
    }
    public static R error(Integer code, String msg){
        R result=new R();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
    public static R error(REnum rEnum){
        R result=new R();
        result.setCode(rEnum.getCode());
        result.setMsg(result.getMsg());
        return result;
    }

}
