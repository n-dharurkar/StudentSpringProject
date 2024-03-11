package com.example.Student.model;

import lombok.*;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="Students")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Student {
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

	    @Column
	    private String fullname;

	    @Column
	    private int age;
	    
	    @Column
	    private String mobile_no;
	    
	    @Column
	    private String birth_date;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getFullname() {
			return fullname;
		}

		public void setFullname(String fullname) {
			this.fullname = fullname;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getMobile_no() {
			return mobile_no;
		}

		public void setMobile_no(String mobile_no) {
			this.mobile_no = mobile_no;
		}

		public String getBirth_date() {
			return birth_date;
		}

		public void setBirth_date(String birth_date) {
			this.birth_date = birth_date;
		}

		

}
