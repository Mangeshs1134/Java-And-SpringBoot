package com.myproject.myJournalProject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailEntity {
    
    // class data members
    private String to;
    private String msgBody;
    private String subject;

}
