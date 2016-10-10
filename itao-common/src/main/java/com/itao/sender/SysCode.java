package com.itao.sender;

/**
 * @author Created by Vicdor(linss) on 2016-05-12 01:24.
 */
public class SysCode {
    private static SysCode consts = new SysCode();
    public String UNKNOWN = "-2000";
    public String RULE_ILLEGAL = "-2001";
    public String IP_ILLEGAL = "-2002";
    public String LOGIN_ILLEGAL = "-2003";
    public String RIGHTS_ILLEGAL = "-2004";
    public String SUCC = "100";
    public String CODENAME = "code";
    public String CODEMSG = "msg";

    private SysCode() {
    }

    public static SysCode newInstance() {
        return consts;
    }

    public static void setConfig(SysCode sysConst) {
        consts = sysConst;
    }

    public void setUNKNOWN(String uNKNOW) {
        this.UNKNOWN = uNKNOW;
    }

    public void setRULE_ILLEGAL(String rULEILLEGAL) {
        this.RULE_ILLEGAL = rULEILLEGAL;
    }

    public void setIP_ILLEGAL(String iPILLEGAL) {
        this.IP_ILLEGAL = iPILLEGAL;
    }

    public void setLOGIN_ILLEGAL(String lOGINILLEGAL) {
        this.LOGIN_ILLEGAL = lOGINILLEGAL;
    }

    public void setRIGHTS_ILLEGAL(String rIGHTSILLEGAL) {
        this.RIGHTS_ILLEGAL = rIGHTSILLEGAL;
    }

    public void setSUCC(String sUCC) {
        this.SUCC = sUCC;
    }

    public void setCODENAME(String cODENAME) {
        this.CODENAME = cODENAME;
    }

    public void setCODEMSG(String cODEMSG) {
        this.CODEMSG = cODEMSG;
    }
}
