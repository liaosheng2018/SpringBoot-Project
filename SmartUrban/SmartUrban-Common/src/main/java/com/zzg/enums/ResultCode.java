package com.zzg.enums;

public enum ResultCode implements java.io.Serializable {

    /** 操作成功 */
    SUCCESS(200, "成功"),

    /** 操作失败 */
    FAIL(500, "操作失败"),

    /** 空指针异常 */
    NullpointerException(500, "空指针异常"),

    /** token失效 */
    TokenExpire(500,"token失效 ");

  /* private static final Map<Integer, ResultCode> MAP = Arrays.stream(ResultCode.values()).collect(Collectors.toMap(ResultCode::getVal, Function.identity()));

   public static ResultCode getResultCode(Integer code){
       return MAP.get(code);
   }*/
    ResultCode(int value, String msg){
        this.val = value;
        this.msg = msg;
    }

    public int val() {
        return val;
    }

    public String msg() {
        return msg;
    }

    private int val;
    private String msg;

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
