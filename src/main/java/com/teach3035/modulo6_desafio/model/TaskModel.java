package com.teach3035.modulo6_desafio.model;

import com.teach3035.modulo6_desafio.model.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@SoftDelete
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = {"user"})
public class TaskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String title;

    @Setter
    @NonNull
    private String description;

    @Setter
    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
