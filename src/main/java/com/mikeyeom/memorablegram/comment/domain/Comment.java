package com.mikeyeom.memorablegram.comment.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name="`comment`")
@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	
	@Column(name="postId")
	public int postId;
	
	@Column(name="userId")
	public int userId;
	
	public String contents;
	
	@CreationTimestamp
	@Column(name="createdAt")
	public LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name="updatedAt")
	public LocalDateTime updatedAt;
}
