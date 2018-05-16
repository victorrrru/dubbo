package com.fww.callback;

/**
 * @author 范文武
 * @date 2018/05/16 14:35
 */
public class Remote {
    public void executeMessage(String msg,CallBack callBack){

        for(int i=0;i < 1000000000;i++){
            ;
        }
        System.out.println("excute msg:"+msg);
        msg = msg+"msg change...";
        callBack.execute(msg);
    }
}
