package com.example.mysqlsharding.model;

public class User {

    private String userName;
    private String passWord;
    private String sqlSessionFactoryBeanName;
    private String sqlSessionBeanName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getSqlSessionFactoryBeanName() {
        return sqlSessionFactoryBeanName;
    }

    public void setSqlSessionFactoryBeanName(String sqlSessionFactoryBeanName) {
        this.sqlSessionFactoryBeanName = sqlSessionFactoryBeanName;
    }

    public String getSqlSessionBeanName() {
        return sqlSessionBeanName;
    }

    public void setSqlSessionBeanName(String sqlSessionBeanName) {
        this.sqlSessionBeanName = sqlSessionBeanName;
    }
}
