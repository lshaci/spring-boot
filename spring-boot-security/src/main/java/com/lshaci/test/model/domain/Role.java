package com.lshaci.test.model.domain;

import javax.persistence.*;

@Table(name = "t_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_name_zh")
    private String roleNameZh;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return role_name
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * @return role_name_zh
     */
    public String getRoleNameZh() {
        return roleNameZh;
    }

    /**
     * @param roleNameZh
     */
    public void setRoleNameZh(String roleNameZh) {
        this.roleNameZh = roleNameZh;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roleName=").append(roleName);
        sb.append(", roleNameZh=").append(roleNameZh);
        sb.append("]");
        return sb.toString();
    }
}