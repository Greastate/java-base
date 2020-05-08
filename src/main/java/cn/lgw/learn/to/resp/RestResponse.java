package cn.lgw.learn.to.resp;

import com.alibaba.fastjson.JSON;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_CONFLICT;
import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static javax.servlet.http.HttpServletResponse.SC_GATEWAY_TIMEOUT;
import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static javax.servlet.http.HttpServletResponse.SC_REQUEST_TIMEOUT;
import static javax.servlet.http.HttpServletResponse.SC_SERVICE_UNAVAILABLE;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

/**
 * REST响应类
 */
public abstract class RestResponse {

    private static final String DEFAULT_DATA = "";

    public abstract int getCode();

    public abstract <T> T getData();

    public static RestResponse ok() {
        return new OK(DEFAULT_DATA);
    }

    public static RestResponse ok(Object data) {
        return new OK(data);
    }

    public static RestResponse error(int code, String msg) {
        return new RestError(code, msg);
    }

    public static RestResponse badRequest(String msg) {
        return new BadRequest(msg);
    }

    public static RestResponse unauthorized() {
        return new Unauthorized("您无权访问");
    }

    public static RestResponse unauthorized(String msg) {
        return new Unauthorized(msg);
    }

    public static RestResponse forbidden(String msg) {
        return new ForBidden(msg);
    }

    public static RestResponse notFound(String msg) {
        return new NotFound(msg);
    }

    public static RestResponse requestTimeout(String msg) {
        return new RequestTimeout(msg);
    }

    public static RestResponse conflict(String msg) {
        return new Conflict(msg);
    }

    public static RestResponse serverError(String msg) {
        return new ServerRestError(msg);
    }

    public static RestResponse serviceUnavaiable(String msg) {
        return new ServiceUnavailable(msg);
    }

    public static RestResponse gatewayTimeout(String msg) {
        return new GatewayTimeout(msg);
    }

    public static RestResponse degradeServer(String msg) {
        return new DegradeServer(msg);
    }

    public static RestResponse paramInvalid(String msg) {
        return new ParamInvalid(msg);
    }

    public static RestResponse stateInvalid(String msg) {
        return new StateInvalid(msg);
    }

    public static RestResponse serverBusy() {
        return new ServerBusy();
    }

    static class Conflict extends RestError {

        public Conflict(String msg) {
            super(msg);
        }

        @Override
        public int getCode() {
            return SC_CONFLICT;
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }

    static class BadRequest extends RestError {

        public BadRequest(String msg) {
            super(msg);
        }

        @Override
        public int getCode() {
            return SC_BAD_REQUEST;
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }

    static class DegradeServer extends RestError {

        private static final int DEGRDE_SERVER_CODE = 600;

        public DegradeServer(String msg) {
            super(msg);
        }

        @Override
        public int getCode() {
            return DEGRDE_SERVER_CODE;
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }

    static class ForBidden extends RestError {

        public ForBidden(String msg) {
            super(msg);
        }

        @Override
        public int getCode() {
            return SC_FORBIDDEN;
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }

    static class GatewayTimeout extends RestError {

        public GatewayTimeout(String msg) {
            super(msg);
        }

        @Override
        public int getCode() {
            return SC_GATEWAY_TIMEOUT;
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }

    static class NotFound extends RestError {

        public NotFound(String msg) {
            super(msg);
        }

        @Override
        public int getCode() {
            return SC_NOT_FOUND;
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }

    static class OK extends RestResponse {

        public OK(Object data) {
            this.data = data;
        }

        private final Object data;

        @Override
        public int getCode() {
            return SC_OK;
        }

        @Override
        public <T> T getData() {
            return (T) this.data;
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }

    static class ParamInvalid extends RestError {

        private static final int PARAM_INVALID = 601;

        public ParamInvalid(String msg) {
            super(msg);
        }

        @Override
        public int getCode() {
            return PARAM_INVALID;
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }

    static class StateInvalid extends RestError {

        private static final int STATE_INVALID = 602;

        public StateInvalid(String msg) {
            super(msg);
        }

        @Override
        public int getCode() {
            return STATE_INVALID;
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }

    static class ServerBusy extends RestError {

        private static final int SERVER_BUSY = 603;

        public ServerBusy() {
            super("服务器繁忙，请稍后重试");
        }

        @Override
        public int getCode() {
            return SERVER_BUSY;
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }

    static class RequestTimeout extends RestError {

        public RequestTimeout(String msg) {
            super(msg);
        }

        @Override
        public int getCode() {
            return SC_REQUEST_TIMEOUT;
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }

    static class RestError extends RestResponse {

        public RestError(String msg) {
            this.msg = msg;
        }

        public RestError(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        protected int code;
        protected String msg;

        @Override
        public <T> T getData() {
            return null;
        }

        @Override
        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }

    static class ServerRestError extends RestError {

        public ServerRestError(String msg) {
            super(msg);
        }

        @Override
        public int getCode() {
            return SC_INTERNAL_SERVER_ERROR;
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }

    static class ServiceUnavailable extends RestError {

        public ServiceUnavailable(String msg) {
            super(msg);
        }

        @Override
        public int getCode() {
            return SC_SERVICE_UNAVAILABLE;
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }

    static class Unauthorized extends RestError {

        public Unauthorized(String msg) {
            super(msg);
        }

        @Override
        public int getCode() {
            return SC_UNAUTHORIZED;
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }
}


