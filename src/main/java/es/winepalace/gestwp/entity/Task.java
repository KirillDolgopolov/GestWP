package es.winepalace.gestwp.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
@Table
public class Task {
    @Id
    @Column("taskID")
    private Integer ID;
    @NotNull
    @Column("Description")
    private String shopName;
    private String taskDescription;
    private String author;
    private String worker;
    private String status;
    private boolean isDone;

}
