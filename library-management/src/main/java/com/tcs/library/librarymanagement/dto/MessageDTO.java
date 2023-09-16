package com.tcs.library.librarymanagement.dto;

import com.tcs.library.librarymanagement.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageDTO
{
    private StatusEnum status;
    private String message;

}

