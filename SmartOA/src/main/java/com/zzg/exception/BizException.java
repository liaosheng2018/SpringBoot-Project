package com.zzg.exception;

import com.zzg.common.vo.enums.CodeMsgEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义业务异常
 */
public class BizException extends RuntimeException{
    private static Logger logger= LoggerFactory.getLogger(BizException.class);

    /**
     * 错误码
     */
    protected Integer code;
    /**
     * 错误信息
     */
    protected String message;

    public BizException() {
        super();
    }

    public BizException(CodeMsgEnum codeMsgEnum) {
        super(String.valueOf(codeMsgEnum.getCode()));
        this.code = codeMsgEnum.getCode();
        this.message = codeMsgEnum.getMessage();
    }

    public BizException(CodeMsgEnum codeMsgEnum, Throwable cause) {
        super(String.valueOf(codeMsgEnum.getCode()), cause);
        this.code = codeMsgEnum.getCode();
        this.message = codeMsgEnum.getMessage();
    }

    public BizException(String errorMsg) {
        super(errorMsg);
        this.message = errorMsg;
    }

    public BizException(Integer errorCode, String errorMsg) {
        super(String.valueOf(errorCode));
        this.code = errorCode;
        this.message = errorMsg;
    }

    public BizException(Integer errorCode, String errorMsg, Throwable cause) {
        super(String.valueOf(errorCode), cause);
        this.code = errorCode;
        this.message = errorMsg;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
