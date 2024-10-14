package com.cream.dto;

import com.google.gson.annotations.Expose;

public class AdminDTO {
	@Expose  private int id;
	@Expose private String adminId;
	@Expose private String adminPw;
	@Expose  private int shucream;

    public AdminDTO() {}

    public AdminDTO(int id, String adminId, String adminPw, int shucream) {
        this.id = id;
        this.adminId = adminId;
        this.adminPw = adminPw;
        this.shucream = shucream;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getAdminId() { return adminId; }
    public void setAdminId(String adminId) { this.adminId = adminId; }

    public String getAdminPw() { return adminPw; }
    public void setAdminPw(String adminPw) { this.adminPw = adminPw; }

    public int getShucream() { return shucream; }
    public void setShucream(int shucream) { this.shucream = shucream; }
}