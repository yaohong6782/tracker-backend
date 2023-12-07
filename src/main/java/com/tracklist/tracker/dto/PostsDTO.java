package com.tracklist.tracker.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tracklist.tracker.entity.Users;

public class PostsDTO {

    private Long id;
    private String questionTitle;
    private Integer questionNumber;
    private String questionUrl;
    private String content;
    private UsersDTO usersDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public Integer getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(Integer questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getQuestionUrl() {
        return questionUrl;
    }

    public void setQuestionUrl(String questionUrl) {
        this.questionUrl = questionUrl;
    }

    //    public Long getUsers() {
//        return users;
//    }
//
//    public void setUsers(Long users) {
//        this.users = users;
//    }


    public UsersDTO getUsersDTO() {
        return usersDTO;
    }

    public void setUsersDTO(UsersDTO usersDTO) {
        this.usersDTO = usersDTO;
    }

    @Override
    public String toString() {
        return "PostsDTO{" +
                "id=" + id +
                ", questionTitle='" + questionTitle + '\'' +
                ", questionNumber=" + questionNumber +
                ", questionUrl='" + questionUrl + '\'' +
                ", content='" + content + '\'' +
                ", usersDTO=" + usersDTO +
                '}';
    }
}
