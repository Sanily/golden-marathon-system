package com.example.marathon.constant.Enum;

public enum ResponseCode {
    OK(0, "OK", "OK"),
    Unauthorized(10001, "认证不通过", "Unauthorized"),
    UserNotActivated(10002, "用户未激活", "user was not activated"),
    Forbidden(10003, "没有权限", "Forbidden"),
    Exception(20001, "", ""),
    UnkownException(20002, "未知错误", "Unknow Exception"),
    ParamValidException(20003, "参数校验异常", "Param Valid Exception"),
    BusinessException(20004, "业务异常", "Business Exception"),
    HttpRequestMethodNotSupportedException(20005, "当前http调用方法不支持,以下是支持的方法:%s", "HttpRequest Method Not Supported Exception,Supported Methods:%s"),
    URISyntaxException(20006, "URI语法有误,原因:%s", "URI Syntax Exception,reason:%s"),
    NoAuthor(401, "用户未登录，无法访问受保护资源", "Sorry,you are not login,please login"),
    TOKEN_TIMEOUT(4011, "此token已超时,请重新获取", "token already time out"),
    TOKEN_ERROR(4012, "非法的token结构,请仔细检查处理逻辑", "token is not correct"),
    EMPTY(50002, "查询结果为空", "NO RESULT"),
    SERVER_STOP(50003, "服务维护中", "SERVER_STOP"),
    TOO_MANY_REQUEST(50005, "频繁请求", "TOO_MANY_REQUEST"),
    TASK_RUN_ERROR(50102, "用户请求执行任务，任务执行失败", "Sorry,the task you committed throw an exception"),
    TASK_RUNNING(50104, "任务正在执行，请稍后", "The task is running please wait for a moment");


    private int errorcode;
    private String message;
    private String message_en;

    private ResponseCode(int errorcode, String message, String message_en) {
        this.errorcode = errorcode;
        this.message = message;
        this.message_en = message_en;
    }

    public int getErrorcode() {
        return this.errorcode;
    }

    public void setErrorcode(int errorcode) {
        this.errorcode = errorcode;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage_en() {
        return this.message_en;
    }

    public void setMessage_en(String message_en) {
        this.message_en = message_en;
    }
}
