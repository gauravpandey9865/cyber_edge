package com.project.cyberedge.model;


import com.project.cyberedge.enums.RoleEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Entity
@Builder
@Setter
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor
public class Role extends AbstractEntity {
    @Enumerated(EnumType.STRING)
    private RoleEnum roleName ;

}
