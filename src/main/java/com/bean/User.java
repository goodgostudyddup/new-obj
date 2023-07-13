package com.bean;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private Integer u_id;
    private String u_name;
    private String u_pwd;
}
