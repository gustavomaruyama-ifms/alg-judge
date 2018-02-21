package br.ifms.cx.algjudge.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Response {

    private String status;
    private String msg;
    private Object data;

    public Response() {
    }

    public static Response Ok(Object obj) {
        Response r = new Response();
        r.setStatus("OK");
        r.setData(obj);
        r.setMsg("EMPTY");
        return r;
    }

    public static Response Ok(String msg) {
        Response r = new Response();
        r.setStatus("OK");
        r.setData("EMPTY");
        r.setMsg(msg);
        return r;
    }

    public static Response Ok(String msg, Object obj) {
        Response r = new Response();
        r.setStatus("OK");
        r.setMsg(msg);
        r.setData(obj);
        return r;
    }

    public static Response Error(String msg) {
        Response r = new Response();
        r.setStatus("ERROR");
        r.setMsg(msg);
        return r;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
