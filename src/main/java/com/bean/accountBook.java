package com.bean;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountBook {
    private Integer u_id;//用户id
    private Integer j_id;//账本id
    private String szqk; //收支情况
    private String money;
    private String lx;//类型
    private String bz;//备注
    private String date;//日期

}
