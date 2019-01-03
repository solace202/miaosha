package com.miaoshaproject.dataobject;

public class UserDo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_infor.id
     *
     * @mbg.generated Thu Jan 03 17:39:05 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_infor.name
     *
     * @mbg.generated Thu Jan 03 17:39:05 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_infor.gender
     *
     * @mbg.generated Thu Jan 03 17:39:05 CST 2019
     */
    private Integer gender;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_infor.age
     *
     * @mbg.generated Thu Jan 03 17:39:05 CST 2019
     */
    private Integer age;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_infor.telphone
     *
     * @mbg.generated Thu Jan 03 17:39:05 CST 2019
     */
    private String telphone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_infor.register_mode
     *
     * @mbg.generated Thu Jan 03 17:39:05 CST 2019
     */
    private String registerMode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_infor.third_party_id
     *
     * @mbg.generated Thu Jan 03 17:39:05 CST 2019
     */
    private String thirdPartyId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_infor.id
     *
     * @return the value of user_infor.id
     *
     * @mbg.generated Thu Jan 03 17:39:05 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_infor.id
     *
     * @param id the value for user_infor.id
     *
     * @mbg.generated Thu Jan 03 17:39:05 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_infor.name
     *
     * @return the value of user_infor.name
     *
     * @mbg.generated Thu Jan 03 17:39:05 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_infor.name
     *
     * @param name the value for user_infor.name
     *
     * @mbg.generated Thu Jan 03 17:39:05 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_infor.gender
     *
     * @return the value of user_infor.gender
     *
     * @mbg.generated Thu Jan 03 17:39:05 CST 2019
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_infor.gender
     *
     * @param gender the value for user_infor.gender
     *
     * @mbg.generated Thu Jan 03 17:39:05 CST 2019
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_infor.age
     *
     * @return the value of user_infor.age
     *
     * @mbg.generated Thu Jan 03 17:39:05 CST 2019
     */
    public Integer getAge() {
        return age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_infor.age
     *
     * @param age the value for user_infor.age
     *
     * @mbg.generated Thu Jan 03 17:39:05 CST 2019
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_infor.telphone
     *
     * @return the value of user_infor.telphone
     *
     * @mbg.generated Thu Jan 03 17:39:05 CST 2019
     */
    public String getTelphone() {
        return telphone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_infor.telphone
     *
     * @param telphone the value for user_infor.telphone
     *
     * @mbg.generated Thu Jan 03 17:39:05 CST 2019
     */
    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_infor.register_mode
     *
     * @return the value of user_infor.register_mode
     *
     * @mbg.generated Thu Jan 03 17:39:05 CST 2019
     */
    public String getRegisterMode() {
        return registerMode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_infor.register_mode
     *
     * @param registerMode the value for user_infor.register_mode
     *
     * @mbg.generated Thu Jan 03 17:39:05 CST 2019
     */
    public void setRegisterMode(String registerMode) {
        this.registerMode = registerMode == null ? null : registerMode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_infor.third_party_id
     *
     * @return the value of user_infor.third_party_id
     *
     * @mbg.generated Thu Jan 03 17:39:05 CST 2019
     */
    public String getThirdPartyId() {
        return thirdPartyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_infor.third_party_id
     *
     * @param thirdPartyId the value for user_infor.third_party_id
     *
     * @mbg.generated Thu Jan 03 17:39:05 CST 2019
     */
    public void setThirdPartyId(String thirdPartyId) {
        this.thirdPartyId = thirdPartyId == null ? null : thirdPartyId.trim();
    }
}