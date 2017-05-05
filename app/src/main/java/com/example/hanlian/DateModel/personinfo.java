package com.example.hanlian.DateModel;

import java.util.List;

/**
 * Created by Administrator on 2017/5/2.
 */

public class personinfo {


    /**
     * ErrorCode : 0
     * ErrorMsg : null
     * Token : 9FEE28405425AADE657F21D8A8685B52FB7112369062D5D739DA9998EA3D887EB1171E4EFACD726C78E6D6DA7193B505
     * PageNum : 0
     * Result : {"$id":"1","CM_USERID":"20170483184524161","CM_SHOPNAME":"黄磊","CM_PHONE":15949569024,"CM_ACCOUNT":"110","CM_PASSWORD":"123","CM_INTEGRAL":0,"CM_BALANCE":0,"CM_CARDPATH":"C://Applications/0.png|","CM_STOREPATH":"C://Applications/1.png|","CM_LICENSEPATH":"C://Applications/2.png|","CM_SHOPEADDRESS":"敬源食品华中大厦","CM_SHOPLON":2235,"CM_SHOPLAT":123,"CM_LEVEL":0,"CM_CREATETIME":"2017-04-12T18:45:24.163","CM_ISEXAMINE":0,"CM_REASON":null,"CM_CARDNO":"331081198211115839","CM_NAME":"张文贵","CM_CONTACTNAME":"张","CM_CONTACTPHONE":"15157263333","CM_TELEPHONE":"","CM_PAX":"","EntityKey":{"$id":"2","EntitySetName":"TB_USERS","EntityContainerName":"SCEntities","EntityKeyValues":[{"Key":"CM_USERID","Type":"System.String","Value":"20170483184524161"}]}}
     */

    private int ErrorCode;
    private Object ErrorMsg;
    private String Token;
    private int PageNum;
    private ResultBean Result;

    public int getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(int ErrorCode) {
        this.ErrorCode = ErrorCode;
    }

