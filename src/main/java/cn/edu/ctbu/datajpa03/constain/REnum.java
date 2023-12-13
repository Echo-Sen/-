package cn.edu.ctbu.datajpa03.constain;



public enum REnum {
    UNKNOWN_ERR(-999,"未知错误"),
    COMMON_ERR(-10,"一般性错误"),
    LOGIN_ERR(-2,"出错了，不正确的用户名密码！！"),

    SUCCESS(1,"成功");

    private final Integer code;
    private final String msg;
    REnum(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
    public Integer getCode(){

        return code;
    }
    public String getMsg(){
        return msg;
    }


}
