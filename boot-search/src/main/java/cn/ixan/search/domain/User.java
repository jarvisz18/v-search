package cn.ixan.search.domain;

import io.searchbox.annotations.JestId;
import lombok.Data;

/**
 * 学生实体类
 *
 * @author stackzhang@126.com
 */
@Data
public class User {
    /**
     * 学号
     */
    @JestId
    private Integer studentId;
    /**
     * 学院
     */
    private String college;
    /**
     * 姓名
     */
    private String name;

    private String idCard;

    private String sex;

    private String education;

    private String profession;

    private String teacherFlag;

    private String normalStudentCategory;

    private String difficultStudentCategory;

    private String trainMethod;

    private String schoolSystem;

    private String nationality;

    private String politicalStatus;

    private String birthday;

    private String admissionTime;

    private String graduationTime;

    private String bachelorOfScience;

    private String direction;

    private String studentArea;

    private String isCountryArea;

    private String address;

    private String postcode;

    private String candidateNumber;

    private String mobile;

    private String mailbox;

    private String qqNumber;

    private String phone;
}