    public Object getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(Object ErrorMsg) {
        this.ErrorMsg = ErrorMsg;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public int getPageNum() {
        return PageNum;
    }

    public void setPageNum(int PageNum) {
        this.PageNum = PageNum;
    }

    public ResultBean getResult() {
        return Result;
    }

    public void setResult(ResultBean Result) {
        this.Result = Result;
    }

    public static class ResultBean {
        /**
         * $id : 1
         * CM_USERID : 20170483184524161
         * CM_SHOPNAME : 黄磊
         * CM_PHONE : 15949569024
         * CM_ACCOUNT : 110
         * CM_PASSWORD : 123
         * CM_INTEGRAL : 0
         * CM_BALANCE : 0
         * CM_CARDPATH : C://Applications/0.png|
         * CM_STOREPATH : C://Applications/1.png|
         * CM_LICENSEPATH : C://Applications/2.png|
         * CM_SHOPEADDRESS : 敬源食品华中大厦
         * CM_SHOPLON : 2235
         * CM_SHOPLAT : 123
         * CM_LEVEL : 0
         * CM_CREATETIME : 2017-04-12T18:45:24.163
         * CM_ISEXAMINE : 0
         * CM_REASON : null
         * CM_CARDNO : 331081198211115839
         * CM_NAME : 张文贵
         * CM_CONTACTNAME : 张
         * CM_CONTACTPHONE : 15157263333
         * CM_TELEPHONE :
         * CM_PAX :
         * EntityKey : {"$id":"2","EntitySetName":"TB_USERS","EntityContainerName":"SCEntities","EntityKeyValues":[{"Key":"CM_USERID","Type":"System.String","Value":"20170483184524161"}]}
         */
        private String $id;
        private String CM_USERID;
        private String CM_SHOPNAME;
        private long CM_PHONE;
        private String CM_ACCOUNT;
        private String CM_PASSWORD;
        private int CM_INTEGRAL;
        private int CM_BALANCE;
        private String CM_CARDPATH;
        private String CM_STOREPATH;
        private String CM_LICENSEPATH;
        private String CM_SHOPEADDRESS;
        private int CM_SHOPLON;
        private int CM_SHOPLAT;
        private int CM_LEVEL;
        private String CM_CREATETIME;
        private int CM_ISEXAMINE;
        private Object CM_REASON;
        private String CM_CARDNO;
        private String CM_NAME;
        private String CM_CONTACTNAME;
        private String CM_CONTACTPHONE;
        private String CM_TELEPHONE;
        private String CM_PAX;
        private EntityKeyBean EntityKey;

        public String get$id() {
            return $id;
        }

        public void set$id(String $id) {
            this.$id = $id;
        }

        public String getCM_USERID() {
            return CM_USERID;
        }

        public void setCM_USERID(String CM_USERID) {
            this.CM_USERID = CM_USERID;
        }

        public String getCM_SHOPNAME() {
            return CM_SHOPNAME;
        }

        public void setCM_SHOPNAME(String CM_SHOPNAME) {
            this.CM_SHOPNAME = CM_SHOPNAME;
        }

        public long getCM_PHONE() {
            return CM_PHONE;
        }

        public void setCM_PHONE(long CM_PHONE) {
            this.CM_PHONE = CM_PHONE;
        }

        public String getCM_ACCOUNT() {
            return CM_ACCOUNT;
        }

        public void setCM_ACCOUNT(String CM_ACCOUNT) {
            this.CM_ACCOUNT = CM_ACCOUNT;
        }

        public String getCM_PASSWORD() {
            return CM_PASSWORD;
        }

        public void setCM_PASSWORD(String CM_PASSWORD) {
            this.CM_PASSWORD = CM_PASSWORD;
        }

        public int getCM_INTEGRAL() {
            return CM_INTEGRAL;
        }

        public void setCM_INTEGRAL(int CM_INTEGRAL) {
            this.CM_INTEGRAL = CM_INTEGRAL;
        }

        public int getCM_BALANCE() {
            return CM_BALANCE;
        }

        public void setCM_BALANCE(int CM_BALANCE) {
            this.CM_BALANCE = CM_BALANCE;
        }

        public String getCM_CARDPATH() {
            return CM_CARDPATH;
        }

        public void setCM_CARDPATH(String CM_CARDPATH) {
            this.CM_CARDPATH = CM_CARDPATH;
        }

        public String getCM_STOREPATH() {
            return CM_STOREPATH;
        }

        public void setCM_STOREPATH(String CM_STOREPATH) {
            this.CM_STOREPATH = CM_STOREPATH;
        }

        public String getCM_LICENSEPATH() {
            return CM_LICENSEPATH;
        }

        public void setCM_LICENSEPATH(String CM_LICENSEPATH) {
            this.CM_LICENSEPATH = CM_LICENSEPATH;
        }

        public String getCM_SHOPEADDRESS() {
            return CM_SHOPEADDRESS;
        }

        public void setCM_SHOPEADDRESS(String CM_SHOPEADDRESS) {
            this.CM_SHOPEADDRESS = CM_SHOPEADDRESS;
        }

        public int getCM_SHOPLON() {
            return CM_SHOPLON;
        }

        public void setCM_SHOPLON(int CM_SHOPLON) {
            this.CM_SHOPLON = CM_SHOPLON;
        }

        public int getCM_SHOPLAT() {
            return CM_SHOPLAT;
        }

        public void setCM_SHOPLAT(int CM_SHOPLAT) {
            this.CM_SHOPLAT = CM_SHOPLAT;
        }

        public int getCM_LEVEL() {
            return CM_LEVEL;
        }

        public void setCM_LEVEL(int CM_LEVEL) {
            this.CM_LEVEL = CM_LEVEL;
        }

        public String getCM_CREATETIME() {
            return CM_CREATETIME;
        }

        public void setCM_CREATETIME(String CM_CREATETIME) {
            this.CM_CREATETIME = CM_CREATETIME;
        }

        public int getCM_ISEXAMINE() {
            return CM_ISEXAMINE;
        }

        public void setCM_ISEXAMINE(int CM_ISEXAMINE) {
            this.CM_ISEXAMINE = CM_ISEXAMINE;
        }

        public Object getCM_REASON() {
            return CM_REASON;
        }

        public void setCM_REASON(Object CM_REASON) {
            this.CM_REASON = CM_REASON;
        }

        public String getCM_CARDNO() {
            return CM_CARDNO;
        }

        public void setCM_CARDNO(String CM_CARDNO) {
            this.CM_CARDNO = CM_CARDNO;
        }

        public String getCM_NAME() {
            return CM_NAME;
        }

        public void setCM_NAME(String CM_NAME) {
            this.CM_NAME = CM_NAME;
        }

        public String getCM_CONTACTNAME() {
            return CM_CONTACTNAME;
        }

        public void setCM_CONTACTNAME(String CM_CONTACTNAME) {
            this.CM_CONTACTNAME = CM_CONTACTNAME;
        }

        public String getCM_CONTACTPHONE() {
            return CM_CONTACTPHONE;
        }

        public void setCM_CONTACTPHONE(String CM_CONTACTPHONE) {
            this.CM_CONTACTPHONE = CM_CONTACTPHONE;
        }

        public String getCM_TELEPHONE() {
            return CM_TELEPHONE;
        }

        public void setCM_TELEPHONE(String CM_TELEPHONE) {
            this.CM_TELEPHONE = CM_TELEPHONE;
        }

        public String getCM_PAX() {
            return CM_PAX;
        }

        public void setCM_PAX(String CM_PAX) {
            this.CM_PAX = CM_PAX;
        }

        public EntityKeyBean getEntityKey() {
            return EntityKey;
        }

        public void setEntityKey(EntityKeyBean EntityKey) {
            this.EntityKey = EntityKey;
        }

        public static class EntityKeyBean {
            /**
             * $id : 2
             * EntitySetName : TB_USERS
             * EntityContainerName : SCEntities
             * EntityKeyValues : [{"Key":"CM_USERID","Type":"System.String","Value":"20170483184524161"}]
             */

            private String $id;
            private String EntitySetName;
            private String EntityContainerName;
            private List<EntityKeyValuesBean> EntityKeyValues;

            public String get$id() {
                return $id;
            }

            public void set$id(String $id) {
                this.$id = $id;
            }

            public String getEntitySetName() {
                return EntitySetName;
            }

            public void setEntitySetName(String EntitySetName) {
                this.EntitySetName = EntitySetName;
            }

            public String getEntityContainerName() {
                return EntityContainerName;
            }

            public void setEntityContainerName(String EntityContainerName) {
                this.EntityContainerName = EntityContainerName;
            }

            public List<EntityKeyValuesBean> getEntityKeyValues() {
                return EntityKeyValues;
            }

            public void setEntityKeyValues(List<EntityKeyValuesBean> EntityKeyValues) {
                this.EntityKeyValues = EntityKeyValues;
            }

            public static class EntityKeyValuesBean {
                /**
                 * Key : CM_USERID
                 * Type : System.String
                 * Value : 20170483184524161
                 */

                private String Key;
                private String Type;
                private String Value;

                public String getKey() {
                    return Key;
                }

                public void setKey(String Key) {
                    this.Key = Key;
                }

                public String getType() {
                    return Type;
                }

                public void setType(String Type) {
                    this.Type = Type;
                }

                public String getValue() {
                    return Value;
                }

                public void setValue(String Value) {
                    this.Value = Value;
                }
            }
        }
    }
}



