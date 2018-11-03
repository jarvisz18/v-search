package cn.ixan.search.domain;

import java.util.ArrayList;
import java.util.List;
/**
 * @author stack_zhang@outlook.com
 */
public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andStudentIdIsNull() {
            addCriterion("student_id is null");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNotNull() {
            addCriterion("student_id is not null");
            return (Criteria) this;
        }

        public Criteria andStudentIdEqualTo(Integer value) {
            addCriterion("student_id =", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotEqualTo(Integer value) {
            addCriterion("student_id <>", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThan(Integer value) {
            addCriterion("student_id >", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("student_id >=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThan(Integer value) {
            addCriterion("student_id <", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThanOrEqualTo(Integer value) {
            addCriterion("student_id <=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdIn(List<Integer> values) {
            addCriterion("student_id in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotIn(List<Integer> values) {
            addCriterion("student_id not in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdBetween(Integer value1, Integer value2) {
            addCriterion("student_id between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("student_id not between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andCollegeIsNull() {
            addCriterion("college is null");
            return (Criteria) this;
        }

        public Criteria andCollegeIsNotNull() {
            addCriterion("college is not null");
            return (Criteria) this;
        }

        public Criteria andCollegeEqualTo(String value) {
            addCriterion("college =", value, "college");
            return (Criteria) this;
        }

        public Criteria andCollegeNotEqualTo(String value) {
            addCriterion("college <>", value, "college");
            return (Criteria) this;
        }

        public Criteria andCollegeGreaterThan(String value) {
            addCriterion("college >", value, "college");
            return (Criteria) this;
        }

        public Criteria andCollegeGreaterThanOrEqualTo(String value) {
            addCriterion("college >=", value, "college");
            return (Criteria) this;
        }

        public Criteria andCollegeLessThan(String value) {
            addCriterion("college <", value, "college");
            return (Criteria) this;
        }

        public Criteria andCollegeLessThanOrEqualTo(String value) {
            addCriterion("college <=", value, "college");
            return (Criteria) this;
        }

        public Criteria andCollegeLike(String value) {
            addCriterion("college like", value, "college");
            return (Criteria) this;
        }

        public Criteria andCollegeNotLike(String value) {
            addCriterion("college not like", value, "college");
            return (Criteria) this;
        }

        public Criteria andCollegeIn(List<String> values) {
            addCriterion("college in", values, "college");
            return (Criteria) this;
        }

        public Criteria andCollegeNotIn(List<String> values) {
            addCriterion("college not in", values, "college");
            return (Criteria) this;
        }

        public Criteria andCollegeBetween(String value1, String value2) {
            addCriterion("college between", value1, value2, "college");
            return (Criteria) this;
        }

        public Criteria andCollegeNotBetween(String value1, String value2) {
            addCriterion("college not between", value1, value2, "college");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNull() {
            addCriterion("Id_card is null");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNotNull() {
            addCriterion("Id_card is not null");
            return (Criteria) this;
        }

        public Criteria andIdCardEqualTo(String value) {
            addCriterion("Id_card =", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotEqualTo(String value) {
            addCriterion("Id_card <>", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThan(String value) {
            addCriterion("Id_card >", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThanOrEqualTo(String value) {
            addCriterion("Id_card >=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThan(String value) {
            addCriterion("Id_card <", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThanOrEqualTo(String value) {
            addCriterion("Id_card <=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLike(String value) {
            addCriterion("Id_card like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotLike(String value) {
            addCriterion("Id_card not like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardIn(List<String> values) {
            addCriterion("Id_card in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotIn(List<String> values) {
            addCriterion("Id_card not in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardBetween(String value1, String value2) {
            addCriterion("Id_card between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotBetween(String value1, String value2) {
            addCriterion("Id_card not between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(String value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(String value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(String value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(String value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(String value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(String value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLike(String value) {
            addCriterion("sex like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotLike(String value) {
            addCriterion("sex not like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<String> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<String> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(String value1, String value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(String value1, String value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andEducationIsNull() {
            addCriterion("education is null");
            return (Criteria) this;
        }

        public Criteria andEducationIsNotNull() {
            addCriterion("education is not null");
            return (Criteria) this;
        }

        public Criteria andEducationEqualTo(String value) {
            addCriterion("education =", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotEqualTo(String value) {
            addCriterion("education <>", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationGreaterThan(String value) {
            addCriterion("education >", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationGreaterThanOrEqualTo(String value) {
            addCriterion("education >=", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLessThan(String value) {
            addCriterion("education <", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLessThanOrEqualTo(String value) {
            addCriterion("education <=", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLike(String value) {
            addCriterion("education like", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotLike(String value) {
            addCriterion("education not like", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationIn(List<String> values) {
            addCriterion("education in", values, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotIn(List<String> values) {
            addCriterion("education not in", values, "education");
            return (Criteria) this;
        }

        public Criteria andEducationBetween(String value1, String value2) {
            addCriterion("education between", value1, value2, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotBetween(String value1, String value2) {
            addCriterion("education not between", value1, value2, "education");
            return (Criteria) this;
        }

        public Criteria andProfessionIsNull() {
            addCriterion("profession is null");
            return (Criteria) this;
        }

        public Criteria andProfessionIsNotNull() {
            addCriterion("profession is not null");
            return (Criteria) this;
        }

        public Criteria andProfessionEqualTo(String value) {
            addCriterion("profession =", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionNotEqualTo(String value) {
            addCriterion("profession <>", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionGreaterThan(String value) {
            addCriterion("profession >", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionGreaterThanOrEqualTo(String value) {
            addCriterion("profession >=", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionLessThan(String value) {
            addCriterion("profession <", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionLessThanOrEqualTo(String value) {
            addCriterion("profession <=", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionLike(String value) {
            addCriterion("profession like", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionNotLike(String value) {
            addCriterion("profession not like", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionIn(List<String> values) {
            addCriterion("profession in", values, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionNotIn(List<String> values) {
            addCriterion("profession not in", values, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionBetween(String value1, String value2) {
            addCriterion("profession between", value1, value2, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionNotBetween(String value1, String value2) {
            addCriterion("profession not between", value1, value2, "profession");
            return (Criteria) this;
        }

        public Criteria andTeacherFlagIsNull() {
            addCriterion("teacher_flag is null");
            return (Criteria) this;
        }

        public Criteria andTeacherFlagIsNotNull() {
            addCriterion("teacher_flag is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherFlagEqualTo(String value) {
            addCriterion("teacher_flag =", value, "teacherFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherFlagNotEqualTo(String value) {
            addCriterion("teacher_flag <>", value, "teacherFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherFlagGreaterThan(String value) {
            addCriterion("teacher_flag >", value, "teacherFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherFlagGreaterThanOrEqualTo(String value) {
            addCriterion("teacher_flag >=", value, "teacherFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherFlagLessThan(String value) {
            addCriterion("teacher_flag <", value, "teacherFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherFlagLessThanOrEqualTo(String value) {
            addCriterion("teacher_flag <=", value, "teacherFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherFlagLike(String value) {
            addCriterion("teacher_flag like", value, "teacherFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherFlagNotLike(String value) {
            addCriterion("teacher_flag not like", value, "teacherFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherFlagIn(List<String> values) {
            addCriterion("teacher_flag in", values, "teacherFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherFlagNotIn(List<String> values) {
            addCriterion("teacher_flag not in", values, "teacherFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherFlagBetween(String value1, String value2) {
            addCriterion("teacher_flag between", value1, value2, "teacherFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherFlagNotBetween(String value1, String value2) {
            addCriterion("teacher_flag not between", value1, value2, "teacherFlag");
            return (Criteria) this;
        }

        public Criteria andNormalStudentCategoryIsNull() {
            addCriterion("normal_student_category is null");
            return (Criteria) this;
        }

        public Criteria andNormalStudentCategoryIsNotNull() {
            addCriterion("normal_student_category is not null");
            return (Criteria) this;
        }

        public Criteria andNormalStudentCategoryEqualTo(String value) {
            addCriterion("normal_student_category =", value, "normalStudentCategory");
            return (Criteria) this;
        }

        public Criteria andNormalStudentCategoryNotEqualTo(String value) {
            addCriterion("normal_student_category <>", value, "normalStudentCategory");
            return (Criteria) this;
        }

        public Criteria andNormalStudentCategoryGreaterThan(String value) {
            addCriterion("normal_student_category >", value, "normalStudentCategory");
            return (Criteria) this;
        }

        public Criteria andNormalStudentCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("normal_student_category >=", value, "normalStudentCategory");
            return (Criteria) this;
        }

        public Criteria andNormalStudentCategoryLessThan(String value) {
            addCriterion("normal_student_category <", value, "normalStudentCategory");
            return (Criteria) this;
        }

        public Criteria andNormalStudentCategoryLessThanOrEqualTo(String value) {
            addCriterion("normal_student_category <=", value, "normalStudentCategory");
            return (Criteria) this;
        }

        public Criteria andNormalStudentCategoryLike(String value) {
            addCriterion("normal_student_category like", value, "normalStudentCategory");
            return (Criteria) this;
        }

        public Criteria andNormalStudentCategoryNotLike(String value) {
            addCriterion("normal_student_category not like", value, "normalStudentCategory");
            return (Criteria) this;
        }

        public Criteria andNormalStudentCategoryIn(List<String> values) {
            addCriterion("normal_student_category in", values, "normalStudentCategory");
            return (Criteria) this;
        }

        public Criteria andNormalStudentCategoryNotIn(List<String> values) {
            addCriterion("normal_student_category not in", values, "normalStudentCategory");
            return (Criteria) this;
        }

        public Criteria andNormalStudentCategoryBetween(String value1, String value2) {
            addCriterion("normal_student_category between", value1, value2, "normalStudentCategory");
            return (Criteria) this;
        }

        public Criteria andNormalStudentCategoryNotBetween(String value1, String value2) {
            addCriterion("normal_student_category not between", value1, value2, "normalStudentCategory");
            return (Criteria) this;
        }

        public Criteria andDifficultStudentCategoryIsNull() {
            addCriterion("difficult_student_category is null");
            return (Criteria) this;
        }

        public Criteria andDifficultStudentCategoryIsNotNull() {
            addCriterion("difficult_student_category is not null");
            return (Criteria) this;
        }

        public Criteria andDifficultStudentCategoryEqualTo(String value) {
            addCriterion("difficult_student_category =", value, "difficultStudentCategory");
            return (Criteria) this;
        }

        public Criteria andDifficultStudentCategoryNotEqualTo(String value) {
            addCriterion("difficult_student_category <>", value, "difficultStudentCategory");
            return (Criteria) this;
        }

        public Criteria andDifficultStudentCategoryGreaterThan(String value) {
            addCriterion("difficult_student_category >", value, "difficultStudentCategory");
            return (Criteria) this;
        }

        public Criteria andDifficultStudentCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("difficult_student_category >=", value, "difficultStudentCategory");
            return (Criteria) this;
        }

        public Criteria andDifficultStudentCategoryLessThan(String value) {
            addCriterion("difficult_student_category <", value, "difficultStudentCategory");
            return (Criteria) this;
        }

        public Criteria andDifficultStudentCategoryLessThanOrEqualTo(String value) {
            addCriterion("difficult_student_category <=", value, "difficultStudentCategory");
            return (Criteria) this;
        }

        public Criteria andDifficultStudentCategoryLike(String value) {
            addCriterion("difficult_student_category like", value, "difficultStudentCategory");
            return (Criteria) this;
        }

        public Criteria andDifficultStudentCategoryNotLike(String value) {
            addCriterion("difficult_student_category not like", value, "difficultStudentCategory");
            return (Criteria) this;
        }

        public Criteria andDifficultStudentCategoryIn(List<String> values) {
            addCriterion("difficult_student_category in", values, "difficultStudentCategory");
            return (Criteria) this;
        }

        public Criteria andDifficultStudentCategoryNotIn(List<String> values) {
            addCriterion("difficult_student_category not in", values, "difficultStudentCategory");
            return (Criteria) this;
        }

        public Criteria andDifficultStudentCategoryBetween(String value1, String value2) {
            addCriterion("difficult_student_category between", value1, value2, "difficultStudentCategory");
            return (Criteria) this;
        }

        public Criteria andDifficultStudentCategoryNotBetween(String value1, String value2) {
            addCriterion("difficult_student_category not between", value1, value2, "difficultStudentCategory");
            return (Criteria) this;
        }

        public Criteria andTrainMethodIsNull() {
            addCriterion("train_method is null");
            return (Criteria) this;
        }

        public Criteria andTrainMethodIsNotNull() {
            addCriterion("train_method is not null");
            return (Criteria) this;
        }

        public Criteria andTrainMethodEqualTo(String value) {
            addCriterion("train_method =", value, "trainMethod");
            return (Criteria) this;
        }

        public Criteria andTrainMethodNotEqualTo(String value) {
            addCriterion("train_method <>", value, "trainMethod");
            return (Criteria) this;
        }

        public Criteria andTrainMethodGreaterThan(String value) {
            addCriterion("train_method >", value, "trainMethod");
            return (Criteria) this;
        }

        public Criteria andTrainMethodGreaterThanOrEqualTo(String value) {
            addCriterion("train_method >=", value, "trainMethod");
            return (Criteria) this;
        }

        public Criteria andTrainMethodLessThan(String value) {
            addCriterion("train_method <", value, "trainMethod");
            return (Criteria) this;
        }

        public Criteria andTrainMethodLessThanOrEqualTo(String value) {
            addCriterion("train_method <=", value, "trainMethod");
            return (Criteria) this;
        }

        public Criteria andTrainMethodLike(String value) {
            addCriterion("train_method like", value, "trainMethod");
            return (Criteria) this;
        }

        public Criteria andTrainMethodNotLike(String value) {
            addCriterion("train_method not like", value, "trainMethod");
            return (Criteria) this;
        }

        public Criteria andTrainMethodIn(List<String> values) {
            addCriterion("train_method in", values, "trainMethod");
            return (Criteria) this;
        }

        public Criteria andTrainMethodNotIn(List<String> values) {
            addCriterion("train_method not in", values, "trainMethod");
            return (Criteria) this;
        }

        public Criteria andTrainMethodBetween(String value1, String value2) {
            addCriterion("train_method between", value1, value2, "trainMethod");
            return (Criteria) this;
        }

        public Criteria andTrainMethodNotBetween(String value1, String value2) {
            addCriterion("train_method not between", value1, value2, "trainMethod");
            return (Criteria) this;
        }

        public Criteria andSchoolSystemIsNull() {
            addCriterion("school_system is null");
            return (Criteria) this;
        }

        public Criteria andSchoolSystemIsNotNull() {
            addCriterion("school_system is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolSystemEqualTo(String value) {
            addCriterion("school_system =", value, "schoolSystem");
            return (Criteria) this;
        }

        public Criteria andSchoolSystemNotEqualTo(String value) {
            addCriterion("school_system <>", value, "schoolSystem");
            return (Criteria) this;
        }

        public Criteria andSchoolSystemGreaterThan(String value) {
            addCriterion("school_system >", value, "schoolSystem");
            return (Criteria) this;
        }

        public Criteria andSchoolSystemGreaterThanOrEqualTo(String value) {
            addCriterion("school_system >=", value, "schoolSystem");
            return (Criteria) this;
        }

        public Criteria andSchoolSystemLessThan(String value) {
            addCriterion("school_system <", value, "schoolSystem");
            return (Criteria) this;
        }

        public Criteria andSchoolSystemLessThanOrEqualTo(String value) {
            addCriterion("school_system <=", value, "schoolSystem");
            return (Criteria) this;
        }

        public Criteria andSchoolSystemLike(String value) {
            addCriterion("school_system like", value, "schoolSystem");
            return (Criteria) this;
        }

        public Criteria andSchoolSystemNotLike(String value) {
            addCriterion("school_system not like", value, "schoolSystem");
            return (Criteria) this;
        }

        public Criteria andSchoolSystemIn(List<String> values) {
            addCriterion("school_system in", values, "schoolSystem");
            return (Criteria) this;
        }

        public Criteria andSchoolSystemNotIn(List<String> values) {
            addCriterion("school_system not in", values, "schoolSystem");
            return (Criteria) this;
        }

        public Criteria andSchoolSystemBetween(String value1, String value2) {
            addCriterion("school_system between", value1, value2, "schoolSystem");
            return (Criteria) this;
        }

        public Criteria andSchoolSystemNotBetween(String value1, String value2) {
            addCriterion("school_system not between", value1, value2, "schoolSystem");
            return (Criteria) this;
        }

        public Criteria andNationalityIsNull() {
            addCriterion("nationality is null");
            return (Criteria) this;
        }

        public Criteria andNationalityIsNotNull() {
            addCriterion("nationality is not null");
            return (Criteria) this;
        }

        public Criteria andNationalityEqualTo(String value) {
            addCriterion("nationality =", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityNotEqualTo(String value) {
            addCriterion("nationality <>", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityGreaterThan(String value) {
            addCriterion("nationality >", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityGreaterThanOrEqualTo(String value) {
            addCriterion("nationality >=", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityLessThan(String value) {
            addCriterion("nationality <", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityLessThanOrEqualTo(String value) {
            addCriterion("nationality <=", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityLike(String value) {
            addCriterion("nationality like", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityNotLike(String value) {
            addCriterion("nationality not like", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityIn(List<String> values) {
            addCriterion("nationality in", values, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityNotIn(List<String> values) {
            addCriterion("nationality not in", values, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityBetween(String value1, String value2) {
            addCriterion("nationality between", value1, value2, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityNotBetween(String value1, String value2) {
            addCriterion("nationality not between", value1, value2, "nationality");
            return (Criteria) this;
        }

        public Criteria andPoliticalStatusIsNull() {
            addCriterion("political_status is null");
            return (Criteria) this;
        }

        public Criteria andPoliticalStatusIsNotNull() {
            addCriterion("political_status is not null");
            return (Criteria) this;
        }

        public Criteria andPoliticalStatusEqualTo(String value) {
            addCriterion("political_status =", value, "politicalStatus");
            return (Criteria) this;
        }

        public Criteria andPoliticalStatusNotEqualTo(String value) {
            addCriterion("political_status <>", value, "politicalStatus");
            return (Criteria) this;
        }

        public Criteria andPoliticalStatusGreaterThan(String value) {
            addCriterion("political_status >", value, "politicalStatus");
            return (Criteria) this;
        }

        public Criteria andPoliticalStatusGreaterThanOrEqualTo(String value) {
            addCriterion("political_status >=", value, "politicalStatus");
            return (Criteria) this;
        }

        public Criteria andPoliticalStatusLessThan(String value) {
            addCriterion("political_status <", value, "politicalStatus");
            return (Criteria) this;
        }

        public Criteria andPoliticalStatusLessThanOrEqualTo(String value) {
            addCriterion("political_status <=", value, "politicalStatus");
            return (Criteria) this;
        }

        public Criteria andPoliticalStatusLike(String value) {
            addCriterion("political_status like", value, "politicalStatus");
            return (Criteria) this;
        }

        public Criteria andPoliticalStatusNotLike(String value) {
            addCriterion("political_status not like", value, "politicalStatus");
            return (Criteria) this;
        }

        public Criteria andPoliticalStatusIn(List<String> values) {
            addCriterion("political_status in", values, "politicalStatus");
            return (Criteria) this;
        }

        public Criteria andPoliticalStatusNotIn(List<String> values) {
            addCriterion("political_status not in", values, "politicalStatus");
            return (Criteria) this;
        }

        public Criteria andPoliticalStatusBetween(String value1, String value2) {
            addCriterion("political_status between", value1, value2, "politicalStatus");
            return (Criteria) this;
        }

        public Criteria andPoliticalStatusNotBetween(String value1, String value2) {
            addCriterion("political_status not between", value1, value2, "politicalStatus");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNull() {
            addCriterion("birthday is null");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNotNull() {
            addCriterion("birthday is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdayEqualTo(String value) {
            addCriterion("birthday =", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotEqualTo(String value) {
            addCriterion("birthday <>", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThan(String value) {
            addCriterion("birthday >", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThanOrEqualTo(String value) {
            addCriterion("birthday >=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThan(String value) {
            addCriterion("birthday <", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThanOrEqualTo(String value) {
            addCriterion("birthday <=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLike(String value) {
            addCriterion("birthday like", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotLike(String value) {
            addCriterion("birthday not like", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayIn(List<String> values) {
            addCriterion("birthday in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotIn(List<String> values) {
            addCriterion("birthday not in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayBetween(String value1, String value2) {
            addCriterion("birthday between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotBetween(String value1, String value2) {
            addCriterion("birthday not between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeIsNull() {
            addCriterion("admission_time is null");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeIsNotNull() {
            addCriterion("admission_time is not null");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeEqualTo(String value) {
            addCriterion("admission_time =", value, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeNotEqualTo(String value) {
            addCriterion("admission_time <>", value, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeGreaterThan(String value) {
            addCriterion("admission_time >", value, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeGreaterThanOrEqualTo(String value) {
            addCriterion("admission_time >=", value, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeLessThan(String value) {
            addCriterion("admission_time <", value, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeLessThanOrEqualTo(String value) {
            addCriterion("admission_time <=", value, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeLike(String value) {
            addCriterion("admission_time like", value, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeNotLike(String value) {
            addCriterion("admission_time not like", value, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeIn(List<String> values) {
            addCriterion("admission_time in", values, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeNotIn(List<String> values) {
            addCriterion("admission_time not in", values, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeBetween(String value1, String value2) {
            addCriterion("admission_time between", value1, value2, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeNotBetween(String value1, String value2) {
            addCriterion("admission_time not between", value1, value2, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeIsNull() {
            addCriterion("graduation_time is null");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeIsNotNull() {
            addCriterion("graduation_time is not null");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeEqualTo(String value) {
            addCriterion("graduation_time =", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeNotEqualTo(String value) {
            addCriterion("graduation_time <>", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeGreaterThan(String value) {
            addCriterion("graduation_time >", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeGreaterThanOrEqualTo(String value) {
            addCriterion("graduation_time >=", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeLessThan(String value) {
            addCriterion("graduation_time <", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeLessThanOrEqualTo(String value) {
            addCriterion("graduation_time <=", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeLike(String value) {
            addCriterion("graduation_time like", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeNotLike(String value) {
            addCriterion("graduation_time not like", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeIn(List<String> values) {
            addCriterion("graduation_time in", values, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeNotIn(List<String> values) {
            addCriterion("graduation_time not in", values, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeBetween(String value1, String value2) {
            addCriterion("graduation_time between", value1, value2, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeNotBetween(String value1, String value2) {
            addCriterion("graduation_time not between", value1, value2, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andBachelorOfScienceIsNull() {
            addCriterion("bachelor_of_science is null");
            return (Criteria) this;
        }

        public Criteria andBachelorOfScienceIsNotNull() {
            addCriterion("bachelor_of_science is not null");
            return (Criteria) this;
        }

        public Criteria andBachelorOfScienceEqualTo(String value) {
            addCriterion("bachelor_of_science =", value, "bachelorOfScience");
            return (Criteria) this;
        }

        public Criteria andBachelorOfScienceNotEqualTo(String value) {
            addCriterion("bachelor_of_science <>", value, "bachelorOfScience");
            return (Criteria) this;
        }

        public Criteria andBachelorOfScienceGreaterThan(String value) {
            addCriterion("bachelor_of_science >", value, "bachelorOfScience");
            return (Criteria) this;
        }

        public Criteria andBachelorOfScienceGreaterThanOrEqualTo(String value) {
            addCriterion("bachelor_of_science >=", value, "bachelorOfScience");
            return (Criteria) this;
        }

        public Criteria andBachelorOfScienceLessThan(String value) {
            addCriterion("bachelor_of_science <", value, "bachelorOfScience");
            return (Criteria) this;
        }

        public Criteria andBachelorOfScienceLessThanOrEqualTo(String value) {
            addCriterion("bachelor_of_science <=", value, "bachelorOfScience");
            return (Criteria) this;
        }

        public Criteria andBachelorOfScienceLike(String value) {
            addCriterion("bachelor_of_science like", value, "bachelorOfScience");
            return (Criteria) this;
        }

        public Criteria andBachelorOfScienceNotLike(String value) {
            addCriterion("bachelor_of_science not like", value, "bachelorOfScience");
            return (Criteria) this;
        }

        public Criteria andBachelorOfScienceIn(List<String> values) {
            addCriterion("bachelor_of_science in", values, "bachelorOfScience");
            return (Criteria) this;
        }

        public Criteria andBachelorOfScienceNotIn(List<String> values) {
            addCriterion("bachelor_of_science not in", values, "bachelorOfScience");
            return (Criteria) this;
        }

        public Criteria andBachelorOfScienceBetween(String value1, String value2) {
            addCriterion("bachelor_of_science between", value1, value2, "bachelorOfScience");
            return (Criteria) this;
        }

        public Criteria andBachelorOfScienceNotBetween(String value1, String value2) {
            addCriterion("bachelor_of_science not between", value1, value2, "bachelorOfScience");
            return (Criteria) this;
        }

        public Criteria andDirectionIsNull() {
            addCriterion("direction is null");
            return (Criteria) this;
        }

        public Criteria andDirectionIsNotNull() {
            addCriterion("direction is not null");
            return (Criteria) this;
        }

        public Criteria andDirectionEqualTo(String value) {
            addCriterion("direction =", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionNotEqualTo(String value) {
            addCriterion("direction <>", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionGreaterThan(String value) {
            addCriterion("direction >", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionGreaterThanOrEqualTo(String value) {
            addCriterion("direction >=", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionLessThan(String value) {
            addCriterion("direction <", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionLessThanOrEqualTo(String value) {
            addCriterion("direction <=", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionLike(String value) {
            addCriterion("direction like", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionNotLike(String value) {
            addCriterion("direction not like", value, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionIn(List<String> values) {
            addCriterion("direction in", values, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionNotIn(List<String> values) {
            addCriterion("direction not in", values, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionBetween(String value1, String value2) {
            addCriterion("direction between", value1, value2, "direction");
            return (Criteria) this;
        }

        public Criteria andDirectionNotBetween(String value1, String value2) {
            addCriterion("direction not between", value1, value2, "direction");
            return (Criteria) this;
        }

        public Criteria andStudentAreaIsNull() {
            addCriterion("student_area is null");
            return (Criteria) this;
        }

        public Criteria andStudentAreaIsNotNull() {
            addCriterion("student_area is not null");
            return (Criteria) this;
        }

        public Criteria andStudentAreaEqualTo(String value) {
            addCriterion("student_area =", value, "studentArea");
            return (Criteria) this;
        }

        public Criteria andStudentAreaNotEqualTo(String value) {
            addCriterion("student_area <>", value, "studentArea");
            return (Criteria) this;
        }

        public Criteria andStudentAreaGreaterThan(String value) {
            addCriterion("student_area >", value, "studentArea");
            return (Criteria) this;
        }

        public Criteria andStudentAreaGreaterThanOrEqualTo(String value) {
            addCriterion("student_area >=", value, "studentArea");
            return (Criteria) this;
        }

        public Criteria andStudentAreaLessThan(String value) {
            addCriterion("student_area <", value, "studentArea");
            return (Criteria) this;
        }

        public Criteria andStudentAreaLessThanOrEqualTo(String value) {
            addCriterion("student_area <=", value, "studentArea");
            return (Criteria) this;
        }

        public Criteria andStudentAreaLike(String value) {
            addCriterion("student_area like", value, "studentArea");
            return (Criteria) this;
        }

        public Criteria andStudentAreaNotLike(String value) {
            addCriterion("student_area not like", value, "studentArea");
            return (Criteria) this;
        }

        public Criteria andStudentAreaIn(List<String> values) {
            addCriterion("student_area in", values, "studentArea");
            return (Criteria) this;
        }

        public Criteria andStudentAreaNotIn(List<String> values) {
            addCriterion("student_area not in", values, "studentArea");
            return (Criteria) this;
        }

        public Criteria andStudentAreaBetween(String value1, String value2) {
            addCriterion("student_area between", value1, value2, "studentArea");
            return (Criteria) this;
        }

        public Criteria andStudentAreaNotBetween(String value1, String value2) {
            addCriterion("student_area not between", value1, value2, "studentArea");
            return (Criteria) this;
        }

        public Criteria andIsCountryareaIsNull() {
            addCriterion("is_countryArea is null");
            return (Criteria) this;
        }

        public Criteria andIsCountryareaIsNotNull() {
            addCriterion("is_countryArea is not null");
            return (Criteria) this;
        }

        public Criteria andIsCountryareaEqualTo(String value) {
            addCriterion("is_countryArea =", value, "isCountryarea");
            return (Criteria) this;
        }

        public Criteria andIsCountryareaNotEqualTo(String value) {
            addCriterion("is_countryArea <>", value, "isCountryarea");
            return (Criteria) this;
        }

        public Criteria andIsCountryareaGreaterThan(String value) {
            addCriterion("is_countryArea >", value, "isCountryarea");
            return (Criteria) this;
        }

        public Criteria andIsCountryareaGreaterThanOrEqualTo(String value) {
            addCriterion("is_countryArea >=", value, "isCountryarea");
            return (Criteria) this;
        }

        public Criteria andIsCountryareaLessThan(String value) {
            addCriterion("is_countryArea <", value, "isCountryarea");
            return (Criteria) this;
        }

        public Criteria andIsCountryareaLessThanOrEqualTo(String value) {
            addCriterion("is_countryArea <=", value, "isCountryarea");
            return (Criteria) this;
        }

        public Criteria andIsCountryareaLike(String value) {
            addCriterion("is_countryArea like", value, "isCountryarea");
            return (Criteria) this;
        }

        public Criteria andIsCountryareaNotLike(String value) {
            addCriterion("is_countryArea not like", value, "isCountryarea");
            return (Criteria) this;
        }

        public Criteria andIsCountryareaIn(List<String> values) {
            addCriterion("is_countryArea in", values, "isCountryarea");
            return (Criteria) this;
        }

        public Criteria andIsCountryareaNotIn(List<String> values) {
            addCriterion("is_countryArea not in", values, "isCountryarea");
            return (Criteria) this;
        }

        public Criteria andIsCountryareaBetween(String value1, String value2) {
            addCriterion("is_countryArea between", value1, value2, "isCountryarea");
            return (Criteria) this;
        }

        public Criteria andIsCountryareaNotBetween(String value1, String value2) {
            addCriterion("is_countryArea not between", value1, value2, "isCountryarea");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andPostcodeIsNull() {
            addCriterion("postcode is null");
            return (Criteria) this;
        }

        public Criteria andPostcodeIsNotNull() {
            addCriterion("postcode is not null");
            return (Criteria) this;
        }

        public Criteria andPostcodeEqualTo(String value) {
            addCriterion("postcode =", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotEqualTo(String value) {
            addCriterion("postcode <>", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeGreaterThan(String value) {
            addCriterion("postcode >", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeGreaterThanOrEqualTo(String value) {
            addCriterion("postcode >=", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLessThan(String value) {
            addCriterion("postcode <", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLessThanOrEqualTo(String value) {
            addCriterion("postcode <=", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLike(String value) {
            addCriterion("postcode like", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotLike(String value) {
            addCriterion("postcode not like", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeIn(List<String> values) {
            addCriterion("postcode in", values, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotIn(List<String> values) {
            addCriterion("postcode not in", values, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeBetween(String value1, String value2) {
            addCriterion("postcode between", value1, value2, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotBetween(String value1, String value2) {
            addCriterion("postcode not between", value1, value2, "postcode");
            return (Criteria) this;
        }

        public Criteria andCandidateNumberIsNull() {
            addCriterion("candidate_number is null");
            return (Criteria) this;
        }

        public Criteria andCandidateNumberIsNotNull() {
            addCriterion("candidate_number is not null");
            return (Criteria) this;
        }

        public Criteria andCandidateNumberEqualTo(String value) {
            addCriterion("candidate_number =", value, "candidateNumber");
            return (Criteria) this;
        }

        public Criteria andCandidateNumberNotEqualTo(String value) {
            addCriterion("candidate_number <>", value, "candidateNumber");
            return (Criteria) this;
        }

        public Criteria andCandidateNumberGreaterThan(String value) {
            addCriterion("candidate_number >", value, "candidateNumber");
            return (Criteria) this;
        }

        public Criteria andCandidateNumberGreaterThanOrEqualTo(String value) {
            addCriterion("candidate_number >=", value, "candidateNumber");
            return (Criteria) this;
        }

        public Criteria andCandidateNumberLessThan(String value) {
            addCriterion("candidate_number <", value, "candidateNumber");
            return (Criteria) this;
        }

        public Criteria andCandidateNumberLessThanOrEqualTo(String value) {
            addCriterion("candidate_number <=", value, "candidateNumber");
            return (Criteria) this;
        }

        public Criteria andCandidateNumberLike(String value) {
            addCriterion("candidate_number like", value, "candidateNumber");
            return (Criteria) this;
        }

        public Criteria andCandidateNumberNotLike(String value) {
            addCriterion("candidate_number not like", value, "candidateNumber");
            return (Criteria) this;
        }

        public Criteria andCandidateNumberIn(List<String> values) {
            addCriterion("candidate_number in", values, "candidateNumber");
            return (Criteria) this;
        }

        public Criteria andCandidateNumberNotIn(List<String> values) {
            addCriterion("candidate_number not in", values, "candidateNumber");
            return (Criteria) this;
        }

        public Criteria andCandidateNumberBetween(String value1, String value2) {
            addCriterion("candidate_number between", value1, value2, "candidateNumber");
            return (Criteria) this;
        }

        public Criteria andCandidateNumberNotBetween(String value1, String value2) {
            addCriterion("candidate_number not between", value1, value2, "candidateNumber");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMailboxIsNull() {
            addCriterion("mailbox is null");
            return (Criteria) this;
        }

        public Criteria andMailboxIsNotNull() {
            addCriterion("mailbox is not null");
            return (Criteria) this;
        }

        public Criteria andMailboxEqualTo(String value) {
            addCriterion("mailbox =", value, "mailbox");
            return (Criteria) this;
        }

        public Criteria andMailboxNotEqualTo(String value) {
            addCriterion("mailbox <>", value, "mailbox");
            return (Criteria) this;
        }

        public Criteria andMailboxGreaterThan(String value) {
            addCriterion("mailbox >", value, "mailbox");
            return (Criteria) this;
        }

        public Criteria andMailboxGreaterThanOrEqualTo(String value) {
            addCriterion("mailbox >=", value, "mailbox");
            return (Criteria) this;
        }

        public Criteria andMailboxLessThan(String value) {
            addCriterion("mailbox <", value, "mailbox");
            return (Criteria) this;
        }

        public Criteria andMailboxLessThanOrEqualTo(String value) {
            addCriterion("mailbox <=", value, "mailbox");
            return (Criteria) this;
        }

        public Criteria andMailboxLike(String value) {
            addCriterion("mailbox like", value, "mailbox");
            return (Criteria) this;
        }

        public Criteria andMailboxNotLike(String value) {
            addCriterion("mailbox not like", value, "mailbox");
            return (Criteria) this;
        }

        public Criteria andMailboxIn(List<String> values) {
            addCriterion("mailbox in", values, "mailbox");
            return (Criteria) this;
        }

        public Criteria andMailboxNotIn(List<String> values) {
            addCriterion("mailbox not in", values, "mailbox");
            return (Criteria) this;
        }

        public Criteria andMailboxBetween(String value1, String value2) {
            addCriterion("mailbox between", value1, value2, "mailbox");
            return (Criteria) this;
        }

        public Criteria andMailboxNotBetween(String value1, String value2) {
            addCriterion("mailbox not between", value1, value2, "mailbox");
            return (Criteria) this;
        }

        public Criteria andQqNumberIsNull() {
            addCriterion("qq_number is null");
            return (Criteria) this;
        }

        public Criteria andQqNumberIsNotNull() {
            addCriterion("qq_number is not null");
            return (Criteria) this;
        }

        public Criteria andQqNumberEqualTo(String value) {
            addCriterion("qq_number =", value, "qqNumber");
            return (Criteria) this;
        }

        public Criteria andQqNumberNotEqualTo(String value) {
            addCriterion("qq_number <>", value, "qqNumber");
            return (Criteria) this;
        }

        public Criteria andQqNumberGreaterThan(String value) {
            addCriterion("qq_number >", value, "qqNumber");
            return (Criteria) this;
        }

        public Criteria andQqNumberGreaterThanOrEqualTo(String value) {
            addCriterion("qq_number >=", value, "qqNumber");
            return (Criteria) this;
        }

        public Criteria andQqNumberLessThan(String value) {
            addCriterion("qq_number <", value, "qqNumber");
            return (Criteria) this;
        }

        public Criteria andQqNumberLessThanOrEqualTo(String value) {
            addCriterion("qq_number <=", value, "qqNumber");
            return (Criteria) this;
        }

        public Criteria andQqNumberLike(String value) {
            addCriterion("qq_number like", value, "qqNumber");
            return (Criteria) this;
        }

        public Criteria andQqNumberNotLike(String value) {
            addCriterion("qq_number not like", value, "qqNumber");
            return (Criteria) this;
        }

        public Criteria andQqNumberIn(List<String> values) {
            addCriterion("qq_number in", values, "qqNumber");
            return (Criteria) this;
        }

        public Criteria andQqNumberNotIn(List<String> values) {
            addCriterion("qq_number not in", values, "qqNumber");
            return (Criteria) this;
        }

        public Criteria andQqNumberBetween(String value1, String value2) {
            addCriterion("qq_number between", value1, value2, "qqNumber");
            return (Criteria) this;
        }

        public Criteria andQqNumberNotBetween(String value1, String value2) {
            addCriterion("qq_number not between", value1, value2, "qqNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}