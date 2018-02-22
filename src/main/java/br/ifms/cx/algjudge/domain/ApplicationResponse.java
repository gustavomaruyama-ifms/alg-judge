package br.ifms.cx.algjudge.domain;

import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ApplicationResponse {

    private String status;
    private String msg;
    private Object data;

    public ApplicationResponse() {
    }

    public static Response ok(Object obj) {
        ApplicationResponse r = new ApplicationResponse();
        r.setStatus("OK");
        r.setData(obj);
        return Response.status(Response.Status.OK).entity(r).build();
    }

    public static Response ok(String msg) {
        ApplicationResponse r = new ApplicationResponse();
        r.setStatus("OK");
        r.setMsg(msg);
        return Response.status(Response.Status.OK).entity(r).build();
    }

    public static Response ok(String msg, Object obj) {
        ApplicationResponse r = new ApplicationResponse();
        r.setStatus("OK");
        r.setMsg(msg);
        r.setData(obj);
        return Response.status(Response.Status.OK).entity(r).build();
    }

    public static Response erroInterno(String msg) {
        ApplicationResponse r = new ApplicationResponse();
        r.setStatus("ERROR");
        r.setMsg(msg);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(r).build();
    }

    public static Response naoEncontrado(String msg) {
        ApplicationResponse r = new ApplicationResponse();
        r.setStatus("ERROR");
        r.setMsg(msg);
        return Response.status(Response.Status.NOT_FOUND).entity(r).build();
    }

    public static Response requisicaoInvalida(String msg) {
        ApplicationResponse r = new ApplicationResponse();
        r.setStatus("ERROR");
        r.setMsg(msg);
        return Response.status(Response.Status.BAD_REQUEST).entity(r).build();
    }

    public static Response naoAutorizado(String msg) {
        ApplicationResponse r = new ApplicationResponse();
        r.setStatus("ERROR");
        r.setMsg(msg);
        return Response.status(Response.Status.UNAUTHORIZED).entity(r).build();
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
