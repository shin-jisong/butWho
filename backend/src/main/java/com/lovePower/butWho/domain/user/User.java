package com.lovePower.butWho.domain.user;

import com.lovePower.butWho.domain.result.Result;
import com.lovePower.butWho.dto.result.response.FinalResponse;
import com.lovePower.butWho.dto.result.response.ResultSaveResponse;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class User {
    @Id
    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식을 맞춰주세요.")
    private String email = null;

    private String password = null;

    @Column(name = "nick_name")
    private String nickName = null;

    @Builder
    public User(String email,String password,String nickName){
        this.email = email;
        this.password = password;
        this.nickName = nickName;
    }

    //각 유저가 결과를 가짐
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Result> allResults = new ArrayList<>();
    protected User(){}

    //결과저장
    public void save(Result result){
        this.allResults.add(result);
    }

    public void clear(){
        //TODO 전부 다 클리어하지 않았을 경우 예외처리
        allResults.clear();
    }
}
