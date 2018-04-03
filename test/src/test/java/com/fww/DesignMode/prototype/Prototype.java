package com.fww.DesignMode.prototype;

/**
 * Created by victorrrr
 * Date : 2017/7/4 17:15
 */
    public class Prototype implements Cloneable {

        private String name;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public Object clone(){
            try {
                return super.clone();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

